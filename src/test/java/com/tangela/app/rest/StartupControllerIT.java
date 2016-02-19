package com.tangela.app.rest;

import com.tangela.TangelaServicesApplication;
import com.tangela.app.rest.response.GetStartupsResponse;
import com.tangela.domain.model.Startup;
import com.tangela.domain.model.StartupFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TangelaServicesApplication.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@ActiveProfiles("local")
public class StartupControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL base;

    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
    }

    @Test
    public void testGetStartups() throws Exception {
        final ResponseEntity<GetStartupsResponse> response =
                template.getForEntity(base.toURI().resolve("/startups/"), GetStartupsResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().startups().size(), 10);
    }

    @Test
    public void testGetStartupsByMarkets() throws Exception {
        final ResponseEntity<GetStartupsResponse> response =
                template.getForEntity(base.toURI().resolve("/startups/?markets=Testing,Technology"), GetStartupsResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().startups().size(), 2);
    }

    @Test
    public void testGetStartupsByLocations() throws Exception {
        final ResponseEntity<GetStartupsResponse> response =
                template.getForEntity(base.toURI().resolve("/startups/?locations=Vienna,Manitoba"), GetStartupsResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().startups().size(), 3);
    }

    @Test
    public void testGetStartupsByQuality() throws Exception {
        final ResponseEntity<GetStartupsResponse> response =
                template.getForEntity(base.toURI().resolve("/startups/?quality=1"), GetStartupsResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().startups().size(), 10);
    }

    @Test
    public void testGetStartupsByCreatedAt() throws Exception {
        final ResponseEntity<GetStartupsResponse> response =
                template.getForEntity(base.toURI().resolve("/startups/?createdAt=2011-01-01"), GetStartupsResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().startups().size(), 9);
    }

    @Test
    public void testGetStartupsByMarketsLocationsQualityAndCreatedAt() throws Exception {
        final ResponseEntity<GetStartupsResponse> response =
                template.getForEntity(base.toURI().resolve("/startups/?markets=Testing,Technology&locations=Vienna,Manitoba&quality=1&createdAt=2011-01-01"), GetStartupsResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().startups().size(), 2);
    }

    @Test
    public void testGetStartupByAngelId() throws Exception {
        final ResponseEntity<Startup> response =
                template.getForEntity(base.toURI().resolve("/startups/24507"), Startup.class);

        assertNotNull(response);
        assertNotNull(response.getBody().rid());
    }

    @Test
    public void testGetStartupByInvalidAngelId() throws Exception {
        final ResponseEntity<Startup> response =
                template.getForEntity(base.toURI().resolve("/startups/2450790"), Startup.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void testSaveUpdateAndDeleteStartup() throws Exception {
        Startup startup = StartupFixture.withDefaults();
        startup.setRid("");
        Long angelId = startup.angelId();

        ResponseEntity<Startup> response =
                template.postForEntity(base.toURI().resolve("/startups/"), startup, Startup.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        startup = response.getBody();
        startup.setName("Revelion");

        response = template.getForEntity(base.toURI().resolve("/startups/" + angelId), Startup.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        template.put(base.toURI().resolve("/startups/" + angelId), startup);

        response = template.getForEntity(base.toURI().resolve("/startups/" + angelId), Startup.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        startup = response.getBody();

        assertEquals(startup.name(), "Revelion");

        template.delete(base.toURI().resolve("/startups/" + angelId));

        response = template.getForEntity(base.toURI().resolve("/startups/" + angelId), Startup.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }
}

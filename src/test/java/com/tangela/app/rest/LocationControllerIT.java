package com.tangela.app.rest;

import com.tangela.TangelaServicesApplication;
import com.tangela.domain.model.Location;
import com.tangela.domain.model.LocationFixture;
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

import java.net.URL;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TangelaServicesApplication.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@ActiveProfiles("local")
public class LocationControllerIT {

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
    public void testGetLocationByAngelId() throws Exception {
        final ResponseEntity<Location> response =
                template.getForEntity(base.toURI().resolve("/locations/1683"), Location.class);

        assertNotNull(response);
        assertNotNull(response.getBody().rid());
    }

    @Test
    public void testGetLocationByInvalidAngelId() throws Exception {
        final ResponseEntity<Location> response =
                template.getForEntity(base.toURI().resolve("/locations/98"), Location.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void testSaveUpdateAndDeleteLocation() throws Exception {
        Location location = LocationFixture.cityWithDefaults();
        location.setRid("");
        Long angelId = location.angelId();

        ResponseEntity<Location> response =
                template.postForEntity(base.toURI().resolve("/locations/"), location, Location.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        location = response.getBody();
        location.setName("Bruselas");
        location.setDisplayName("Bruselas");

        response = template.getForEntity(base.toURI().resolve("/locations/" + angelId), Location.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        template.put(base.toURI().resolve("/locations/" + angelId), location);

        response = template.getForEntity(base.toURI().resolve("/locations/" + angelId), Location.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        location = response.getBody();

        assertEquals(location.name(), "Bruselas");
        assertEquals(location.displayName(), "Bruselas");

        template.delete(base.toURI().resolve("/locations/" + angelId));

        response = template.getForEntity(base.toURI().resolve("/locations/" + angelId), Location.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }
}

package com.tangela.app.rest;

import com.tangela.TangelaServicesApplication;
import com.tangela.domain.model.Round;
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

import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TangelaServicesApplication.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@ActiveProfiles("local")
public class RoundControllerIT {

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
    public void testGetRoundByAngelId() throws Exception {
        final ResponseEntity<Round> response =
                template.getForEntity(base.toURI().resolve("/rounds/4500"), Round.class);

        assertNotNull(response);
        assertNotNull(response.getBody().rid());
    }

    @Test
    public void testGetRoundByInvalidAngelId() throws Exception {
        final ResponseEntity<Round> response =
                template.getForEntity(base.toURI().resolve("/rounds/96"), Round.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void testSaveUpdateAndDeleteRound() throws Exception {
        Long angelId = 999L;

        Round round = new Round("", 1, angelId, "Serie D", "Serie D", "10000", now(UTC), 10L, "https://angel.co/round-source", "", "", "");
        ResponseEntity<Round> response =
                template.postForEntity(base.toURI().resolve("/rounds/"), round, Round.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        round = response.getBody();
        round.setName("Serie F");

        response = template.getForEntity(base.toURI().resolve("/rounds/" + angelId), Round.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        template.put(base.toURI().resolve("/rounds/" + angelId), round);

        response = template.getForEntity(base.toURI().resolve("/rounds/" + angelId), Round.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        round = response.getBody();

        assertEquals(round.name(), "Serie F");

        template.delete(base.toURI().resolve("/rounds/" + angelId));

        response = template.getForEntity(base.toURI().resolve("/rounds/" + angelId), Round.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }
}

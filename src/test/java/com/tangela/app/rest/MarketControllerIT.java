package com.tangela.app.rest;

import com.tangela.TangelaServicesApplication;
import com.tangela.domain.model.Location;
import com.tangela.domain.model.Market;
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
public class MarketControllerIT {

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
    public void testGetMarketByAngelId() throws Exception {
        final ResponseEntity<Market> response =
                template.getForEntity(base.toURI().resolve("/markets/4001"), Market.class);

        assertNotNull(response);
        assertNotNull(response.getBody().rid());
    }

    @Test
    public void testGetMarketByInvalidAngelId() throws Exception {
        final ResponseEntity<Market> response =
                template.getForEntity(base.toURI().resolve("/markets/99"), Market.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void testSaveUpdateAndDeleteMarket() throws Exception {
        Long angelId = 4999L;

        Market market = new Market("", 1, angelId, "Testing", "Testing", "https://angel.co/testing");
        ResponseEntity<Market> response =
                template.postForEntity(base.toURI().resolve("/markets/"), market, Market.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        market = response.getBody();
        market.setName("Technology");
        market.setDisplayName("Technology");

        response = template.getForEntity(base.toURI().resolve("/markets/" + angelId), Market.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        template.put(base.toURI().resolve("/markets/" + angelId), market);

        response = template.getForEntity(base.toURI().resolve("/markets/" + angelId), Market.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        market = response.getBody();

        assertEquals(market.name(), "Technology");
        assertEquals(market.displayName(), "Technology");

        template.delete(base.toURI().resolve("/markets/" + angelId));

        response = template.getForEntity(base.toURI().resolve("/markets/" + angelId), Market.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }
}

package com.tangela.app.rest;

import com.tangela.TangelaServicesApplication;
import com.tangela.domain.model.RoleIn;
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
public class RoleInControllerIT {

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
    public void testGetRoleInByAngelId() throws Exception {
        final ResponseEntity<RoleIn> response =
                template.getForEntity(base.toURI().resolve("/rolesIn/7000"), RoleIn.class);

        assertNotNull(response);
        assertNotNull(response.getBody().rid());
    }

    @Test
    public void testGetRoleInByInvalidAngelId() throws Exception {
        final ResponseEntity<RoleIn> response =
                template.getForEntity(base.toURI().resolve("/rolesIn/97"), RoleIn.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void testSaveUpdateAndDeleteRoleIn() throws Exception {
        Long angelId = 7999L;

        RoleIn roleIn = new RoleIn("", 1, angelId, "E", now(UTC), null, false, null);
        ResponseEntity<RoleIn> response =
                template.postForEntity(base.toURI().resolve("/rolesIn/"), roleIn, RoleIn.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        roleIn = response.getBody();
        roleIn.setRole("F");

        response = template.getForEntity(base.toURI().resolve("/rolesIn/" + angelId), RoleIn.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        template.put(base.toURI().resolve("/rolesIn/" + angelId), roleIn);

        response = template.getForEntity(base.toURI().resolve("/rolesIn/" + angelId), RoleIn.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        roleIn = response.getBody();

        assertEquals(roleIn.role(), "F");

        template.delete(base.toURI().resolve("/rolesIn/" + angelId));

        response = template.getForEntity(base.toURI().resolve("/rolesIn/" + angelId), RoleIn.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }
}

package com.tangela.app.rest;

import com.tangela.TangelaServicesApplication;
import com.tangela.domain.model.RoleIn;
import com.tangela.domain.model.RoleInStartup;
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
public class RoleInStartupControllerIT {

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
    public void testGetRoleInStartupByAngelId() throws Exception {
        final ResponseEntity<RoleInStartup> response =
                template.getForEntity(base.toURI().resolve("/rolesInStartup/7500"), RoleInStartup.class);

        assertNotNull(response);
        assertNotNull(response.getBody().rid());
    }

    @Test
    public void testGetRoleInStartupByInvalidAngelId() throws Exception {
        final ResponseEntity<RoleInStartup> response =
                template.getForEntity(base.toURI().resolve("/rolesInStartup/96"), RoleInStartup.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void testSaveUpdateAndDeleteRoleInStartup() throws Exception {
        Long angelId = 8999L;

        RoleInStartup roleInStartup = new RoleInStartup("", 1, angelId, "E", now(UTC), null, false, null, null);
        ResponseEntity<RoleInStartup> response =
                template.postForEntity(base.toURI().resolve("/rolesInStartup/"), roleInStartup, RoleInStartup.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        roleInStartup = response.getBody();
        roleInStartup.setRole("F");

        response = template.getForEntity(base.toURI().resolve("/rolesInStartup/" + angelId), RoleInStartup.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        template.put(base.toURI().resolve("/rolesInStartup/" + angelId), roleInStartup);

        response = template.getForEntity(base.toURI().resolve("/rolesInStartup/" + angelId), RoleInStartup.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        roleInStartup = response.getBody();

        assertEquals(roleInStartup.role(), "F");

        template.delete(base.toURI().resolve("/rolesInStartup/" + angelId));

        response = template.getForEntity(base.toURI().resolve("/rolesInStartup/" + angelId), RoleInStartup.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }
}

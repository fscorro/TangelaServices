package com.tangela.app.rest;

import com.tangela.TangelaServicesApplication;
import com.tangela.app.rest.response.GetUsersResponse;
import com.tangela.domain.model.User;
import com.tangela.domain.model.UserFixture;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TangelaServicesApplication.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@ActiveProfiles("local")
public class UserControllerIT {

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
    public void testGetUsers() throws Exception {
        final ResponseEntity<GetUsersResponse> response =
                template.getForEntity(base.toURI().resolve("/users/"), GetUsersResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().users().size(), 3);
    }

    @Test
    public void testGetUsersByLocations() throws Exception {
        final ResponseEntity<GetUsersResponse> response =
                template.getForEntity(base.toURI().resolve("/users/?locations=San%20Jose,Vienna"), GetUsersResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().users().size(), 2);
    }

    @Test
    public void testGetUsersByMarkets() throws Exception {
        final ResponseEntity<GetUsersResponse> response =
                template.getForEntity(base.toURI().resolve("/users/?markets=Translation,Testing"), GetUsersResponse.class);

        assertNotNull(response);
        assertEquals(response.getBody().users().size(), 1);
    }

    @Test
    public void testGetUserByAngelId() throws Exception {
        final ResponseEntity<User> response =
                template.getForEntity(base.toURI().resolve("/users/6000"), User.class);

        assertNotNull(response);
        assertNotNull(response.getBody().rid());
    }

    @Test
    public void testGetUserByInvalidAngelId() throws Exception {
        final ResponseEntity<User> response =
                template.getForEntity(base.toURI().resolve("/users/6009"), User.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }

    @Test
    public void testSaveUpdateAndDeleteUser() throws Exception {
        User user = UserFixture.withDefaults();
        user.setRid("");
        Long angelId = user.angelId();

        ResponseEntity<User> response =
                template.postForEntity(base.toURI().resolve("/users/"), user, User.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        user = response.getBody();
        user.setName("Tobias Cat");

        response = template.getForEntity(base.toURI().resolve("/users/" + angelId), User.class);

        assertNotNull(response);
        assertNotNull(response.getBody());

        template.put(base.toURI().resolve("/users/" + angelId), user);

        response = template.getForEntity(base.toURI().resolve("/users/" + angelId), User.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        user = response.getBody();

        assertEquals(user.name(), "Tobias Cat");

        template.delete(base.toURI().resolve("/users/" + angelId));

        response = template.getForEntity(base.toURI().resolve("/users/" + angelId), User.class);

        assertNotNull(response);
        assertNull(response.getBody());
    }
}

package com.tangela.app.rest;

import com.tangela.app.rest.request.GetStartupsRequest;
import com.tangela.app.rest.response.GetStartupsResponse;
import com.tangela.app.rest.response.GetUsersResponse;
import com.tangela.domain.model.Startup;
import com.tangela.domain.model.StartupFixture;
import com.tangela.domain.model.User;
import com.tangela.domain.model.UserFixture;
import com.tangela.domain.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void getUsers() {
        Mockito.when(userService.getUsers(newArrayList("Testing"), newArrayList("Vienna"), 1, null)).thenReturn(newArrayList(UserFixture.withDefaults()));

        GetStartupsRequest request = new GetStartupsRequest("Testing", "Vienna", 1, null);
        GetUsersResponse response = userController.getUsers(request);

        assertNotNull(response);
        assertEquals(response.users().size(), 1);
    }

    @Test
    public void getUsersWithNullAndEmptyMarkets(){
        Mockito.when(userService.getUsers(null, newArrayList("Vienna"), 1, null)).thenReturn(newArrayList(UserFixture.withDefaults()));

        GetStartupsRequest request = new GetStartupsRequest("", "Vienna", 1, null);
        GetUsersResponse response = userController.getUsers(request);

        assertNotNull(response);
        assertEquals(response.users().size(), 1);

        request = new GetStartupsRequest(null, "Vienna", 1, null);
        response = userController.getUsers(request);

        assertNotNull(response);
        assertEquals(response.users().size(), 1);
    }

    @Test
    public void getUsersWithNullAndEmptyLocations(){
        when(userService.getUsers(newArrayList("Testing"), null, 1, null)).thenReturn(newArrayList(UserFixture.withDefaults()));

        GetStartupsRequest request = new GetStartupsRequest("Testing", "", 1, null);
        GetUsersResponse response = userController.getUsers(request);

        assertNotNull(response);
        assertEquals(response.users().size(), 1);

        request = new GetStartupsRequest("Testing", null, 1, null);
        response = userController.getUsers(request);

        assertNotNull(response);
        assertEquals(response.users().size(), 1);
    }

    @Test
    public void getUserByAngelId(){
        when(userService.getByAngelId(100L)).thenReturn(UserFixture.withDefaults());
        User user = userController.getUserByAngelId(100L);
        assertNotNull(user);
    }

    @Test
    public void testSaveUser(){
        User user = UserFixture.withDefaults();
        when(userService.save(user)).thenReturn(user);
        user = userController.saveUser(user);
        assertNotNull(user);
    }

    @Test
    public void testUpdateUser(){
        User user = UserFixture.withDefaults();
        when(userService.update(user)).thenReturn(user);
        user = userController.updateUser(user.angelId(), user);
        assertNotNull(user);
    }

    @Test
    public void testDeleteUser(){
        doNothing().when(userService).delete(90L);
        userController.deleteUser(90L);
    }
}

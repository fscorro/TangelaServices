package com.tangela.domain.service;

import com.tangela.domain.model.User;
import com.tangela.domain.model.UserFixture;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.UserQuery;
import com.tangela.domain.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private OrientDBServiceConnector serviceConnector;

    @Mock
    private QueryGenerator queryGenerator;

    @Test
    public void testGetUsers() {
        UserQuery userQuery = new UserQuery(newArrayList(UserFixture.withDefaults()));
        when(queryGenerator.getUsersFromStartupsQuery(newArrayList("Testing"), newArrayList("Vienna"), 1, null)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(userQuery);

        List<User> users = userService.getUsers(newArrayList("Testing"), newArrayList("Vienna"), 1, null);

        assertNotNull(users);
        assertEquals(users.size(), 1);

        verify(queryGenerator, times(1)).getUsersFromStartupsQuery(anyListOf(String.class), anyListOf(String.class), anyInt(), anyObject());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetUserByAngelId() {
        UserQuery userQuery = new UserQuery(newArrayList(UserFixture.withDefaults()));
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.USER)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(userQuery);

        User user = userService.getByAngelId(100L);
        assertNotNull(user);

        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetUserByInvalidAngelId() {
        UserQuery userQuery = new UserQuery(newArrayList());
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.USER)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(userQuery);
        User user = userService.getByAngelId(100L);
        assertNull(user);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetUserByInvalidAngelIdAndReturnNull() {
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.USER)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(null);
        User user = userService.getByAngelId(100L);
        assertNull(user);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testUpdateUser() {
        User user = UserFixture.withDefaults();
        UserQuery userQuery = new UserQuery(newArrayList(user));
        when(queryGenerator.getDocumentByAngelIdAndClass(user.angelId(), QueryGenerator.USER)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(userQuery);
        when(serviceConnector.updateDocument(user.rid().substring(1), user, String.class)).thenReturn("{}");
        user = userService.update(user);
        assertNotNull(user);
    }

    @Test
    public void testUpdateUserWithInvalidAngelId() {
        User user = UserFixture.withDefaults();
        UserQuery userQuery = new UserQuery(newArrayList(user));
        when(queryGenerator.getDocumentByAngelIdAndClass(43L, QueryGenerator.USER)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(userQuery);
        user = userService.update(user);
        assertNull(user);
    }

    @Test
    public void testUpdateUserWithInvalidJsonResponse() {
        String jsonResponse = "{";
        User user = UserFixture.withDefaults();
        UserQuery userQuery = new UserQuery(newArrayList(user));
        when(queryGenerator.getDocumentByAngelIdAndClass(user.angelId(), QueryGenerator.USER)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(userQuery);
        when(serviceConnector.updateDocument(user.rid().substring(1), user, String.class)).thenReturn(jsonResponse);
        user = userService.update(user);
        assertNull(user);
    }

    @Test
    public void testSaveUser() {
        User user = UserFixture.withDefaults();
        when(serviceConnector.saveDocument(user, User.class)).thenReturn(user);
        user = userService.save(user);
        assertNotNull(user);
    }

    @Test
    public void testDeleteUser() {
        User user = UserFixture.withDefaults();
        UserQuery userQuery = new UserQuery(newArrayList(user));
        when(queryGenerator.getDocumentByAngelIdAndClass(user.angelId(), QueryGenerator.USER)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(userQuery);
        doNothing().when(serviceConnector).deleteDocument(user.rid().substring(1));
        userService.delete(user.angelId());
        verify(serviceConnector, times(1)).deleteDocument(user.rid().substring(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserInvalidAngelId() {
        User user = UserFixture.withDefaults();
        UserQuery userQuery = new UserQuery(newArrayList(user));
        when(queryGenerator.getDocumentByAngelIdAndClass(1L, QueryGenerator.USER)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", UserQuery.class, User.FETCH_PLAN)).thenReturn(userQuery);
        userService.delete(user.angelId());
    }
}

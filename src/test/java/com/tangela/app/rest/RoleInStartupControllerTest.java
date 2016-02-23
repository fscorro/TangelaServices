package com.tangela.app.rest;

import com.tangela.domain.model.RoleInStartup;
import com.tangela.domain.model.RoleInStartupFixture;
import com.tangela.domain.service.impl.RoleInStartupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleInStartupControllerTest {

    @InjectMocks
    private RoleInStartupController roleInStartupController;

    @Mock
    private RoleInStartupServiceImpl roleInStartupService;

    @Test
    public void getRoleInStartupByAngelId(){
        when(roleInStartupService.getByAngelId(100L)).thenReturn(RoleInStartupFixture.withDefaults());
        RoleInStartup roleInStartup = roleInStartupController.getRoleInStartupByAngelId(100L);
        assertNotNull(roleInStartup);
    }

    @Test
    public void testSaveRoleInStartup(){
        RoleInStartup roleInStartup = RoleInStartupFixture.withDefaults();
        when(roleInStartupService.save(roleInStartup)).thenReturn(roleInStartup);
        roleInStartup = roleInStartupController.saveRoleInStartup(roleInStartup);
        assertNotNull(roleInStartup);
    }

    @Test
    public void testUpdateRoleInStartup(){
        RoleInStartup roleInStartup = RoleInStartupFixture.withDefaults();
        when(roleInStartupService.update(roleInStartup)).thenReturn(roleInStartup);
        roleInStartup = roleInStartupController.updateRoleInStartup(roleInStartup.angelId(), roleInStartup);
        assertNotNull(roleInStartup);
    }

    @Test
    public void testDeleteRoleInStartup(){
        doNothing().when(roleInStartupService).delete(90L);
        roleInStartupController.deleteRoleInStartup(90L);
    }
}

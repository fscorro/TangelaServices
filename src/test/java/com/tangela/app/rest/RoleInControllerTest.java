package com.tangela.app.rest;

import com.tangela.domain.model.RoleIn;
import com.tangela.domain.model.RoleInFixture;
import com.tangela.domain.service.impl.RoleInServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleInControllerTest {

    @InjectMocks
    private RoleInController roleInController;

    @Mock
    private RoleInServiceImpl roleInService;

    @Test
    public void getRoleInByAngelId(){
        when(roleInService.getByAngelId(100L)).thenReturn(RoleInFixture.withDefaults());
        RoleIn roleIn = roleInController.getRoleInByAngelId(100L);
        assertNotNull(roleIn);
    }

    @Test
    public void testSaveRoleIn(){
        RoleIn roleIn = RoleInFixture.withDefaults();
        when(roleInService.save(roleIn)).thenReturn(roleIn);
        roleIn = roleInController.saveRoleIn(roleIn);
        assertNotNull(roleIn);
    }

    @Test
    public void testUpdateRoleIn(){
        RoleIn roleIn = RoleInFixture.withDefaults();
        when(roleInService.update(roleIn)).thenReturn(roleIn);
        roleIn = roleInController.updateRoleIn(roleIn.angelId(), roleIn);
        assertNotNull(roleIn);
    }

    @Test
    public void testDeleteRoleIn(){
        doNothing().when(roleInService).delete(90L);
        roleInController.deleteRoleIn(90L);
    }
}

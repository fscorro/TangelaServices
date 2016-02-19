package com.tangela.app.rest;

import com.tangela.domain.model.RoleInStartup;
import com.tangela.domain.service.impl.RoleInStartupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rolesInStartup")
public class RoleInStartupController {

    @Autowired
    private RoleInStartupServiceImpl roleInStartupService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RoleInStartup saveRoleInStartup(@RequestBody RoleInStartup roleInStartup) {
        return roleInStartupService.save(roleInStartup);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.GET)
    public RoleInStartup getRoleInStartupByAngelId(@PathVariable final Long angelId) {
        return roleInStartupService.getByAngelId(angelId);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.PUT)
    public RoleInStartup updateRoleInStartup(@PathVariable final Long angelId,
                                             @RequestBody RoleInStartup roleInStartup) {
        return roleInStartupService.update(roleInStartup);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.DELETE)
    public void deleteRoleInStartup(@PathVariable final Long angelId) {
        roleInStartupService.delete(angelId);
    }
}

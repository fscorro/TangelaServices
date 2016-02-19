package com.tangela.app.rest;

import com.tangela.domain.model.RoleIn;
import com.tangela.domain.service.impl.RoleInServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rolesIn")
public class RoleInController {

    @Autowired
    private RoleInServiceImpl roleInService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RoleIn saveRoleIn(@RequestBody RoleIn roleIn) {
        return roleInService.save(roleIn);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.GET)
    public RoleIn getRoleInByAngelId(@PathVariable final Long angelId) {
        return roleInService.getByAngelId(angelId);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.PUT)
    public RoleIn updateRoleIn(@PathVariable final Long angelId,
                               @RequestBody RoleIn roleIn) {
        return roleInService.update(roleIn);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.DELETE)
    public void deleteRoleIn(@PathVariable final Long angelId) {
        roleInService.delete(angelId);
    }
}

package com.tangela.app.rest;

import com.tangela.app.rest.request.GetStartupsRequest;
import com.tangela.app.rest.response.GetUsersResponse;
import com.tangela.domain.model.User;
import com.tangela.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get Users related to filter startups
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public GetUsersResponse getUsers(GetStartupsRequest request) {
        List<String> markets = (request.getMarkets() != null && !request.getMarkets().isEmpty()) ? newArrayList(request.getMarkets().split(",")) : null;
        List<String> locations = (request.getLocations() != null && !request.getLocations().isEmpty()) ? newArrayList(request.getLocations().split(",")) : null;

        return new GetUsersResponse(userService.getUsers(markets, locations, request.getQuality(), request.getCreatedAt()));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.GET)
    public User getUserByAngelId(@PathVariable final Long angelId) {
        return userService.getByAngelId(angelId);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable final Long angelId,
                                 @RequestBody User user) {
        return userService.update(user);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable final Long angelId) {
        userService.delete(angelId);
    }
}

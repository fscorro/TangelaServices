package com.tangela.services.app.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationRestController
{
    @RequestMapping(value = "/{storeId}/{category}/{event}", method = RequestMethod.POST)
    public void pushNotification(
            @PathVariable final Integer storeId,
            @PathVariable final String category,
            @PathVariable final String event) {

    }
}
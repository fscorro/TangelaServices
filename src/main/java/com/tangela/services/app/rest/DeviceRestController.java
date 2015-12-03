package com.tangela.services.app.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/devices")
public class DeviceRestController
{
    @RequestMapping(value = "/{storeId}/{platform}/{token}", method = RequestMethod.POST)
    public void saveDevice(
            @PathVariable final Integer storeId,
            @PathVariable final String platform,
            @PathVariable final String token) {


    }
}
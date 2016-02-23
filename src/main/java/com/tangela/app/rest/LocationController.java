package com.tangela.app.rest;

import com.tangela.domain.model.Location;
import com.tangela.domain.service.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationServiceImpl locationService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Location saveLocation(@RequestBody Location location) {
        return locationService.save(location);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.GET)
    public Location getLocationByAngelId(@PathVariable final Long angelId) {
        return locationService.getByAngelId(angelId);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.PUT)
    public Location updateLocation(@PathVariable final Long angelId,
                                 @RequestBody Location location) {
        return locationService.update(location);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable final Long angelId) {
        locationService.delete(angelId);
    }
}

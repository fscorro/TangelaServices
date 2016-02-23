package com.tangela.app.rest;

import com.tangela.app.rest.request.GetStartupsRequest;
import com.tangela.app.rest.response.GetStartupsResponse;
import com.tangela.domain.model.Startup;
import com.tangela.domain.service.StartupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

@RestController
@RequestMapping("/startups")
public class StartupController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartupController.class);

    @Autowired
    private StartupService startupService;

    /**
     * Get startups by Location, Market, quality and createdAt
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public GetStartupsResponse getStartups(GetStartupsRequest request) {
        List<String> markets = (request.getMarkets() != null && !request.getMarkets().isEmpty()) ? newArrayList(request.getMarkets().split(",")) : null;
        List<String> locations = (request.getLocations() != null && !request.getLocations().isEmpty()) ? newArrayList(request.getLocations().split(",")) : null;

        return new GetStartupsResponse(startupService.getStartups(markets, locations, request.getQuality(), request.getCreatedAt()));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Startup saveStartup(@RequestBody Startup startup) {
        return startupService.save(startup);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.GET)
    public Startup getStartupByAngelId(@PathVariable final Long angelId) {
        return startupService.getByAngelId(angelId);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.PUT)
    public Startup updateStartup(@PathVariable final Long angelId,
                               @RequestBody Startup startup) {
        return startupService.update(startup);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.DELETE)
    public void deleteStartup(@PathVariable final Long angelId) {
        startupService.delete(angelId);
    }
}

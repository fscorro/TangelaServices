package com.tangela.app.rest;

import com.tangela.domain.model.Market;
import com.tangela.domain.service.impl.MarketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/markets")
public class MarketController {

    @Autowired
    private MarketServiceImpl marketService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Market saveMarket(@RequestBody Market market) {
        return marketService.save(market);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.GET)
    public Market getMarketByAngelId(@PathVariable final Long angelId) {
        return marketService.getByAngelId(angelId);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.PUT)
    public Market updateMarket(@PathVariable final Long angelId,
                               @RequestBody Market market) {
        return marketService.update(market);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.DELETE)
    public void deleteMarket(@PathVariable final Long angelId) {
        marketService.delete(angelId);
    }

}

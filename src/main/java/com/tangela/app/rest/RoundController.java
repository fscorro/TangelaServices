package com.tangela.app.rest;

import com.tangela.domain.model.Round;
import com.tangela.domain.service.impl.RoundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rounds")
public class RoundController {

    @Autowired
    private RoundServiceImpl roundService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Round saveRound(@RequestBody Round round) {
        return roundService.save(round);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.GET)
    public Round getRoundByAngelId(@PathVariable final Long angelId) {
        return roundService.getByAngelId(angelId);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.PUT)
    public Round updateRound(@PathVariable final Long angelId,
                               @RequestBody Round round) {
        return roundService.update(round);
    }

    @RequestMapping(value = "/{angelId}", method = RequestMethod.DELETE)
    public void deleteRound(@PathVariable final Long angelId) {
        roundService.delete(angelId);
    }
}

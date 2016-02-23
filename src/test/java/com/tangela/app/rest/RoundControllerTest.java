package com.tangela.app.rest;

import com.tangela.domain.model.Round;
import com.tangela.domain.model.RoundFixture;
import com.tangela.domain.service.impl.RoundServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoundControllerTest {

    @InjectMocks
    private RoundController roundController;

    @Mock
    private RoundServiceImpl roundService;

    @Test
    public void getRoundByAngelId(){
        when(roundService.getByAngelId(100L)).thenReturn(RoundFixture.withDefaults());
        Round round = roundController.getRoundByAngelId(100L);
        assertNotNull(round);
    }

    @Test
    public void testSaveRound(){
        Round round = RoundFixture.withDefaults();
        when(roundService.save(round)).thenReturn(round);
        round = roundController.saveRound(round);
        assertNotNull(round);
    }

    @Test
    public void testUpdateRound(){
        Round round = RoundFixture.withDefaults();
        when(roundService.update(round)).thenReturn(round);
        round = roundController.updateRound(round.angelId(), round);
        assertNotNull(round);
    }

    @Test
    public void testDeleteRound(){
        doNothing().when(roundService).delete(90L);
        roundController.deleteRound(90L);
    }
}

package com.tangela.app.rest;

import com.tangela.domain.model.Market;
import com.tangela.domain.model.MarketFixture;
import com.tangela.domain.service.impl.MarketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarketControllerTest {

    @InjectMocks
    private MarketController marketController;

    @Mock
    private MarketServiceImpl marketService;

    @Test
    public void testGetMarketByAngelId(){
        when(marketService.getByAngelId(100L)).thenReturn(MarketFixture.withDefaults());
        Market market = marketController.getMarketByAngelId(100L);
        assertNotNull(market);
    }

    @Test
    public void testSaveMarket(){
        Market market = MarketFixture.withDefaults();
        when(marketService.save(market)).thenReturn(market);
        market = marketController.saveMarket(market);
        assertNotNull(market);
    }

    @Test
    public void testUpdateMarket(){
        Market market = MarketFixture.withDefaults();
        when(marketService.update(market)).thenReturn(market);
        market = marketController.updateMarket(market.angelId(), market);
        assertNotNull(market);
    }

    @Test
    public void testDeleteMarket(){
        doNothing().when(marketService).delete(90L);
        marketController.deleteMarket(90L);
    }
}

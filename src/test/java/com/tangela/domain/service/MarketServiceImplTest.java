package com.tangela.domain.service;

import com.tangela.domain.model.Market;
import com.tangela.domain.model.MarketFixture;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.MarketQuery;
import com.tangela.domain.service.impl.MarketServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MarketServiceImplTest {

    @InjectMocks
    private MarketServiceImpl marketService;

    @Mock
    private OrientDBServiceConnector serviceConnector;

    @Mock
    private QueryGenerator queryGenerator;

    @Test
    public void testGetMarketByAngelId() {
        MarketQuery marketQuery = new MarketQuery(newArrayList(MarketFixture.withDefaults()));
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.MARKET)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", MarketQuery.class, Market.FETCH_PLAN)).thenReturn(marketQuery);
        Market market  = marketService.getByAngelId(100L);
        assertNotNull(market);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetMarketByInvalidAngelId() {
        MarketQuery marketQuery = new MarketQuery(newArrayList());
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.MARKET)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", MarketQuery.class, Market.FETCH_PLAN)).thenReturn(marketQuery);
        Market market = marketService.getByAngelId(100L);
        assertNull(market);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetMarketByInvalidAngelIdAndReturnNull() {
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.MARKET)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", MarketQuery.class, Market.FETCH_PLAN)).thenReturn(null);
        Market market = marketService.getByAngelId(100L);
        assertNull(market);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testUpdateMarket() {
        Market market = MarketFixture.withDefaults();
        MarketQuery marketQuery = new MarketQuery(newArrayList(market));
        when(queryGenerator.getDocumentByAngelIdAndClass(market.angelId(), QueryGenerator.MARKET)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", MarketQuery.class, Market.FETCH_PLAN)).thenReturn(marketQuery);
        when(serviceConnector.updateDocument(market.rid().substring(1), market, String.class)).thenReturn("{}");
        market = marketService.update(market);
        assertNotNull(market);
    }

    @Test
    public void testUpdateMarketWithInvalidAngelId() {
        Market market = MarketFixture.withDefaults();
        MarketQuery marketQuery = new MarketQuery(newArrayList(market));
        when(queryGenerator.getDocumentByAngelIdAndClass(43L, QueryGenerator.MARKET)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", MarketQuery.class, Market.FETCH_PLAN)).thenReturn(marketQuery);
        market = marketService.update(market);
        assertNull(market);
    }

    @Test
    public void testUpdateMarketWithInvalidJsonResponse() {
        String jsonResponse = "{";
        Market market = MarketFixture.withDefaults();
        MarketQuery marketQuery = new MarketQuery(newArrayList(market));
        when(queryGenerator.getDocumentByAngelIdAndClass(market.angelId(), QueryGenerator.MARKET)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", MarketQuery.class, Market.FETCH_PLAN)).thenReturn(marketQuery);
        when(serviceConnector.updateDocument(market.rid().substring(1), market, String.class)).thenReturn(jsonResponse);
        market = marketService.update(market);
        assertNull(market);
    }

    @Test
    public void testSaveMarket() {
        Market market = MarketFixture.withDefaults();
        when(serviceConnector.saveDocument(market, Market.class)).thenReturn(market);
        market = marketService.save(market);
        assertNotNull(market);
    }

    @Test
    public void testDeleteMarket() {
        Market market = MarketFixture.withDefaults();
        MarketQuery marketQuery = new MarketQuery(newArrayList(market));
        when(queryGenerator.getDocumentByAngelIdAndClass(market.angelId(), QueryGenerator.MARKET)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", MarketQuery.class, Market.FETCH_PLAN)).thenReturn(marketQuery);
        doNothing().when(serviceConnector).deleteDocument(market.rid().substring(1));
        marketService.delete(market.angelId());
        verify(serviceConnector, times(1)).deleteDocument(market.rid().substring(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteMarketInvalidAngelId() {
        Market market = MarketFixture.withDefaults();
        MarketQuery marketQuery = new MarketQuery(newArrayList(market));
        when(queryGenerator.getDocumentByAngelIdAndClass(1L, QueryGenerator.MARKET)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", MarketQuery.class, Market.FETCH_PLAN)).thenReturn(marketQuery);
        marketService.delete(market.angelId());
    }
}

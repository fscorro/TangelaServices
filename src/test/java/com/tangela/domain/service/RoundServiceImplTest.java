package com.tangela.domain.service;

import com.tangela.domain.model.Round;
import com.tangela.domain.model.RoundFixture;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.RoundQuery;
import com.tangela.domain.service.impl.RoundServiceImpl;
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
public class RoundServiceImplTest {

    @InjectMocks
    private RoundServiceImpl roundService;

    @Mock
    private OrientDBServiceConnector serviceConnector;

    @Mock
    private QueryGenerator queryGenerator;

    @Test
    public void testGetRoundByAngelId() {
        RoundQuery roundQuery = new RoundQuery(newArrayList(RoundFixture.withDefaults()));
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROUND)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoundQuery.class, Round.FETCH_PLAN)).thenReturn(roundQuery);
        Round round = roundService.getByAngelId(100L);
        assertNotNull(round);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetRoundByInvalidAngelId() {
        RoundQuery roundQuery = new RoundQuery(newArrayList());
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROUND)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoundQuery.class, Round.FETCH_PLAN)).thenReturn(roundQuery);
        Round round = roundService.getByAngelId(100L);
        assertNull(round);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetRoundByInvalidAngelIdAndReturnNull() {
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROUND)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoundQuery.class, Round.FETCH_PLAN)).thenReturn(null);
        Round round = roundService.getByAngelId(100L);
        assertNull(round);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testUpdateRound() {
        Round round = RoundFixture.withDefaults();
        RoundQuery roundQuery = new RoundQuery(newArrayList(round));
        when(queryGenerator.getDocumentByAngelIdAndClass(round.angelId(), QueryGenerator.ROUND)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoundQuery.class, Round.FETCH_PLAN)).thenReturn(roundQuery);
        when(serviceConnector.updateDocument(round.rid().substring(1), round, String.class)).thenReturn("{}");
        round = roundService.update(round);
        assertNotNull(round);
    }

    @Test
    public void testUpdateRoundWithInvalidAngelId() {
        Round round = RoundFixture.withDefaults();
        RoundQuery roundQuery = new RoundQuery(newArrayList(round));
        when(queryGenerator.getDocumentByAngelIdAndClass(43L, QueryGenerator.ROUND)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoundQuery.class, Round.FETCH_PLAN)).thenReturn(roundQuery);
        round = roundService.update(round);
        assertNull(round);
    }

    @Test
    public void testUpdateRoundWithInvalidJsonResponse() {
        String jsonResponse = "{";
        Round round = RoundFixture.withDefaults();
        RoundQuery roundQuery = new RoundQuery(newArrayList(round));
        when(queryGenerator.getDocumentByAngelIdAndClass(round.angelId(), QueryGenerator.ROUND)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoundQuery.class, Round.FETCH_PLAN)).thenReturn(roundQuery);
        when(serviceConnector.updateDocument(round.rid().substring(1), round, String.class)).thenReturn(jsonResponse);
        round = roundService.update(round);
        assertNull(round);
    }

    @Test
    public void testSaveRound() {
        Round round = RoundFixture.withDefaults();
        when(serviceConnector.saveDocument(round, Round.class)).thenReturn(round);
        round = roundService.save(round);
        assertNotNull(round);
    }

    @Test
    public void testDeleteRound() {
        Round round = RoundFixture.withDefaults();
        RoundQuery roundQuery = new RoundQuery(newArrayList(round));
        when(queryGenerator.getDocumentByAngelIdAndClass(round.angelId(), QueryGenerator.ROUND)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoundQuery.class, Round.FETCH_PLAN)).thenReturn(roundQuery);
        doNothing().when(serviceConnector).deleteDocument(round.rid().substring(1));
        roundService.delete(round.angelId());
        verify(serviceConnector, times(1)).deleteDocument(round.rid().substring(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteRoundInvalidAngelId() {
        Round round = RoundFixture.withDefaults();
        RoundQuery roundQuery = new RoundQuery(newArrayList(round));
        when(queryGenerator.getDocumentByAngelIdAndClass(1L, QueryGenerator.ROUND)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoundQuery.class, Round.FETCH_PLAN)).thenReturn(roundQuery);
        roundService.delete(round.angelId());
    }
}

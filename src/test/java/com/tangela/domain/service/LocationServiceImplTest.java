package com.tangela.domain.service;

import com.tangela.domain.model.Location;
import com.tangela.domain.model.LocationFixture;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.LocationQuery;
import com.tangela.domain.service.impl.LocationServiceImpl;
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
public class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl locationService;

    @Mock
    private OrientDBServiceConnector serviceConnector;

    @Mock
    private QueryGenerator queryGenerator;

    @Test
    public void testGetLocationByAngelId() {
        LocationQuery locationQuery = new LocationQuery(newArrayList(LocationFixture.cityWithDefaults()));
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.LOCATION)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", LocationQuery.class, Location.FETCH_PLAN)).thenReturn(locationQuery);
        Location location  = locationService.getByAngelId(100L);
        assertNotNull(location);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetLocationByInvalidAngelId() {
        LocationQuery locationQuery = new LocationQuery(newArrayList());
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.LOCATION)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", LocationQuery.class, Location.FETCH_PLAN)).thenReturn(locationQuery);
        Location location = locationService.getByAngelId(100L);
        assertNull(location);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetLocationByInvalidAngelIdAndReturnNull() {
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.LOCATION)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", LocationQuery.class, Location.FETCH_PLAN)).thenReturn(null);
        Location location = locationService.getByAngelId(100L);
        assertNull(location);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testUpdateLocation() {
        Location location = LocationFixture.cityWithDefaults();
        LocationQuery locationQuery = new LocationQuery(newArrayList(location));
        when(queryGenerator.getDocumentByAngelIdAndClass(location.angelId(), QueryGenerator.LOCATION)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", LocationQuery.class, Location.FETCH_PLAN)).thenReturn(locationQuery);
        when(serviceConnector.updateDocument(location.rid().substring(1), location, String.class)).thenReturn("{}");
        location = locationService.update(location);
        assertNotNull(location);
    }

    @Test
    public void testUpdateLocationWithInvalidAngelId() {
        Location location = LocationFixture.cityWithDefaults();
        LocationQuery locationQuery = new LocationQuery(newArrayList(location));
        when(queryGenerator.getDocumentByAngelIdAndClass(43L, QueryGenerator.LOCATION)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", LocationQuery.class, Location.FETCH_PLAN)).thenReturn(locationQuery);
        location = locationService.update(location);
        assertNull(location);
    }

    @Test
    public void testUpdateLocationWithInvalidJsonResponse() {
        String jsonResponse = "{";
        Location location = LocationFixture.cityWithDefaults();
        LocationQuery locationQuery = new LocationQuery(newArrayList(location));
        when(queryGenerator.getDocumentByAngelIdAndClass(location.angelId(), QueryGenerator.LOCATION)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", LocationQuery.class, Location.FETCH_PLAN)).thenReturn(locationQuery);
        when(serviceConnector.updateDocument(location.rid().substring(1), location, String.class)).thenReturn(jsonResponse);
        location = locationService.update(location);
        assertNull(location);
    }

    @Test
    public void testSaveLocation() {
        Location location = LocationFixture.cityWithDefaults();
        when(serviceConnector.saveDocument(location, Location.class)).thenReturn(location);
        location = locationService.save(location);
        assertNotNull(location);
    }

    @Test
    public void testDeleteLocation() {
        Location location = LocationFixture.cityWithDefaults();
        LocationQuery locationQuery = new LocationQuery(newArrayList(location));
        when(queryGenerator.getDocumentByAngelIdAndClass(location.angelId(), QueryGenerator.LOCATION)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", LocationQuery.class, Location.FETCH_PLAN)).thenReturn(locationQuery);
        doNothing().when(serviceConnector).deleteDocument(location.rid().substring(1));
        locationService.delete(location.angelId());
        verify(serviceConnector, times(1)).deleteDocument(location.rid().substring(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteLocationInvalidAngelId() {
        Location location = LocationFixture.cityWithDefaults();
        LocationQuery locationQuery = new LocationQuery(newArrayList(location));
        when(queryGenerator.getDocumentByAngelIdAndClass(1L, QueryGenerator.LOCATION)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", LocationQuery.class, Location.FETCH_PLAN)).thenReturn(locationQuery);
        locationService.delete(location.angelId());
    }
}

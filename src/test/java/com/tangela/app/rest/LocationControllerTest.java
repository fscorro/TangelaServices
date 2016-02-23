package com.tangela.app.rest;

import com.tangela.domain.model.Location;
import com.tangela.domain.model.LocationFixture;
import com.tangela.domain.service.impl.LocationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationControllerTest {

    @InjectMocks
    private LocationController locationController;

    @Mock
    private LocationServiceImpl locationService;

    @Test
    public void getLocationByAngelId(){
        when(locationService.getByAngelId(100L)).thenReturn(LocationFixture.cityWithDefaults());
        Location location = locationController.getLocationByAngelId(100L);
        assertNotNull(location);
    }

    @Test
    public void testSaveLocation(){
        Location location = LocationFixture.cityWithDefaults();
        when(locationService.save(location)).thenReturn(location);
        location = locationController.saveLocation(location);
        assertNotNull(location);
    }

    @Test
    public void testUpdateLocation(){
        Location location = LocationFixture.cityWithDefaults();
        when(locationService.update(location)).thenReturn(location);
        location = locationController.updateLocation(location.angelId(), location);
        assertNotNull(location);
    }

    @Test
    public void testDeleteLocation(){
        doNothing().when(locationService).delete(90L);
        locationController.deleteLocation(90L);
    }
}

package com.tangela.app.rest;

import com.tangela.app.rest.request.GetStartupsRequest;
import com.tangela.app.rest.response.GetStartupsResponse;
import com.tangela.domain.model.Startup;
import com.tangela.domain.model.StartupFixture;
import com.tangela.domain.service.StartupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StartupControllerTest {

    @InjectMocks
    private StartupController startupController;

    @Mock
    private StartupService startupService;

    @Test
    public void getStartups() {
        when(startupService.getStartups(newArrayList("Testing"), newArrayList("Vienna"), 1, null)).thenReturn(newArrayList(StartupFixture.withDefaults()));

        GetStartupsRequest request = new GetStartupsRequest("Testing", "Vienna", 1, null);
        GetStartupsResponse response = startupController.getStartups(request);

        assertNotNull(response);
        assertEquals(response.startups().size(), 1);
    }

    @Test
    public void getStartupsWithNullAndEmptyMarkets(){
        when(startupService.getStartups(null, newArrayList("Vienna"), 1, null)).thenReturn(newArrayList(StartupFixture.withDefaults()));

        GetStartupsRequest request = new GetStartupsRequest("", "Vienna", 1, null);
        GetStartupsResponse response = startupController.getStartups(request);

        assertNotNull(response);
        assertEquals(response.startups().size(), 1);

        request = new GetStartupsRequest(null, "Vienna", 1, null);
        response = startupController.getStartups(request);

        assertNotNull(response);
        assertEquals(response.startups().size(), 1);
    }

    @Test
    public void getStartupsWithNullAndEmptyLocations(){
        when(startupService.getStartups(newArrayList("Testing"), null, 1, null)).thenReturn(newArrayList(StartupFixture.withDefaults()));

        GetStartupsRequest request = new GetStartupsRequest("Testing", "", 1, null);
        GetStartupsResponse response = startupController.getStartups(request);

        assertNotNull(response);
        assertEquals(response.startups().size(), 1);

        request = new GetStartupsRequest("Testing", null, 1, null);
        response = startupController.getStartups(request);

        assertNotNull(response);
        assertEquals(response.startups().size(), 1);
    }

    @Test
    public void getStartupByAngelId(){
        when(startupService.getByAngelId(100L)).thenReturn(StartupFixture.withDefaults());
        Startup startup = startupController.getStartupByAngelId(100L);
        assertNotNull(startup);
    }

    @Test
    public void testSaveStartup(){
        Startup startup = StartupFixture.withDefaults();
        when(startupService.save(startup)).thenReturn(startup);
        startup = startupController.saveStartup(startup);
        assertNotNull(startup);
    }

    @Test
    public void testUpdateStartup(){
        Startup startup = StartupFixture.withDefaults();
        when(startupService.update(startup)).thenReturn(startup);
        startup = startupController.updateStartup(startup.angelId(), startup);
        assertNotNull(startup);
    }

    @Test
    public void testDeleteStartup(){
        doNothing().when(startupService).delete(90L);
        startupController.deleteStartup(90L);
    }
}

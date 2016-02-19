package com.tangela.domain.service;

import com.tangela.domain.model.Startup;
import org.joda.time.DateTime;

import java.util.List;

public interface StartupService extends ModelService<Startup> {

    List<Startup> getStartups(List<String> markets, List<String> locations, Integer quality, DateTime date);
}

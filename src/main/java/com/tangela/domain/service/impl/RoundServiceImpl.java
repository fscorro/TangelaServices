package com.tangela.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangela.domain.model.Round;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.RoundQuery;
import com.tangela.domain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RoundServiceImpl implements ModelService<Round> {

    @Autowired
    private OrientDBServiceConnector serviceConnector;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public Round getByAngelId(Long angelId) {
        String query = queryGenerator.getDocumentByAngelIdAndClass(angelId, QueryGenerator.ROUND);
        RoundQuery rounds = serviceConnector.query(query, RoundQuery.class, Round.FETCH_PLAN);
        if(rounds == null || rounds.rounds().size() < 1) {
            return null;
        }
        return rounds.rounds().get(0);
    }

    @Override
    public Round update(Round entity) {
        Round round = getByAngelId(entity.angelId());
        if(round == null) {
            return null;
        }
        String response = serviceConnector.updateDocument(round.rid().substring(1), updateRoundByRound(round, entity), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, Round.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Round save(Round entity) {
        return serviceConnector.saveDocument(entity, Round.class);
    }

    @Override
    public void delete(Long angelId) {
        Round round = getByAngelId(angelId);
        if(round == null) {
            throw new IllegalArgumentException();
        }
        serviceConnector.deleteDocument(round.rid().substring(1));
    }

    private Round updateRoundByRound(Round old, Round round) {
        old.setAngelId(round.angelId());
        old.setName(round.name());
        old.setRoundType(round.roundType());
        old.setRaised(round.raised());
        old.setRoundClosedAt(round.roundClosedAt());
        old.setRoundId(round.roundId());
        old.setRoundSourceUrl(round.roundSourceUrl());
        old.setParticipantName(round.participantName());
        old.setParticipantType(round.participantType());
        old.setParticipantId(round.participantId());
        return old;
    }
}

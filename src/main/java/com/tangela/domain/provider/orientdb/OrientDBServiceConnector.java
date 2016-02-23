package com.tangela.domain.provider.orientdb;

import com.tangela.domain.model.*;
import com.tangela.domain.provider.exception.OrientDBException;
import com.tangela.domain.provider.response.Database;
import com.tangela.domain.provider.response.StartupQuery;
import com.tangela.domain.provider.support.AuthRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class OrientDBServiceConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrientDBServiceConnector.class);

    private static final String CONNECT_COMMAND = "connect";
    private static final String DATABASE_COMMAND = "database";

    private static final String DOCUMENT_COMMAND = "document";
    private static final String DOCUMENT_UPDATE_MODE = "?updateMode=";
    private static final String DOCUMENT_UPDATE_MODE_PARTICAL = "partial";

    private static final String QUERY_LANGUAGE = "sql";
    private static final String QUERY_COMMAND = "query";
    private static final Integer QUERY_LIMIT = 30;

    @Value("${orientdb.url}")
    private String orientdbEndpoint;

    @Value("${orientdb.database}")
    private String database;

    @Autowired
    private AuthRestTemplate authRestTemplate;

    public void connect() {
        LOGGER.info("[connect] Connecting to OrientDB...");
        String uri = format("%s/%s/%s", orientdbEndpoint, CONNECT_COMMAND, database);
        ResponseEntity entity = authRestTemplate.get(uri, null);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new OrientDBException("Error connecting with database.");
        }
        LOGGER.info("[connect] connected with OrientDB.");
    }

    public Database database() {
        LOGGER.info("[database] Getting server database information...");
        String uri = format("%s/%s/%s", orientdbEndpoint, DATABASE_COMMAND, database);
        ResponseEntity<Database> entity = authRestTemplate.get(uri, Database.class);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new OrientDBException("Error getting server database information.");
        }
        LOGGER.info(entity.getBody().toString());
        LOGGER.info("[database] Got server database information.");
        return entity.getBody();
    }

    public <T> T query(String query, Class<T> clazz, String fetch) {
        LOGGER.info("[query] Executing query in database...");
        LOGGER.debug("[query] Executing query {}", query);
        String uri = format("%s/%s/%s/%s/%s/%d/%s", orientdbEndpoint, QUERY_COMMAND, database, QUERY_LANGUAGE, query, QUERY_LIMIT, fetch);
        ResponseEntity<T> entity = authRestTemplate.get(uri, clazz);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new OrientDBException("Error executing query in database.");
        }
        LOGGER.info(entity.getBody().toString());
        LOGGER.info("[query] Query executed in database.");
        return entity.getBody();
    }

    public <T> T getDocument(String rid, Class<T> clazz, String fetch) {
        String uri = format("%s/%s/%s/%s/%s", orientdbEndpoint, DOCUMENT_COMMAND, database, rid, fetch);
        ResponseEntity<T> entity = authRestTemplate.get(uri, clazz);
        if (!entity.getStatusCode().is2xxSuccessful()) {
            throw new OrientDBException("Error getting server database information.");
        }
        return entity.getBody();
    }

    public <T, U> U updateDocument(String rid, T entity, Class<U> clazz) {
        String uri = format("%s/%s/%s/%s/%s", orientdbEndpoint, DOCUMENT_COMMAND, database, rid,
                DOCUMENT_UPDATE_MODE + DOCUMENT_UPDATE_MODE_PARTICAL);
        ResponseEntity<U> response = authRestTemplate.put(uri, entity, clazz);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new OrientDBException("Error getting server database information.");
        }
        return response.getBody();
    }

    public <T, U> U saveDocument(T entity, Class<U> clazz) {
        String uri = format("%s/%s/%s/", orientdbEndpoint, DOCUMENT_COMMAND, database);
        ResponseEntity<U> response = authRestTemplate.post(uri, entity, clazz);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new OrientDBException("Error getting server database information.");
        }
        return response.getBody();
    }

    public void deleteDocument(String rid) {
        String uri = format("%s/%s/%s/%s", orientdbEndpoint, DOCUMENT_COMMAND, database, rid);
        ResponseEntity<String> response = authRestTemplate.delete(uri, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new OrientDBException("Error getting server database information.");
        }
    }
}

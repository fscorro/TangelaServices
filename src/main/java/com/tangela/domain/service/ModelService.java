package com.tangela.domain.service;

import com.tangela.domain.model.Startup;

public interface ModelService<T> {

    T getByAngelId(Long angelId);

    T update(T entity);

    T save(T entity);

    void delete(Long angelId);
}

package com.tangela.services.domain.Repository;

import java.util.List;

import com.orientechnologies.orient.core.id.ORID;

public interface Repository<T>
{
    /**
     * store the entity.
     */
    public T save(T instance);

    /**
     * Refresh the entity state.
     */
    public T refresh(T instance);

    /**
     * Delete the entity.
     *
     */
    public void delete(T instance);

    /**
     * Find the entity with the corresponding id.
     *
     * @param id
     *            The entity id
     * @return The entity
     */
    public T findById(ORID id);

    /**
     * Prepare a query to find entities.
     *
     * @param query SQL query
     * @param params Params to bind to the query
     * @return A result set
     */
    public List<T> find(String query, Object... params);

    /**
     * This creates a proxied object instance
     * @param clazz
     * @return
     */
    public T newInstance(Class<T> clazz);
}


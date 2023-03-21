package com.akdev.tacocloud.taco.data;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    Optional<E> findById(K id);

    List<E> findAll();

    E save(E entity);
}

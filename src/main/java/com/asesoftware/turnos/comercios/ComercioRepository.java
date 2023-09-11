package com.asesoftware.turnos.comercios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComercioRepository extends CrudRepository<Comercio, Long>, PagingAndSortingRepository<Comercio, Long> {
}

package com.asesoftware.turnos.comercios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


//marcamos la interfaz como un componente del repo
public interface ComercioRepository extends CrudRepository<Comercio, Long>, PagingAndSortingRepository<Comercio, Long> {
}

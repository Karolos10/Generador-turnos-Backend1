package com.asesoftware.turnos.servicios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServicioRepository extends CrudRepository<Servicio, Long>, PagingAndSortingRepository<Servicio, Long> {
}

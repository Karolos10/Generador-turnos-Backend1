package com.asesoftware.turnos.turnos;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TurnoRepository extends CrudRepository<Turno, Long>, PagingAndSortingRepository<Turno, Long> {


    //@Query(value = "CALL CREAR_TURNOS_POR_SERVICIO(:FECHA_INICIO_IN,:FECHA_FIN_IN,:ID_SERVICIO_IN);", nativeQuery = true)
    @Procedure(name = "CREAR_TURNOS_POR_SERVICIO")
    Long crearTurnosPorServicio(
            @Param("FECHA_INICIO_IN") Date fechaInicio,
            @Param("FECHA_FIN_IN") Date fechaFin,
            @Param("ID_SERVICIO_IN") Long idServicio);


    List<Turno> findAllByServicio_IdServicio(Long idServicio);



}

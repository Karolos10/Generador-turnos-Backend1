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

    /**
     * llama a un procedimiento almacenado llamado CREAR_TURNOS_POR_SERVICIO en la base de datos.
     */


    //@Query(value = "CALL CREAR_TURNOS_POR_SERVICIO(:FECHA_INICIO_IN,:FECHA_FIN_IN,:ID_SERVICIO_IN);", nativeQuery = true)

    //Este metodo devuelve un valor de tipo long
    @Procedure(name = "CREAR_TURNOS_POR_SERVICIO")
    Long crearTurnosPorServicio(
            //Mapeamos los parametros de entrada que son del procedimiento
            @Param("FECHA_INICIO_IN") Date fechaInicio,
            @Param("FECHA_FIN_IN") Date fechaFin,
            @Param("ID_SERVICIO_IN") Long idServicio);


    /**
     * Este método encuentra todos los turnos asociados a un servicio dado
     * utilizando el ID del servicio..
     */
    //Con este id buscaremos los turnos
    //Nos retorna la lsita de todos los turnos asociados con este id de servicio
    List<Turno> findAllByServicio_IdServicio(Long idServicio);



}

package com.asesoftware.turnos.turnos;


import com.asesoftware.turnos.servicios.Servicio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turnos")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "CREAR_TURNOS_POR_SERVICIO",
                procedureName = "SYSTEM.CREAR_TURNOS_POR_SERVICIO",
                parameters = {
                        //configuramos nuestros parametros de entradas y salidas para el pocedimiento
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="FECHA_INICIO_IN", type=Date.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="FECHA_FIN_IN", type=Date.class),
                        @StoredProcedureParameter(mode=ParameterMode.IN, name="ID_SERVICIO_IN", type=Long.class),
                        @StoredProcedureParameter(mode=ParameterMode.OUT, name="RESULT_OUT", type=Long.class)
                })
})
public class Turno {

    //Mapeamos las tablas para la base de datos
    @Id
    @SequenceGenerator(name="TURNOS_SEQUENCE", sequenceName="TURNOS_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TURNOS_SEQUENCE")
    @Column(name = "id_turno", nullable = false)
    private Long idTurno;


    @Column(name = "fecha_turno", nullable = false)
    private Date fechaTurno;

    @Column(name = "hora_inicio", nullable = false)
    private String horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private String horaFin;

    @Column(name = "estado", nullable = false)
    private String estado;

    //Indicamos que hay una relacion de muchos a uno y traemos el id_servicios como llave foranea a turnos
    @ManyToOne()
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;


}

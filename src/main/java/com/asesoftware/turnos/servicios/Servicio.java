package com.asesoftware.turnos.servicios;

import com.asesoftware.turnos.turnos.Turno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @SequenceGenerator(name="SERVICIOS_SEQUENCE", sequenceName="SERVICIOS_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERVICIOS_SEQUENCE")
    @Column(name = "id_servicio", nullable = false)
    private Long idServicio;

    @Column(name = "nombre_servicio", nullable = false)
    private String nombreServicio;

    @Column(name = "hora_apertura", nullable = false)
    private String horaApertura;

    @Column(name = "hora_cierre", nullable = false)
    private String horaCierre;

    @Column(name = "duracion", nullable = false)
    private String duracion;



}

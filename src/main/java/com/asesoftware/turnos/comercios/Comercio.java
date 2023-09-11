package com.asesoftware.turnos.comercios;


import com.asesoftware.turnos.servicios.Servicio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comercios")
public class Comercio {

    @Id
    @SequenceGenerator(name="COMERCIOS_SEQUENCE", sequenceName="COMERCIOS_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMERCIOS_SEQUENCE")
    @Column(name = "id_comercio", nullable = false)
    private Long idComercio;

    @Column(name = "nombre_comercio", nullable = false)
    private String nombreComercio;

    @Column(name = "aforo_maximo", nullable = false)
    private Long aforoMaximo;

    @OneToMany
    @JoinColumn(name = "id_comercio")
    private List<Servicio> servicios;
}

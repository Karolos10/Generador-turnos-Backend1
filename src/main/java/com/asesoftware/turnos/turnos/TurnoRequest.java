package com.asesoftware.turnos.turnos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoRequest {

    private Date fechaInicio;
    private Date fechaFin;
    private Long idServicio;
}

package com.asesoftware.turnos.turnos;

import com.asesoftware.turnos.servicios.ServicioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoRepository turnosRepository;
    private final ServicioRepository servicioRepository;

    //inyectamos en nuestro constructor las dependencias
    public TurnoController(TurnoRepository turnosRepository, ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
        this.turnosRepository = turnosRepository;
    }

    //Mapeamos las solicitudes http con el metodo post
    @PostMapping
    private ResponseEntity<List<Turno>> create(@RequestBody TurnoRequest turnoRequest) {

        //Creamos turnos basados en la solicitud
            Long result = turnosRepository.crearTurnosPorServicio(turnoRequest.getFechaInicio(),turnoRequest.getFechaFin(),turnoRequest.getIdServicio());
            //se proporciona un status 200 para que la operacion sea exitosa
            if(result == 200){
                //Busca y recupera la lista de turnos asociados al servicio
                List<Turno> turnos = turnosRepository.findAllByServicio_IdServicio(turnoRequest.getIdServicio());
                //Devolvemos la respuesta con la lista de los turnos con el estado 200
                return ResponseEntity.ok(turnos);
            }
            //Devolvemos nullo si no es exitosa
            return null;
    }

    //Metodo para obtener los datos
    @GetMapping("/{requestedId}")
    private ResponseEntity<Turno> findById(@PathVariable Long requestedId){
        //Buscamos el turno por el id en el repositorio
        Optional<Turno> turno = turnosRepository.findById(requestedId);
        //Verificamos si se encontro el objeto con un estado ok
        return turno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    private ResponseEntity<List<Turno>> findAll(Pageable pageable){
        //Estre metodo busca todos los turnos en el repositorio con la paginacion
        Page<Turno> page = turnosRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "idTurno"))));
        //Devolvemos la respuesta de la lista de los turnos en la pagina actual i con status 200
        return ResponseEntity.ok(page.getContent());
    }
}

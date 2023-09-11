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

    public TurnoController(TurnoRepository turnosRepository, ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
        this.turnosRepository = turnosRepository;
    }
    @PostMapping
    private ResponseEntity<List<Turno>> create(@RequestBody TurnoRequest turnoRequest) {

            Long result = turnosRepository.crearTurnosPorServicio(turnoRequest.getFechaInicio(),turnoRequest.getFechaFin(),turnoRequest.getIdServicio());
            if(result == 200){
                List<Turno> turnos = turnosRepository.findAllByServicio_IdServicio(turnoRequest.getIdServicio());
                return ResponseEntity.ok(turnos);
            }
            return null;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Turno> findById(@PathVariable Long requestedId){
        Optional<Turno> turno = turnosRepository.findById(requestedId);
        return turno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    private ResponseEntity<List<Turno>> findAll(Pageable pageable){
        Page<Turno> page = turnosRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "idTurno"))));
        return ResponseEntity.ok(page.getContent());
    }
}

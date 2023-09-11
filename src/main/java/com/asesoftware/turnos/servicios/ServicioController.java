package com.asesoftware.turnos.servicios;

import com.asesoftware.turnos.comercios.Comercio;
import com.asesoftware.turnos.comercios.ComercioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioRepository servicioRepository;

    public ServicioController(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }
    @PostMapping
    private ResponseEntity<Void> create(@RequestBody Servicio newServicioRequest) {
        Servicio savedServicio = servicioRepository.save(newServicioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Servicio> findById(@PathVariable Long requestedId){
        Optional<Servicio> servicio = servicioRepository.findById(requestedId);
        return servicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    private ResponseEntity<List<Servicio>> findAll(Pageable pageable){
        Page<Servicio> page = servicioRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "idServicio"))));
        return ResponseEntity.ok(page.getContent());
    }
}

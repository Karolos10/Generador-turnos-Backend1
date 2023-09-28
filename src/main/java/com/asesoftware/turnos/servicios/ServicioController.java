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

        //Creamos un nuevo servicio en la base de datos utilizando el repositorio
        Servicio savedServicio = servicioRepository.save(newServicioRequest);
        //Devolvemos respuesta ok status 201 de creado!
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Servicio> findById(@PathVariable Long requestedId){
        //Buscamos comercio por id
        Optional<Servicio> servicio = servicioRepository.findById(requestedId);
        //Si esta ok, devolvemos el objebto de comemercio con un status 200
        //Si no se encuentra devolvemos un status 400 de no encontrado
        return servicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Mapeamos solicitudes http get pero encontrando todos los objetos encontrados en la base
    @GetMapping()
    private ResponseEntity<List<Servicio>> findAll(Pageable pageable){
        //Con este metodo buscamos los servicios de la base de datos con paginacion y ordenamiento
        Page<Servicio> page = servicioRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "idServicio"))));
        //Devolvemos respuesta con la lista de los comercios encontrados
        return ResponseEntity.ok(page.getContent());
    }
}

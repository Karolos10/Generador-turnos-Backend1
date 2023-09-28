package com.asesoftware.turnos.comercios;


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
@RequestMapping("/comercios")
public class ComercioController {
    private final ComercioRepository comercioRepository;

    //Inyectamos el repositorio comercio
    public ComercioController(ComercioRepository comercioRepository) {
        this.comercioRepository = comercioRepository;
    }

    //Mapeamos las solicitudes http con el metodo post
    @PostMapping
    private ResponseEntity<Void> create(@RequestBody Comercio newComercioRequest) {
        //Creamos un nuevo comercio en la base de datos utilizando el repositorio
        Comercio savedComercio = comercioRepository.save(newComercioRequest);
        //Devolvemos respuesta ok status 201 de creado!
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Comercio> findById(@PathVariable Long requestedId){
        //Buscamos comercio por id
        Optional<Comercio> comercio = comercioRepository.findById(requestedId);
        //Si esta ok, devolvemos el objebto de comemercio con un status 200
        //Si no se encuentra devolvemos un status 400 de no encontrado
        return comercio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Mapeamos solicitudes http get pero encontrando todos los objetos encontrados en la base
    @GetMapping()
    private ResponseEntity<List<Comercio>> findAll(Pageable pageable){
        //Con este metodo buscamos los comercios en la base de datos con paginacion y ordenamiento
        Page<Comercio> page = comercioRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "idComercio"))));
        //Devolvemos respuesta con la lista de los comercios encontrados
        return ResponseEntity.ok(page.getContent());
    }

}

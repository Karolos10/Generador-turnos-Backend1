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

    public ComercioController(ComercioRepository comercioRepository) {
        this.comercioRepository = comercioRepository;
    }
    @PostMapping
    private ResponseEntity<Void> create(@RequestBody Comercio newComercioRequest) {
        Comercio savedComercio = comercioRepository.save(newComercioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Comercio> findById(@PathVariable Long requestedId){
        Optional<Comercio> comercio = comercioRepository.findById(requestedId);
        return comercio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    private ResponseEntity<List<Comercio>> findAll(Pageable pageable){
        Page<Comercio> page = comercioRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "idComercio"))));
        return ResponseEntity.ok(page.getContent());
    }

}

package org.iplacex.proyectos.discografia.discos;

import org.iplacex.proyectos.discografia.artistas.Artista;
import org.iplacex.proyectos.discografia.artistas.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> handlePostDiscoRequest(@RequestBody Disco disco) {
        if (artistaRepository.existsById(disco.idArtista)) {
            Disco savedDisco = discoRepository.save(disco);
            return new ResponseEntity<>(savedDisco, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Artista no encontrado", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> handleGetDiscosRequest() {
        List<Disco> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

   @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandleGetArtistaRequest(@PathVariable("id") String id){
        Optional<Disco> temp = discoRepository.findById(id);

        if(!temp.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(temp.get(), null, HttpStatus.OK);
    }

    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> handleGetDiscosByArtistaRequest(@PathVariable String id) {
        List<Disco> discos = discoRepository.findDiscosByIdArtista(id);
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

}

package org.iplacex.proyectos.discografia.artistas;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistaRepository extends MongoRepository<Artista, String>{}

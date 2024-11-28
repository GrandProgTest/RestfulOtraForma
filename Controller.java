package com.example.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoController {

    private static List<Curso> cursos = new ArrayList<>();

    // Listar todos los cursos
    @GET
    public List<Curso> listarCursos() {
        return cursos;
    }

    // Crear un curso
    @POST
    public Response crearCurso(Curso curso) {
        curso.setId(UUID.randomUUID().toString());
        cursos.add(curso);
        return Response.status(Response.Status.CREATED).entity(curso).build();
    }

    // Obtener un curso por ID
    @GET
    @Path("/{id}")
    public Response obtenerCurso(@PathParam("id") String id) {
        return cursos.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst()
                .map(curso -> Response.ok(curso).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}

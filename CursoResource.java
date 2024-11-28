package com.example.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoResource {

    private static List<Curso> cursos = new ArrayList<>();

    @GET
    public List<Curso> listarCursos() {
        return cursos;
    }

    @POST
    public Response crearCurso(Curso curso) {
        curso.setId(UUID.randomUUID().toString());
        cursos.add(curso);
        return Response.status(Response.Status.CREATED).entity(curso).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerCurso(@PathParam("id") String id) {
        return cursos.stream()
                .filter(curso -> curso.getId().equals(id))
                .findFirst()
                .map(curso -> Response.ok(curso).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Response actualizarCurso(@PathParam("id") String id, Curso data) {
        for (Curso curso : cursos) {
            if (curso.getId().equals(id)) {
                curso.setTitulo(data.getTitulo());
                curso.setDescripcion(data.getDescripcion());
                curso.setCategoria(data.getCategoria());
                curso.setPrecio(data.getPrecio());
                curso.setDuracion(data.getDuracion());
                curso.setCertificado(data.isCertificado());
                return Response.ok(curso).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarCurso(@PathParam("id") String id) {
        cursos.removeIf(curso -> curso.getId().equals(id));
        return Response.noContent().build();
    }
}

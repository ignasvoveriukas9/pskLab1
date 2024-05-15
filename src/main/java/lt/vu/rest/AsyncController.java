package lt.vu.rest;

import lombok.Getter;
import lt.vu.entities.Car;
import lt.vu.services.AsyncService;
import lt.vu.usecases.CarPartNameFinder.CarPartNameFinder;
import lt.vu.usecases.CarPartNameFinder.MockCarPartNameFinder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Path("/async")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsyncController {

        @Inject
        private AsyncService asyncService;

        @GET
        public Response GetInt() {
            CompletableFuture<Integer> future = ((AsyncService) asyncService).GetIntAsync();

            try {
                Integer result = future.get(); // Blokuoja, kol užduotis bus baigta
                return Response.ok(result).build();
            } catch (InterruptedException | ExecutionException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            }
        }
}

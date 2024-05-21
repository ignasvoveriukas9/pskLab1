package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.persistence.CarsDAO;
import lt.vu.rest.contracts.CarDTO;
import lt.vu.services.AsyncService;
import lt.vu.usecases.CarPartNameFinder.CarPartNameFinder;
import lt.vu.usecases.CarPartNameFinder.MockCarPartNameFinder;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
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
        @Getter @Setter
        private AsyncService asyncService;

        @Path("/{id}")
        @PUT
        public Response Update(@PathParam("id") final Integer id, @QueryParam("name") final String make) {
            CompletableFuture<Void> future = ((AsyncService) asyncService).updateCarAsync(id, make);

            return Response.ok(future.join()).build();
        }
}

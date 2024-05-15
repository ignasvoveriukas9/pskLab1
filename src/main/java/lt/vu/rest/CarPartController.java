package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CarPart;
import lt.vu.entities.Car;
import lt.vu.entities.Player;
import lt.vu.persistence.CarPartsDAO;
import lt.vu.persistence.CarsDAO;
import lt.vu.rest.contracts.CarDTO;
import lt.vu.rest.contracts.CarPartDTO;
import lt.vu.rest.contracts.PlayerDto;
import lt.vu.services.ModifyCarPart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
@Path("/carpart")
public class CarPartController {

    @Inject
    @Setter
    @Getter
    private CarPartsDAO carPartsDAO;

    @Inject
    @Getter @Setter
    private CarsDAO carsDAO;

    @Inject
    @Getter @Setter
    private ModifyCarPart modifyCarPart;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarPartById(@PathParam("id") final Integer id) {

        CarPart carPart = carPartsDAO.findOne(id);
        if (carPart == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CarPartDTO carPartDTO = new CarPartDTO();
        carPartDTO.setName(carPart.getName());

        List<CarDTO> carDTOS = new ArrayList<>();
        for(Car car : carPart.getCars()) {
            CarDTO carDTO = new CarDTO();
            carDTO.setId(car.getId());
            carDTO.setMake(car.getMake());
            carDTO.setModel(car.getModel());
            carDTOS.add(carDTO);
        }

        carPartDTO.setCars(carDTOS);

        return Response.ok(carPartDTO).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RequestScoped
    public Response createCarPart(@QueryParam("name") final String name) {

        System.out.println("Create car part: name = " + name);

        CarPart carPart = new CarPart();
        carPart.setName(name);

        carPartsDAO.persist(carPart);

        System.out.println(carPart.getId());

        return Response.created(URI.create("/carpart/" + carPart.getId())).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @RequestScoped
    public Response update(@PathParam("id") final Integer carPartId, CarPartDTO carPartDTO) {
        try {
            CarPart existingCarPart = carPartsDAO.findOne(carPartId);
            if (existingCarPart == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCarPart.setName(carPartDTO.getName());
            carPartsDAO.update(existingCarPart);

            List<Integer> carIds = new ArrayList<>();
            for (CarDTO carDTO : carPartDTO.getCars()) {
                carIds.add(carDTO.getId());
            }

            for(Car car : existingCarPart.getCars()) {
                if(!carIds.contains(car.getId())) {
                    car.getParts().remove(existingCarPart);
                    carsDAO.update(car);
                }
            }

            for(CarDTO carDTO : carPartDTO.getCars()) {
                Car car = carsDAO.findOne(carDTO.getId());
                if (car == null) {
                    return Response.status(Response.Status.NOT_FOUND).build();
                }

                if(!car.getParts().contains(existingCarPart)) {
                    car.getParts().add(existingCarPart);
                    carsDAO.update(car);
                }

            }

            return Response.ok(carPartDTO).build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }


    @Path("/OptLockSlow/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptLockSlow(@PathParam("id") final Integer id) {
        System.out.println("optLock called");
        try {
            CarPart part = modifyCarPart.modifyCarPartSlow(id, "slow");
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.ok().build();
    }

    @Path("/OptLockFast/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptLockFast(@PathParam("id") final Integer id) {
        System.out.println("optLock called");
        try {
            CarPart part = modifyCarPart.modifyCarPartFast(id, "fast");
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.ok().build();
    }
    //Implement the Endpoints
}

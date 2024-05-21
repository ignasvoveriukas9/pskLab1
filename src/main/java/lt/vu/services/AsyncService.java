package lt.vu.services;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.CarsDAO;
import lt.vu.rest.contracts.CarDTO;
import lt.vu.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.RequestContextController;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@ApplicationScoped
/*
public class AsyncService {

    @Inject
    @Getter @Setter
    CarsDAO carsDAO;

    @Inject
    @Getter @Setter
    EntityManager entityManager;

    private final ExecutorService executor = Executors.newFixedThreadPool(10);


    public CompletableFuture<CarDTO> updateCarAsync(int carId, String make, ExecutorService executor) {
        CarsDAO carsDAO = this.carsDAO;
        EntityManager entityManager = this.entityManager;

        return CompletableFuture.supplyAsync(() -> {
            RequestContextController requestContextController = CDI.current().select(RequestContextController.class).get();
            requestContextController.activate();
            try {
                Car car = carsDAO.findOne(carId);
                car.setMake(make);

                entityManager.merge(car);
                entityManager.flush();

                CarDTO carDTO = new CarDTO();
                carDTO.setMake(car.getMake());
                carDTO.setId(car.getId());
                carDTO.setModel(car.getModel());

                return carDTO;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            } finally {
                requestContextController.deactivate();
            }
        }, executor);
    }

 */
public class AsyncService {
    private ExecutorService executor;

    @Inject
    @Getter @Setter
    CarService carService;

    @Inject
    @Getter @Setter
    EntityManager entityManager;

    public AsyncService() {
        this.executor = Executors.newCachedThreadPool();
    }

    @Transactional
    public CompletableFuture<Void> updateCarAsync(int id, String newName) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000); // Simulate intensive work
                carService.updateCarName(id, newName);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return null;
        }, executor);

    }
}

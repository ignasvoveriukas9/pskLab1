package lt.vu.services;

import lt.vu.entities.Car;
import lt.vu.persistence.CarsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class CarService {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public void updateCarName(int id, String newName) {
        Car car = entityManager.find(Car.class, id);
        if (car != null) {
            car.setMake(newName);
            entityManager.merge(car);
            entityManager.flush();
        }
    }
}
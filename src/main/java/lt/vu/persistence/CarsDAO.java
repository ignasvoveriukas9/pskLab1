package lt.vu.persistence;

import lt.vu.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CarsDAO {
    @Inject
    private EntityManager em;

    public void persist(Car car){
        this.em.persist(car);
    }

    public Car findOne(Integer id){ return em.find(Car.class, id); }

    public Car update(Car car){
        return em.merge(car);
    }
}

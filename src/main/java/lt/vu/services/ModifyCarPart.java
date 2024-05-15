package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CarPart;

@ApplicationScoped
public class ModifyCarPart {

    @Inject
    @Getter @Setter
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public CarPart modifyCarPartSlow(int id, String name) throws InterruptedException {
        CarPart carPart = em.find(CarPart.class, id);

        Thread.sleep(5000);

        carPart.setName(name);
        try {
            em.merge(carPart);
            em.flush(); // This should trigger the OptimisticLockException if there's a conflict
        } catch (OptimisticLockException e) {
            System.out.println("Optimistic lock exception occurred: " + e.getMessage());
            throw e;
        }

        return carPart;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public CarPart modifyCarPartFast(int id, String name) {
        CarPart carPart = em.find(CarPart.class, id);

        carPart.setName(name);
        try {
            em.merge(carPart);
            em.flush(); // This should trigger the OptimisticLockException if there's a conflict
        } catch (OptimisticLockException e) {
            System.out.println("Optimistic lock exception occurred: " + e.getMessage());
            throw e;
        }

        return carPart;
    }
}
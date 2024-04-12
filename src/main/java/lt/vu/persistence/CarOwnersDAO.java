package lt.vu.persistence;

import lt.vu.entities.CarOwner;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CarOwnersDAO {

    @Inject
    EntityManager em;

    public List<CarOwner> loadAll() {
        return em.createNamedQuery("CarOwner.findAll", CarOwner.class).getResultList();
    }

    // Norint išvengti SQL injekcijos pavojaus, partialName naudojamas kaip vardizuotas parametras
    /*
    public List<Team> findTeamsByPartialName(String partialName) {
        String queryString = "SELECT t FROM Team t WHERE t.name LIKE :partialName";
        TypedQuery<Team> query = em.createQuery(queryString, Team.class);
        query.setParameter("partialName", "%" + partialName + "%");
        return query.getResultList();
    }
     */

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(CarOwner carOwner){
        this.em.persist(carOwner);
    }

    public CarOwner findOne(Integer id) {
        return em.find(CarOwner.class, id);
    }
}

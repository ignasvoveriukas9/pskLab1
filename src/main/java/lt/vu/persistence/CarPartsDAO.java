package lt.vu.persistence;

import lt.vu.entities.CarPart;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CarPartsDAO {

    @Inject
    EntityManager em;

    public List<CarPart> loadAll() {
        return em.createNamedQuery("CarParts.findAll", CarPart.class).getResultList();
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

    public void persist(CarPart carPart){
        this.em.persist(carPart);
    }

    public CarPart findOne(Integer id) {
        return em.find(CarPart.class, id);
    }
}

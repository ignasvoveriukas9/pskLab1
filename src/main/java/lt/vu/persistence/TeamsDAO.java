package lt.vu.persistence;

import lt.vu.entities.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class TeamsDAO {

    @Inject
    private EntityManager em;

    public List<Team> loadAll() {
        return em.createNamedQuery("Team.findAll", Team.class).getResultList();
    }

    // Norint išvengti SQL injekcijos pavojaus, partialName naudojamas kaip vardizuotas parametras
    public List<Team> findTeamsByPartialName(String partialName) {
        String queryString = "SELECT t FROM Team t WHERE t.name LIKE :partialName";
        TypedQuery<Team> query = em.createQuery(queryString, Team.class);
        query.setParameter("partialName", "%" + partialName + "%");
        return query.getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Team team){
        this.em.persist(team);
    }

    public Team findOne(Integer id) {
        return em.find(Team.class, id);
    }
}

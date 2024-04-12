package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CarOwner;
import lt.vu.persistence.CarOwnersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CarOwners {

    @Inject
    private CarOwnersDAO carOwnersDAO;

    @Getter @Setter
    private CarOwner carOwnerToCreate = new CarOwner();

    @Getter
    private List<CarOwner> allCarOwners;

    @PostConstruct
    public void init(){ loadAllCarOwners(); }

    @Transactional
    public void createCarOwner(){ this.carOwnersDAO.persist(carOwnerToCreate); }

    private void loadAllCarOwners(){ this.allCarOwners = carOwnersDAO.loadAll(); }
}

package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CarOwner;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarOwnersDAO;
import lt.vu.usecases.CarCalculator.CarCalculator;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Model
@LoggedInvocation
public class CarOwners {

    @Inject
    private CarOwnersDAO carOwnersDAO;

    @Inject
    private CarCalculator carCalcualtor;

    @Getter @Setter
    private CarOwner carOwnerToCreate = new CarOwner();

    @Getter
    private List<CarOwner> allCarOwners;

    @Getter
    private List<CarOwner> carOwnersWithMostCars;

    @PostConstruct
    public void init(){

        loadAllCarOwners();

        CarOwnersWithMostCars();
    }

    @Transactional
    public void createCarOwner(){ this.carOwnersDAO.persist(carOwnerToCreate); }

    private void loadAllCarOwners(){ this.allCarOwners = carOwnersDAO.loadAll(); }

    private void CarOwnersWithMostCars(){
        carOwnersWithMostCars = carCalcualtor.GetCarOwnerWithMostCars(allCarOwners);
    }
}

package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.CarOwner;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarOwnersDAO;
import lt.vu.persistence.CarsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class CarsForCarOwner implements Serializable {

    @Inject
    private CarOwnersDAO carOwnersDAO;

    @Inject
    private CarsDAO carsDAO;

    @Getter @Setter
    private CarOwner carOwner;

    @Getter @Setter
    private Car carToCreate = new Car();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carOwnerId = Integer.parseInt(requestParameters.get("carOwnerId"));
        this.carOwner = carOwnersDAO.findOne(carOwnerId);
    }

    @Transactional
    @LoggedInvocation
    public void createCar() {
        carToCreate.setOwner(this.carOwner);
        carsDAO.persist(carToCreate);
    }
}

package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.CarPart;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarOwnersDAO;
import lt.vu.persistence.CarPartsDAO;
import lt.vu.persistence.CarsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class CarDetails {

    @Inject
    private CarOwnersDAO carOwnersDAO;

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private CarPartsDAO carPartsDAO;

    @Getter @Setter
    private Car car;

    @Getter @Setter
    private CarPart carPartToCreate = new CarPart();

    @Getter @Setter
    private Integer toAddCarPartId;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carId = Integer.parseInt(requestParameters.get("carId"));
        this.car = carsDAO.findOne(carId);
    }

    @Transactional
    public String createPart(){
        this.carPartsDAO.persist(carPartToCreate);
        this.car.getParts().add(carPartToCreate);
        try{
            carsDAO.update(this.car);
        } catch (OptimisticLockException e) {
            return "/carDetails.xhtml?faces-redirect=true&carId=" + this.car.getId() + "&error=optimistic-lock-exception";
        }
        return "/carDetails.xhtml?faces-redirect=true&carId=" + this.car.getId();
    }

    @Transactional
    @LoggedInvocation
    public String updateCarPartsList() {

        CarPart carPartToAdd = carPartsDAO.findOne(toAddCarPartId);

        if(carPartToAdd != null){
            car.getParts().add(carPartToAdd);
        }

        try{
            carsDAO.update(this.car);
        } catch (OptimisticLockException e) {
            return "/carDetails.xhtml?faces-redirect=true&carId=" + this.car.getId() + "&error=optimistic-lock-exception";
        }
        return "/carDetails.xhtml?faces-redirect=true&carId=" + this.car.getId();
    }
}

package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.entities.CarPart;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarOwnersDAO;
import lt.vu.persistence.CarPartsDAO;
import lt.vu.persistence.CarsDAO;
import lt.vu.usecases.CarPartNameFinder.CarPartNameFinder;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Stereotype;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.lang.annotation.Documented;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Model
public class CarDetails {

    @Inject
    private CarOwnersDAO carOwnersDAO;

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private CarPartsDAO carPartsDAO;

    @Inject
    private CarPartNameFinder carPartNameFinder;

    @Getter @Setter
    private Car car;

    @Getter @Setter
    private CarPart carPartToCreate = new CarPart();

    @Getter @Setter
    private Integer toAddCarPartId;

    @Getter @Setter
    private Integer searchResult;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void search() {
        CompletableFuture.supplyAsync(() -> carPartNameFinder.findCarPartNameInList(car))
                .thenAcceptAsync(this::setSearchResult);
    }

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

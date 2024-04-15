package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.dao.CarPartCarMapper;
import lt.vu.mybatis.dao.CarPartMapper;
import lt.vu.mybatis.model.Car;
import lt.vu.mybatis.model.CarPart;
import lt.vu.mybatis.model.CarPartCar;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class CarDetailsMyBatis {

    @Inject
    private CarPartCarMapper carPartCarMapper;

    @Inject
    private CarMapper carMapper;

    @Inject
    private CarPartMapper carPartMapper;

    @Getter
    @Setter
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
        this.car = carMapper.selectByPrimaryKey(carId);
    }

    @Transactional
    public String createPart(){
        //this.carPartsDAO.persist(carPartToCreate);
        Integer carPartId = this.carPartMapper.insert(carPartToCreate);

        CarPartCar carPartCar = new CarPartCar();

        carPartCar.setCarId(car.getId());
        carPartCar.setCarPartId(carPartId);

       this.carPartCarMapper.insert(carPartCar);

        return "/carDetails.xhtml?faces-redirect=true&carId=" + this.car.getId();
    }

    @Transactional
    @LoggedInvocation
    public String updateCarPartsList() {

        CarPart carPartToAdd = carPartMapper.selectByPrimaryKey(toAddCarPartId);

        if(carPartToAdd != null){
            CarPartCar carPartCar = new CarPartCar();

            carPartCar.setCarId(car.getId());
            carPartCar.setCarPartId(toAddCarPartId);

            this.carPartCarMapper.insert(carPartCar);
        }

        return "/carDetails.xhtml?faces-redirect=true&carId=" + this.car.getId();
    }
}

package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.dao.CarOwnerMapper;
import lt.vu.mybatis.model.Car;
import lt.vu.mybatis.model.CarOwner;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class CarsForCarOwnerMyBatis {

    @Inject
    private CarOwnerMapper carOwnerMapper;

    @Inject
    private CarMapper carMapper;

    @Getter
    @Setter
    private CarOwner carOwner;

    @Getter @Setter
    private Car carToCreate = new Car();

    @Getter @Setter
    private List<Car> cars;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carOwnerId = Integer.parseInt(requestParameters.get("carOwnerId"));
        this.carOwner = carOwnerMapper.selectByPrimaryKey(carOwnerId);
        cars = carMapper.selectAllByCarOwner(carOwnerId);
    }

    @Transactional
    @LoggedInvocation
    public void createCar() {
        carToCreate.setCarOwner(this.carOwner);
        carToCreate.setCarownerId(this.carOwner.getId());
        carMapper.insert(carToCreate);
    }
}

package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CarOwnerMapper;
import lt.vu.mybatis.model.CarOwner;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CarOwnersMyBatis {
    @Inject
    private CarOwnerMapper carOwnerMapper;

    @Getter
    private List<CarOwner> allCarOwners;

    @Getter @Setter
    private CarOwner carOwnerToCreate = new CarOwner();

    @PostConstruct
    public void init() {
        this.loadAllCarOwners();
    }

    private void loadAllCarOwners() {
        this.allCarOwners = carOwnerMapper.selectAll();
    }

    @Transactional
    public String createCarOwner() {
        carOwnerMapper.insert(carOwnerToCreate);
        return "/myBatis/carOwners?faces-redirect=true";
    }
}

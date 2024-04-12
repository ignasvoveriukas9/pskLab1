package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.CarPart;
import lt.vu.persistence.CarPartsDAO;
import lt.vu.persistence.CarsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class PartDetails implements Serializable {

    @Inject
    private CarPartsDAO carPartsDAO;

    @Inject
    private CarsDAO carsDAO;

    @Getter @Setter
    private CarPart carPart;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carPartId = Integer.parseInt(requestParameters.get("partId"));
        this.carPart = carPartsDAO.findOne(carPartId);
    }
}



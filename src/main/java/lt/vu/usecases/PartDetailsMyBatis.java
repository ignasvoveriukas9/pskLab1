package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CarMapper;
import lt.vu.mybatis.dao.CarPartMapper;
import lt.vu.mybatis.model.CarPart;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class PartDetailsMyBatis {
    @Inject
    private CarPartMapper carPartMapper;

    @Inject
    private CarMapper carMapper;

    @Getter
    @Setter
    private CarPart carPart;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carPartId = Integer.parseInt(requestParameters.get("partId"));
        this.carPart = carPartMapper.selectByPrimaryKey(carPartId);
    }
}

package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CarPartDTO {

    private String name;

    private List<CarDTO> cars;
}

package lt.vu.usecases.CarCalculator;

import lt.vu.entities.CarOwner;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;

@Decorator
public abstract class CarCalculatorDecorator implements ICarCalculator {

    @Inject @Delegate @Any
    ICarCalculator carCalculator;

    public List<CarOwner> GetCarOwnerWithMostCars(List < CarOwner > carOwners ){
        List<CarOwner> list = carCalculator.GetCarOwnerWithMostCars(carOwners);

        if(list.size() > 1){
            System.out.println("There are more than one car owner with most cars");
        } else if (!list.isEmpty()) {
            System.out.println("There is one car owner with most cars");
        } else {
            System.out.println("There is no car owner with most cars");
        }

        return list;
    }
}

package lt.vu.usecases.CarCalculator;

import lt.vu.entities.CarOwner;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CarCalculator implements ICarCalculator {

    public List<CarOwner> GetCarOwnerWithMostCars( List < CarOwner > carOwners ) {

        CarOwner carOwnerWithMostCars = carOwners.get(0);
        for ( CarOwner carOwner : carOwners ) {
            if ( carOwner.getCars().size() > carOwnerWithMostCars.getCars().size() ) {
                carOwnerWithMostCars = carOwner;
            }
        }

        List<CarOwner> carOwnersWithMostCars = new ArrayList<>();
        carOwnersWithMostCars.add(carOwnerWithMostCars);

        return carOwnersWithMostCars;
    }
}

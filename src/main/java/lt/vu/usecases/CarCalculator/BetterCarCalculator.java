package lt.vu.usecases.CarCalculator;

import lt.vu.entities.CarOwner;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Specializes
public class BetterCarCalculator extends CarCalculator {
    public List<CarOwner> GetCarOwnerWithMostCars(List < CarOwner > carOwners ) {

        List<CarOwner> carOwnersWithMostCars = new ArrayList<>();

        for ( CarOwner carOwner : carOwners ) {

            if(carOwnersWithMostCars.isEmpty()){
                carOwnersWithMostCars.add(carOwner);
                continue;
            }

            if ( carOwner.getCars().size() > carOwnersWithMostCars.get(0).getCars().size() ) {
                carOwnersWithMostCars.clear();
                carOwnersWithMostCars.add(carOwner);
            } else if ( carOwner.getCars().size() == carOwnersWithMostCars.get(0).getCars().size()) {
                carOwnersWithMostCars.add(carOwner);
            }
        }

        return carOwnersWithMostCars;
    }
}

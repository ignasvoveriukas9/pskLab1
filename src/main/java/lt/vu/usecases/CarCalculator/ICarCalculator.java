package lt.vu.usecases.CarCalculator;

import lt.vu.entities.CarOwner;

import java.util.List;

public interface ICarCalculator {

    public List<CarOwner> GetCarOwnerWithMostCars(List < CarOwner > carOwners );
}

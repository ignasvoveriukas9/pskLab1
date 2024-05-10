package lt.vu.usecases.CarPartNameFinder;


import lt.vu.entities.Car;
import lt.vu.entities.CarPart;
import lt.vu.persistence.CarPartsDAO;
import lt.vu.persistence.CarsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class ProdCarPartNameFinder implements CarPartNameFinder {

    @Inject
    private CarPartsDAO carPartDAO;
    @Inject
    private CarsDAO carDAO;

    // Constructor, getters, and setters for carPartDAO and carDAO

    public Integer findCarPartNameInList(Car car) {
        Set<Integer> carIdsWithMatchingParts = new HashSet<>();

        // Iterate over each part of the provided car
        for (CarPart carPart : car.getParts()) {
            List<Integer> carIds = new ArrayList<Integer>();

            // Get the list of cars associated with the current car part
            for (Car carID : carPartDAO.findOne(carPart.getId()).getCars()) {
                carIds.add(carID.getId());
            }

            // Add the car IDs to the set of car IDs with matching parts
            carIdsWithMatchingParts.addAll(carIds);
        }

        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }

        return carIdsWithMatchingParts.size();
    }

}

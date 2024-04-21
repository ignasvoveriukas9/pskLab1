package lt.vu.usecases;


import lt.vu.entities.Car;
import lt.vu.entities.CarPart;
import lt.vu.persistence.CarPartsDAO;
import lt.vu.persistence.CarsDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.Set;

@SessionScoped
public class CarPartNameFinder implements Serializable {
/*
    public int findCarPartNameInList(Car car) {
        Set<Integer> carIdsWithMatchingParts = new HashSet<>();

        // Iterate over each part of the provided car
        for (CarPart carPart : car.getParts()) {
            // Get the list of cars associated with the current car part
            List<Integer> carIds = new ArrayList<Integer>();
            for( Car carId: carPart.getCars()){
                carIds.add(carId.getId());
            }
            // Add the car IDs to the set of car IDs with matching parts
            carIdsWithMatchingParts.addAll(carIds);
        }

        // Return the number of unique car IDs with matching parts
        return carIdsWithMatchingParts.size();
    }
    */

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

        return carIdsWithMatchingParts.size();
    }

}

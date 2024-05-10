package lt.vu.usecases.CarPartNameFinder;

import lt.vu.entities.Car;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class MockCarPartNameFinder implements CarPartNameFinder {

    public Integer findCarPartNameInList(Car car) {

        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }

        return 100;
    }
}

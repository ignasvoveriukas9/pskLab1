package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private String make;

    @Basic(optional = false)
    @Column(name="CAR_MODEL")
    private String model;

    @ManyToOne
    @JoinColumn(name="CAROWNER_ID")
    private CarOwner owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(make, car.make) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model);
    }

    @ManyToMany
    @JoinTable(
            name = "CARPART_CAR",
            joinColumns = { @JoinColumn(name = "CAR_ID")},
            inverseJoinColumns = { @JoinColumn(name = "CARPART_ID")}
    )
    private List<CarPart> parts;

}

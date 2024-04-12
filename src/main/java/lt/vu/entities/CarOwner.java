package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "CarOwner.findAll", query = "SELECT co FROM CarOwner co")
@Getter @Setter
public class CarOwner {
    @Id
    @GeneratedValue
    private Integer id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String surname;

    @Basic
    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarOwner owner = (CarOwner) o;
        return (Objects.equals(name, owner.name) && Objects.equals(surname, owner.surname) && Objects.equals(phoneNumber, owner.phoneNumber));
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, surname, phoneNumber);
    }

}

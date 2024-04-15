package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "CarParts.findAll", query = "select a from CarPart as a")
})
@Getter @Setter
public class CarPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Basic(optional = false)
    private String name;


    @ManyToMany(mappedBy = "parts")
    private List<Car> cars;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarPart carPart = (CarPart) o;
        return (Objects.equals(id, carPart.id) && Objects.equals(name, carPart.name));
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}

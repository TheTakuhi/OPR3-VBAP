package osu.damek.usedcars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tag")
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(name = "text", nullable = false)
    private String text;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_user",
            foreignKey = @ForeignKey(
                    name = "user_tag_fk"
            )
    )
    private User user;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            mappedBy = "tags"
    )
    private Set<Car> cars;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(
            mappedBy = "tags"
    )
    private Set<Motorcycle> motorcycles;

    public void removeCar(Car car) {
        cars.removeIf(n -> n.getId().equals(car.getId()));
    }

    public void removeMotorcycle(Motorcycle motorcycle) {
        motorcycles.removeIf(n -> n.getId().equals(motorcycle.getId()));
    }
}

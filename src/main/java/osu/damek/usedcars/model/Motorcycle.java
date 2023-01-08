package osu.damek.usedcars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Motorcycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motorcycle_id")
    private Long motorcycleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tag_car",
            joinColumns = @JoinColumn(name = "motorcycle_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    private String brand;
    private String type;
    private Double price;
    private String imageUrl;
    private String description;

    private Double yearOfManufacture;
    private Double tachometer;
    private Double performance;
    private Double consumption;
    private EFuel fuel;

    public User getUser() {
        return user;
    }

    public void update(Motorcycle newMotorcycle) {
        setBrand(newMotorcycle.getBrand());
        setType(newMotorcycle.getType());
        setPrice(newMotorcycle.getPrice());
        setImageUrl(newMotorcycle.getImageUrl());
        setDescription(newMotorcycle.getDescription());
        setYearOfManufacture(newMotorcycle.getYearOfManufacture());
        setTachometer(newMotorcycle.getTachometer());
        setPerformance(newMotorcycle.getPerformance());
        setConsumption(newMotorcycle.getConsumption());
        setFuel(newMotorcycle.getFuel());
    }
}

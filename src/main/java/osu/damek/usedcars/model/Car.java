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
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "tag_car",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;

    private String brand;
    private String type;
    private Double price;
    private String imageUrl;
    private String description;

    private String bodywork;
    private Double yearOfManufacture;
    private Double tachometer;
    private Double performance;
    private Double consumption;
    private Double capacity;
    private EFuel fuel;

    public User getUser() {
        return user;
    }

    public void update(Car newCar) {
        setBrand(newCar.getBrand());
        setType(newCar.getType());
        setPrice(newCar.getPrice());
        setImageUrl(newCar.getImageUrl());
        setDescription(newCar.getDescription());
        setBodywork(newCar.getBodywork());
        setYearOfManufacture(newCar.getYearOfManufacture());
        setTachometer(newCar.getTachometer());
        setPerformance(newCar.getPerformance());
        setConsumption(newCar.getConsumption());
        setCapacity(newCar.getCapacity());
        setFuel(newCar.getFuel());
    }
}

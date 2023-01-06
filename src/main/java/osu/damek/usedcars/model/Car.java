package osu.damek.usedcars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String brand;

    public Car(Long carId, String text){
        this.carId = carId;
        this.brand = text;
    }

    public boolean isItemEmpty(Car car) {
        return car.getBrand() == null || car.getBrand().equals("");
    }

    public User getUser() {
        return user;
    }

    public void update(Car newCar) {
        setBrand(newCar.getBrand());
    }
}

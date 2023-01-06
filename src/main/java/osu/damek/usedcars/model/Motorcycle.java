package osu.damek.usedcars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Motorcycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long motorcycleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String brand;

    public Motorcycle(Long motorcycleId, String text){
        this.motorcycleId = motorcycleId;
        this.brand = text;
    }

    public boolean isMotorcycleEmpty(Motorcycle motorcycle) {
        return motorcycle.getBrand() == null || motorcycle.getBrand().equals("");
    }

    public User getUser() {
        return user;
    }

    public void update(Motorcycle newMotorcycle) {
        setBrand(newMotorcycle.getBrand());
    }
}

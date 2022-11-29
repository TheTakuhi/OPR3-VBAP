package osu.damek.usedcars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "car")
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(name = "brand_car", nullable = false)
    private String brand;
    @Column(name = "type_car", nullable = false)
    private String type;
    @Column(name = "price_car", nullable = false)
    private Double price;
    @Column(name = "image_url_car", nullable = false)
    private String imageUrl;
    @Column(name = "description_car", nullable = false)
    private String description;
//    @Column(name = "bodywork_car", nullable = false)
//    private String bodywork;
//    @Column(name = "year_car", nullable = false)
//    private Date yearOfManufacture;
//    @Column(name = "tachometer_car", nullable = false)
//    private Integer tachometer;
//    @Column(name = "performance_car", nullable = false)
//    private Double performance;
//    @Column(name = "consumption_car", nullable = false)
//    private Double consumption;
//    @Column(name = "capacity_car", nullable = false)
//    private Double capacity;
//    @Enumerated(EnumType.STRING)
//    @Column(name = "fuel_car", nullable = false)
//    private Fuel fuel;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tag_car",
            joinColumns = @JoinColumn(name = "id_car"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;
    @ManyToOne
    @JoinColumn(
            name = "id_user",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "user_car_fk"
            )
    )
    private User user;
}

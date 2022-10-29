package osu.damek.usedcars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String brand;
    private String type;
    private String bodywork;
    private Date yearOfManufacture;
    private Integer tachometer;
    private Double price;
    private Double performance;
    private Double consumption;
    private Double capacity;
    private Fuel fuel;
    private String imageUrl;
    private String description;
}

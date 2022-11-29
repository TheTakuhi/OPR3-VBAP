package osu.damek.usedcars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "motorcycle")
@Table(name = "motorcycles")
public class Motorcycle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(name = "brand_motorcycle", nullable = false)
    private String brand;
    @Column(name = "type_motorcycle", nullable = false)
    private String type;
    @Column(name = "price_motorcycle", nullable = false)
    private Double price;
    @Column(name = "image_url_motorcycle", nullable = false)
    private String imageUrl;
    @Column(name = "description_motorcycle", nullable = false)
    private String description;
}

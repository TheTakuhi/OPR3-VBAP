package osu.damek.usedcars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tag_motorcycle",
            joinColumns = @JoinColumn(name = "id_motorcycle"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;
    @ManyToOne
    @JoinColumn(
            name = "id_user",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "user_motorcycle_fk"
            )
    )
    private User user;
}

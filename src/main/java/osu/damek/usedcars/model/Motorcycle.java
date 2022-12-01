package osu.damek.usedcars.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    public Motorcycle(String brand, String type, Double price, String imageUrl, String description, User user, List<Tag> tags) {
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.user = user;
        this.tags = tags;
    }

    public Motorcycle(String brand, User user){
        this.brand = brand;
        this.user = user;
        this.tags = new ArrayList<>();
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
}

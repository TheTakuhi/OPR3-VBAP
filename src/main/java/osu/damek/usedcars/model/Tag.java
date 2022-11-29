package osu.damek.usedcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_user",
            foreignKey = @ForeignKey(
                    name = "user_tag_fk"
            )
    )
    private User user;
    @ManyToMany(
            mappedBy = "tags",
            fetch = FetchType.LAZY
    )
    private List<Car> cars;
    @ManyToMany(
            mappedBy = "tags",
            fetch = FetchType.LAZY
    )
    private List<Motorcycle> motorcycles;
}

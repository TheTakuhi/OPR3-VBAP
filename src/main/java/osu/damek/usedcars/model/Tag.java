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
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(
            mappedBy = "tags"
    )
    private Set<Car> cars;

    @ManyToMany(
            mappedBy = "tags"
    )
    private Set<Motorcycle> motorcycles;

    private String text;

    public User getUser() {
        return user;
    }

    public void update(Tag newTag) {
        setText((newTag.getText()));
    }
}

package osu.damek.usedcars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "tags"
    )
    @JsonIgnore
    private Set<Car> cars;

    @ManyToMany(
            mappedBy = "tags",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Motorcycle> motorcycles;

    private String text;

    public User getUser() {
        return user;
    }

    public void update(Tag newTag) {
        setText((newTag.getText()));
    }
}

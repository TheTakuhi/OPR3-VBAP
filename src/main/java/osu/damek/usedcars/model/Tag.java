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

    private String text;

    public Tag(Long tagId, String text){
        this.tagId = tagId;
        this.text = text;
    }

    public boolean isTagEmpty(Tag tag) {
        return tag.getText() == null || tag.getText().equals("");
    }

    public User getUser() {
        return user;
    }

    public void update(Tag newTag) {
        setText((newTag.getText()));
    }
}

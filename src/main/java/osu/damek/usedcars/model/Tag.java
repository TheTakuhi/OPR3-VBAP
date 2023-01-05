package osu.damek.usedcars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String text;

    public Tag(Long tagId, String text){
        this.tagId = tagId;
        this.text = text;
    }

    public boolean isItemEmpty(Tag tag) {
        return tag.getText() == null || tag.getText().equals("");
    }

    public void update(Tag newTag) {
        setText((newTag.getText()));
    }
}

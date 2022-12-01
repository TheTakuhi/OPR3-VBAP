package osu.damek.usedcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role roleU;
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private List<Tag> tags;
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private List<Car> cars;
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private List<Motorcycle> motorcycles;

    public User(String username, String password, Role roleU) {
        this.username = username;
        this.password = password;
        this.roleU = roleU;
        this.tags = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.motorcycles = new ArrayList<>();
    }

    public User(Long id, String username, String password){
        setId(id);
        this.username = username;
        this.password = password;
        this.roleU = Role.USER;
    }

    public User(Long id, String username, String password, Role roleU){
        setId(id);
        this.username = username;
        this.password = password;
        this.roleU = roleU;
    }
}

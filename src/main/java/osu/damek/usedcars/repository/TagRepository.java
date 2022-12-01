package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Motorcycle;
import osu.damek.usedcars.model.Tag;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> getTagById(Long id);
    List<Tag> getAllByUserId(Long userId);

    @Modifying
    @Query("delete from tag t where t.cars is empty and t.motorcycles is empty")
    void removeAllUnused();
}

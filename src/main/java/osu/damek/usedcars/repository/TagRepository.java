package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTagId(Long tagId);
}

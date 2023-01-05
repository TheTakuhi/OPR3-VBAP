package osu.damek.usedcars.service;

import org.springframework.http.ResponseEntity;
import osu.damek.usedcars.model.Tag;

public interface TagService {
    Boolean existsByTagId(Long tagId);

    Tag findTagByTagId(Long tagId);

    ResponseEntity<Object> getAllTags();

    ResponseEntity<Object> getAllTagsByUserId(Long userId);

    ResponseEntity<Object> getTagById(Long tagId);

    ResponseEntity<Object> addTag(Tag tag, Long userId);

    ResponseEntity<Object> updateTag(Tag tag, Long tagId);

    ResponseEntity<Object> deleteTag(Long tagId);
}

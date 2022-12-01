package osu.damek.usedcars.service;

import osu.damek.usedcars.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
    List<Tag> getAllByUserId(Long userId);
    Tag getTagById(Long id);
    Tag addTag(Tag tag);
    Tag updateTag(Tag tag);
    void deleteTag(Long id);
    void deleteByUserId(Long userId);
    void removeUnused();
}

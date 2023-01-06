package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.model.Tag;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.TagRepository;
import osu.damek.usedcars.service.TagService;
import osu.damek.usedcars.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImp implements TagService {
    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private UserService userService;

    @Override
    public Boolean existsByTagId(Long tagId) {
        return tagRepository.existsById(tagId);
    }

    @Override
    public Tag findTagByTagId(Long tagId) {
        return tagRepository.findByTagId(tagId);
    }

    @Override
    public ResponseEntity<Object> getAllTags() {
        List<Tag> ret = tagRepository.findAll();
        ret.forEach(tag -> {
            tag.getUser().setTags(null);
            tag.getUser().setCars(null);
            tag.getUser().setMotorcycles(null);
            tag.setCars(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getAllTagsByUserId(Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        User user = userService.getUserById(userId);
        List<Tag> tags = tagRepository.findAll();

        List<Tag> ret = new ArrayList<>();
        tags.forEach(tag -> {
            tag.getUser().setTags(null);
            tag.getUser().setCars(null);
            tag.getUser().setMotorcycles(null);
            if (tag.getUser().getUserId().equals(user.getUserId()))
                ret.add(tag);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getTagById(Long tagId) {
        if (!tagRepository.existsById(tagId))
            return ResponseEntity.notFound().build();

        Tag ret = tagRepository.findByTagId(tagId);
        ret.setUser(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> addTag(Tag tag, Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        User user = userService.getUserById(userId);
        tag.setUser(user);
        tagRepository.save(tag);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> updateTag(Tag newTag, Long tagId) {
        if (!tagRepository.existsById(tagId))
            return ResponseEntity.notFound().build();

        Tag tag = tagRepository.findByTagId(tagId);
        tag.update(newTag);
        tagRepository.save(tag);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteTag(Long tagId) {
        if (!tagRepository.existsById(tagId))
            return ResponseEntity.notFound().build();

        Tag tag = tagRepository.findByTagId(tagId);
        tag.setUser(null);
        tagRepository.delete(tag);

        return ResponseEntity.ok().build();
    }
}

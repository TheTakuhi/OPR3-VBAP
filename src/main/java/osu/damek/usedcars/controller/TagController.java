package osu.damek.usedcars.controller;

import osu.damek.usedcars.model.Tag;
import osu.damek.usedcars.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags/")
@CrossOrigin
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("all")
    ResponseEntity<Object> getAll() {
        return tagService.getAllTags();
    }

    @GetMapping("all/{userId}")
    ResponseEntity<Object> getAllByUserId(@PathVariable("userId") Long userId) {
        return tagService.getAllTagsByUserId(userId);
    }

    @GetMapping("get/{tagId}")
    ResponseEntity<Object> getTagById(@PathVariable("tagId") Long tagId) {
        return tagService.getTagById(tagId);
    }

    @PostMapping(value = "add/{userId}")
    ResponseEntity<Object> addTag(@RequestBody Tag tag, @PathVariable("userId") Long userId) {
        return tagService.addTag(tag, userId);
    }

    @PutMapping(value = "edit/{tagId}")
    ResponseEntity<Object> updateTag(@RequestBody Tag newTag, @PathVariable("tagId") Long tagId) {
        return tagService.updateTag(newTag, tagId);
    }

    @DeleteMapping(value = "delete/{tagId}")
    ResponseEntity<Object> deleteTag(@PathVariable("tagId") Long tagId) {
        return tagService.deleteTag(tagId);
    }
}

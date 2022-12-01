package osu.damek.usedcars.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import osu.damek.usedcars.model.Tag;
import osu.damek.usedcars.serviceImp.TagServiceImp;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagServiceImp tagService;

    public TagController(TagServiceImp tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tag>> getAllTags(){
        List<Tag> tags = tagService.findAllTags();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") Long id){
        Tag tag = tagService.findTagById(id);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag){
        Tag newTag = tagService.addTag(tag);
        return new ResponseEntity<>(newTag, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag){
        Tag updateTag = tagService.updateTag(tag);
        return new ResponseEntity<>(updateTag, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable("id") Long id){
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
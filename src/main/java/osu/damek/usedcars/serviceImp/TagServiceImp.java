package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.NotFoundException;
import osu.damek.usedcars.model.Tag;
import osu.damek.usedcars.repository.TagRepository;

import java.util.List;

@Service
public class TagServiceImp {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImp(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag addTag(Tag tag){
        return tagRepository.save(tag);
    }

    public List<Tag> findAllTags(){
        return tagRepository.findAll();
    }

    public Tag updateTag(Tag tag){
        return tagRepository.save(tag);
    }

    public Tag findTagById(Long id){
        return tagRepository.findTagById(id).orElseThrow(() -> new NotFoundException("Tag with id: " + id + " was not found"));
    }

    public void deleteTag(Long id){
        tagRepository.deleteTagById(id);
    }
}

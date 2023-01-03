package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.NotFoundException;
import osu.damek.usedcars.model.Tag;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.TagRepository;
import osu.damek.usedcars.service.TagService;
import osu.damek.usedcars.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagServiceImp implements TagService {
    private final TagRepository tagRepository;
    private final UserService userService;

    @Autowired
    public TagServiceImp(TagRepository tagRepository, UserService userService) {
        this.tagRepository = tagRepository;
        this.userService = userService;
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id){
        return tagRepository.getTagById(id).orElseThrow(() -> new NotFoundException("Tag with id: " + id + " was not found"));
    }

    public List<Tag> getAllByUserId(Long userId) {
        return tagRepository.getAllByUserId(userId);
    }

    public Tag addTag(Tag tag){
//        User currentUser = userService.getCurrentUser();
//        tag.setUser(currentUser);
//        currentUser.getTags().add(tag);
        tag.setUser(tag.getUser());
        return tagRepository.save(tag);
    }

    public Tag updateTag(Tag tag){
        tag.setText(tag.getText());
        return tagRepository.save(tag);
    }

    public void deleteTag(Long id){
        Tag tag = getTagById(id);
        tag.getCars().forEach(car -> car.removeTag(tag));
        tag.getMotorcycles().forEach(motorcycle -> motorcycle.removeTag(tag));
        tagRepository.delete(tag);
    }

    public void deleteByUserId(Long userId) {
        getAllByUserId(userId)
                .forEach(tag -> deleteTag(tag.getId()));
    }

    @Transactional
    public void removeUnused() {
        tagRepository.removeAllUnused();
        /*
        getAllByUserId(userService.getCurrentUser().getId())
                .stream()
                .filter(tag -> tag.getNotes().isEmpty() && tag.getNoteGroups().isEmpty())
                .forEach(tagRepository::delete);
        */
    }
}

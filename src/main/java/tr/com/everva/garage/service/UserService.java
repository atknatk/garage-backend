package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.exception.UserNotFoundException;
import tr.com.everva.garage.model.dto.account.BasicUserDto;
import tr.com.everva.garage.model.dto.account.UserDto;
import tr.com.everva.garage.model.entity.Gallery;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.UserRepository;
import tr.com.everva.garage.util.ContextUtils;
import tr.com.everva.garage.util.GalleryContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findByPhoneList(List<String> phoneNumberList) {
        return userRepository.findByPhoneList(phoneNumberList).parallelStream().map(UserDto::new).collect(Collectors.toList());
    }

    public boolean checkOwnGallery(String galleryId) {
        String currentUser = ContextUtils.getCurrentUser().getId();
        Optional<Integer> integer = userRepository.checkOwnGallery(currentUser, galleryId);
        return integer.isPresent();
    }

    public void assignGallery(String userId, Gallery gallery) {
        Optional<User> byId = userRepository.findByIdFetchGallery(userId);
        User user = byId.orElseThrow(() -> {
            throw new UserNotFoundException(userId);
        });
        if (gallery.getUsers() == null) gallery.setUsers(new ArrayList<>());
        gallery.getUsers().add(user);
        user.getGalleries().add(gallery);
        userRepository.save(user);
    }

    public UserDto createPassiveUser(String phone, Gallery gallery) {
        User user = new User();
        user.setActive(false);
        user.setDeleted(false);
        List<Gallery> galleries = new ArrayList<>();
        galleries.add(gallery);
        user.setGalleries(galleries);
        user.setPhone(phone);
        user.setName("");
        User saved = userRepository.save(user);
        return new UserDto(saved);
    }

    public UserDto findById(String id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return new UserDto(byId.get());
        }
        throw new UserNotFoundException(id);
    }
}

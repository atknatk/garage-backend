package tr.com.everva.garage.service;

import org.springframework.stereotype.Service;
import tr.com.everva.garage.exception.UserNotFoundException;
import tr.com.everva.garage.model.dto.account.UserDto;
import tr.com.everva.garage.model.entity.Gallery;
import tr.com.everva.garage.model.entity.User;
import tr.com.everva.garage.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @PersistenceContext
    public EntityManager entityManager;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findByPhoneList(List<String> phoneNumberList) {
        return  userRepository.findByPhoneList(phoneNumberList).parallelStream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto createPassiveUser(String phone, String galleryId) {
        User user = new User();
        user.setActive(false);
        user.setDeleted(false);
        List<Gallery> galleries = new ArrayList<>();
        galleries.add(new Gallery(galleryId));
        user.setGalleries(galleries);
        user.setPhone(phone);
        User saved = userRepository.save(user);
        return new UserDto(saved);
    }

    public UserDto findById(String id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            return new UserDto(byId.get());
        }
        throw new UserNotFoundException(id);
    }
}

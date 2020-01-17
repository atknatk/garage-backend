package tr.com.everva.garage.model.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tr.com.everva.garage.model.dto.gallery.GalleryCreateDto;
import tr.com.everva.garage.model.dto.gallery.GalleryDto;
import tr.com.everva.garage.model.entity.User;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
public class UserDto implements UserDetails {

    @JsonProperty("i")
    private String id;

    @JsonProperty("t")
    private GalleryDto galleryDto;

    @NotNull
    @JsonProperty("n")
    private String name;

    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private boolean accountNonExpired = true;

    @JsonIgnore
    private boolean accountNonLocked = true;

    @JsonIgnore
    private boolean credentialsNonExpired = true;

    @JsonIgnore
    private boolean enabled = true;

    public UserDto() {
    }

    public UserDto(User user) {
        setId(user.getId());
        setName(user.getName());
        if (user.getGallery() != null) {
            GalleryDto galleryDto = new GalleryDto();
            galleryDto.setId(user.getGallery().getId());
           // TODO fix galleryDto.setGalleryName(user.getGallery().getGalleryName());
            setGalleryDto(galleryDto);
        } else if (user.getGalleryId() != null) {
            GalleryDto galleryDto = new GalleryDto();
            galleryDto.setId(user.getGalleryId());
            setGalleryDto(galleryDto);
        }
    }

    public UserDto(UserGalleryDto userGalleryDto) {
        setId(userGalleryDto.getId());
        if (userGalleryDto.getGalleryId() != null && !userGalleryDto.getGalleryId().equals("")) {
            GalleryDto galleryDto = new GalleryDto();
            galleryDto.setId(userGalleryDto.getId());
            setGalleryDto(galleryDto);
        } else if (userGalleryDto.getGalleryId() != null) {
            GalleryDto galleryDto = new GalleryDto();
            galleryDto.setId(userGalleryDto.getGalleryId());
            setGalleryDto(galleryDto);
        }
    }
}

package tr.com.everva.garage.model.dto.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tr.com.everva.garage.model.dto.tenant.GarageCreateDto;
import tr.com.everva.garage.model.entity.User;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
public class UserDto implements UserDetails {

    @JsonProperty("t")
    private GarageCreateDto tenantDto;

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
        setName(user.getName());
        if (user.getTenant() != null) {
            GarageCreateDto tenantDto = new GarageCreateDto();
            tenantDto.setId(user.getTenant().getId());
           // TODO fix tenantDto.setGalleryName(user.getTenant().getGalleryName());
            setTenantDto(tenantDto);
        } else if (user.getTenantId() != null) {
            GarageCreateDto tenantDto = new GarageCreateDto();
            tenantDto.setId(user.getTenantId());
            setTenantDto(tenantDto);
        }
    }
}

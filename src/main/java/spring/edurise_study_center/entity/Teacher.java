package spring.edurise_study_center.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher extends BaseEntity implements UserDetails {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String subject;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @Getter
    @Column(unique = true,name = "username")
    private String username;
    private String password;
    @OneToMany(mappedBy = "teacher")
    private List<Group>groups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(roles.name());
        return Set.of(new SimpleGrantedAuthority("ROLE_"+this.roles.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

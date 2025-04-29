package spring.edurise_study_center.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends BaseEntity implements UserDetails {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private int age;
    @Column(unique=true,name = "username")
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Group> groups;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Payment> payment;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(this.role);
        return Set.of(new SimpleGrantedAuthority("ROLE_"+this.role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

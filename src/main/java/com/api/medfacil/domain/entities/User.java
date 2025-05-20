package com.api.medfacil.domain.entities;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity(name = "User")
@Table(name = "users",
        indexes = {@Index(name = "idx_phone_complete", columnList = "ddi, ddd, phone_number")})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Hidden
    private String password;

    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(length = 1)
    private String sex;

    @Embedded
    private ContactUser contact;

    @Hidden
    private String role;

    @Hidden
    private boolean enable;

    @Override
    @Schema(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    @Hidden
    public String getUsername() {
        return cpf;
    }

    @Override
    @Hidden
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Hidden
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Hidden
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Hidden
    public boolean isEnabled() {
        return this.enable;
    }

}

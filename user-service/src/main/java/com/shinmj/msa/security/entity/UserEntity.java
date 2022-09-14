package com.shinmj.msa.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * packageName :  com.shinmj.msa.security.entity
 * fileName : User
 * author :  home
 * date : 22. 9. 12.
 * description :
 */
@Entity
@Table(name = "user")
@Data
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_enc_pwd")
    private String userEncryptedPwd;

    @Column(name ="email")
    private String email;

    //@ElementCollection
    @Column(name = "role")
    //private List<String> authorities = new ArrayList<>();
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {



        return null;
    }

    @Override
    public String getPassword() {
        return this.userEncryptedPwd;
    }

    @Override
    public String getUsername() {
        return this.userId;
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

package uz.uzinfocom.uzinfocomProjectManagemant.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.MyUser;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.Roles;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.MyUserRepository;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.*;

import java.util.stream.Collectors;

public class CustomUserDetails extends MyUser implements UserDetails {

    public CustomUserDetails(final MyUser users){
        super(users);
    }

    @Autowired
    MyUserRepository userRepository;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        return getRole()
//                .stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
//                .collect(Collectors.toList());

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        for (Roles role: this.getRole()) {

            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));

        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
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

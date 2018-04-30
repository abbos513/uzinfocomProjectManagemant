package uz.uzinfocom.uzinfocomProjectManagemant.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.uzinfocom.uzinfocomProjectManagemant.Entity.MyUser;
import uz.uzinfocom.uzinfocomProjectManagemant.Model.CustomUserDetails;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.MyUserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private MyUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<MyUser> optionalMyUser = userRepository.findByUsername(username);

        optionalMyUser.orElseThrow(() -> new UsernameNotFoundException("Username not Found"));

        optionalMyUser.map(user -> {
                        return new CustomUserDetails(user);
                    }).get();

        return optionalMyUser.map(CustomUserDetails::new).get();
    }
}










































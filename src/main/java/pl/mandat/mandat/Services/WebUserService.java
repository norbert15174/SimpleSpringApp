package pl.mandat.mandat.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mandat.mandat.Model.UserModel;
import pl.mandat.mandat.Repository.UserModelRepository;
@Service
public class WebUserService implements UserDetailsService {

    private UserModelRepository userModelRepository;

    @Autowired
    public WebUserService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userModelRepository.findByUsername(s).get();
    }

    public void saveUser(UserModel userDetails, PasswordEncoder passwordEncoder){
        String password = userDetails.getPassword();
        userDetails.setPassword(passwordEncoder.encode(password));
        userModelRepository.save(userDetails);
    }
}

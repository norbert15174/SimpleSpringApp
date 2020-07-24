package pl.mandat.mandat.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mandat.mandat.Model.UserModel;
import pl.mandat.mandat.Services.WebUserService;

@Configuration
public class NewUserConfiguration {

    public NewUserConfiguration(WebAppConfiguration webAppConfiguration, PasswordEncoder passwordEncoder, WebUserService webUserService) {
        UserModel userModel = new UserModel();
        userModel.setPassword("najlepszy");
        userModel.setUsername("norbert");
        userModel.setRole("ROLE_ADMIN");
        webUserService.saveUser(userModel,passwordEncoder);
    }
}

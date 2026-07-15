package myApp.service;

import myApp.model.EntityUser;
import myApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public EntityUser findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public EntityUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void registerUser(String username, String rawPassword,
                             String firstName, String lastName,
                             String patronymic, String email) {
        EntityUser user = new EntityUser();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymic(patronymic != null ? patronymic : "");
        user.setEmail(email);
        userRepository.save(user);
    }
}

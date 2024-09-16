package dev.swap.springtodo.auth;

import dev.swap.springtodo.exception.ResourceNotFoundException;
import dev.swap.springtodo.user.RegisterUserDTO;
import dev.swap.springtodo.user.User;
import dev.swap.springtodo.user.UserLoginDTO;
import dev.swap.springtodo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User registerUser(RegisterUserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder.encode(userDTO.password()));

        return userRepository.save(user);
    }

    public User  authenticate(UserLoginDTO userLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.email(),
                        userLoginDTO.password()
                )
        );

        return userRepository.findByEmail(userLoginDTO.email()).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

}

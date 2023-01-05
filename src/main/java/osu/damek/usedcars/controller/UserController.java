package osu.damek.usedcars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.security.jwt.JwtUtils;
import osu.damek.usedcars.security.payload.JwtResponse;
import osu.damek.usedcars.security.payload.LoginRequest;
import osu.damek.usedcars.security.payload.SignupRequest;
import osu.damek.usedcars.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("all")
    ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("get/{userId}")
    ResponseEntity<Object> getUserDataByUserId(@PathVariable("userId") Long userId) {
        return userService.getUserDataByUserId(userId);
    }

    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username is already taken!");
        }

        User user = new User(
                signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword())
        );
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        user.setTags(null);

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                user.getUserId(),
                user.getUsername()
        ));
    }
}

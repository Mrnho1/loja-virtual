    package com.dio.e_commerce.controller;

    import com.dio.e_commerce.dto.LoginDTO;
    import com.dio.e_commerce.dto.RegisterDTO;
    import com.dio.e_commerce.model.Cart;
    import com.dio.e_commerce.model.User;
    import com.dio.e_commerce.repository.UserRepository;
    import com.dio.e_commerce.security.JwtService;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.*;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.bind.annotation.*;

    import java.util.Map;

    @RestController
    @RequestMapping("/auth")
    @RequiredArgsConstructor
    public class AuthController {

        private final AuthenticationManager authManager;
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;

        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto) {
            if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Username já existe");
            }

            User user = User.builder()
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(dto.getRole())
                    .cart(new Cart())
                    .build();

            userRepository.save(user);
            return ResponseEntity.ok("Usuário registrado com sucesso");
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
            try {
                Authentication auth = authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

                User user = userRepository.findByUsername(dto.getUsername()).get();

                String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

                return ResponseEntity.ok(Map.of("token", token));

            } catch (BadCredentialsException e) {
                return ResponseEntity.status(401).body("Credenciais inválidas");
            }
        }

        @GetMapping("/users")
        public ResponseEntity<?> getAllUsers() {
            return ResponseEntity.ok(userRepository.findAll());
        }


    }


    package com.example.demo.controller;
    
    import com.example.demo.entity.User;
    import com.example.demo.repository.UserRepository;
    import com.example.demo.service.H2UserLoaderService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController {
    
        @Autowired
        private H2UserLoaderService loader;

        @Autowired
        private UserRepository userRepository;
    
        @PostMapping("/load")
        public ResponseEntity<String> load() {
            loader.loadUsersToH2();
            return ResponseEntity.ok("Users loaded into H2 DB");
        }

        @GetMapping("/search")
        public ResponseEntity<List<User>> searchUsers(@RequestParam("query") String query) {
            return ResponseEntity.ok(userRepository.searchByText(query));
        }

        // b. Find by ID
        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            return userRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        // b. Find by Email
        @GetMapping("/email")
        public ResponseEntity<User> getUserByEmail(@RequestParam("value") String email) {
            return userRepository.findByEmail(email)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    }
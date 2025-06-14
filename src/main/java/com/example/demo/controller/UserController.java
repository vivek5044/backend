    package com.example.demo.controller;
    
    import com.example.demo.entity.User;
    import com.example.demo.repository.UserRepository;
    import com.example.demo.service.H2UserLoaderService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @CrossOrigin(origins = "*")
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

        //free search
        @GetMapping("/search")
        public ResponseEntity<?> searchUsers(@RequestParam("query") String query) {
            List<User> users = userRepository.searchByText(query);

            if (users.isEmpty()) {
                return ResponseEntity
                        .status(404)
                        .body("No users found matching the query: " + query);
            }

            return ResponseEntity.ok(users);
        }


        //Find by ID
        @GetMapping("/{id}")
        public ResponseEntity<?> getUserById(@PathVariable Long id) {
            Optional<User> user = userRepository.findById(id);
            if(user.isEmpty()){
              return ResponseEntity
                      .status(404)
                      .body("No users found matching the id: "+ id);
            };
            return ResponseEntity.ok(user);
        }

        //Find by Email
        @GetMapping("/email")
        public ResponseEntity<?> getUserByEmail(@RequestParam("value") String email) {
            Optional<User> user = userRepository.findByEmail(email);
            if(user.isEmpty()){
                return ResponseEntity
                        .status(404)
                        .body("No users found matching the email: "+ email);
            };
            return ResponseEntity.ok(user);
        }
    }
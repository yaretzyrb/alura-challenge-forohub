package com.yaretzyram.alura.forohub.controllers;

import com.yaretzyram.alura.forohub.domains.models.user.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserOutputDTO> registerNewUser(@RequestBody UserRegisterDTO userRegisterDTO, UriComponentsBuilder uriComponentsBuilder){
        User user = new User(userRegisterDTO);
        userRepository.save(user);

        UserOutputDTO registeredUser = new UserOutputDTO(user.getId(), user.getName(), user.getEmail());
        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(registeredUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@PathVariable Long id){
        User user = userRepository.getReferenceById(id);
        UserOutputDTO foundUser = new UserOutputDTO(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> getUsers(){
        return ResponseEntity.ok(userRepository.findByActiveTrue().stream().toList());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserOutputDTO> updateUser(@RequestBody UserUpdateDTO newUserData){
        User existingUser = userRepository.getReferenceById(newUserData.id());
        existingUser.updateUserData(newUserData);
        UserOutputDTO updatedUser = new UserOutputDTO(existingUser.getId(), existingUser.getName(), existingUser.getEmail());
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        User existingUser = userRepository.getReferenceById(id);
        existingUser.deactivateUser();
        return ResponseEntity.ok("User deactivated");
    }

}

package com.yaretzyram.alura.forohub.controllers;

import com.yaretzyram.alura.forohub.domains.models.user.User;
import com.yaretzyram.alura.forohub.domains.models.user.UserLoginDTO;
import com.yaretzyram.alura.forohub.infra.security.AuthenticationService;
import com.yaretzyram.alura.forohub.infra.security.JWTTokenDTO;
import com.yaretzyram.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginDTO data){
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(token);
        var JWTToken = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(JWTToken));
    }
}

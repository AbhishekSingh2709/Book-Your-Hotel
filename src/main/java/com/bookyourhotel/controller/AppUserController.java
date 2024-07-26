package com.bookyourhotel.controller;

import com.bookyourhotel.dto.AppUserDto;
import com.bookyourhotel.dto.TokenDto;
import com.bookyourhotel.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appUser")
public class AppUserController {
    private AppUserService aus;

    public AppUserController(AppUserService aus) {
        this.aus = aus;
    }

    @PostMapping("/addUsers")
    public ResponseEntity<?> addUser(@RequestBody AppUserDto dto)
    {
        ResponseEntity<?> addUsers = aus.addUser(dto);
        return new ResponseEntity<>(addUsers , HttpStatus.OK);
    }

    @PostMapping("/verifyUser")
    public ResponseEntity<?> verifyUser(@RequestBody AppUserDto dto)
    {
        TokenDto verifyUser = aus.verifyUser(dto);
        if (verifyUser != null)
        {
            return new ResponseEntity<>(verifyUser, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Invalid username or password", HttpStatus.OK);
    }



}

package com.encore.petandbe.controller.user.user.api;

import com.encore.petandbe.controller.user.user.requests.UserSignUpRequest;
import com.encore.petandbe.controller.user.user.responses.UserSignUpResponse;
import com.encore.petandbe.service.user.user.UserSignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserSignUpController {

    private final UserSignUpService userSignUpService;


    public UserSignUpController(UserSignUpService userSignUpService) {
        this.userSignUpService = userSignUpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponse> userSignUp(@RequestBody UserSignUpRequest userSignUpRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userSignUpService.userSignUp(userSignUpRequest));
    }

}



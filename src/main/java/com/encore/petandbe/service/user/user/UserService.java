package com.encore.petandbe.service.user.user;

import java.util.Map;
import java.util.Optional;

import com.encore.petandbe.controller.user.user.responses.UserDetailsResponse;
import com.encore.petandbe.model.user.user.User;
import com.encore.petandbe.repository.UserRepository;
import com.sun.xml.bind.v2.TODO;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsResponse findUserDetails(String token) {
        return null;
    }

    public Long loginByKakao(Map<String, String> user){
        User foundUser = findUserById(user);
        // TODO : HttpServletResponse header에 jwt(id)담아서 보내는 메서드


        return null;
    }

    private User findUserById(Map<String, String> user) {
        Optional<User> foundUser = userRepository.findByUserCode(user.get("id"));

        if(foundUser.isEmpty()){
            return userRepository.save(User.builder()
                .userCode(user.get("id"))
                .nickname(user.get("nickname"))
                .build());
        }

        return foundUser.get();
    }
}

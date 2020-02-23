package demo.alex.controller;

import demo.alex.exception.LoginException;
import demo.alex.rest.LoginUser;
import demo.alex.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    private LoginServiceImpl loginService;

    @Autowired
    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    public ResponseEntity login(LoginUser user) {
        try {
            boolean isLoginSuccessful = loginService.saveNewUser(user);
            if (isLoginSuccessful) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (LoginException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity newUser(LoginUser user) {
        try {
            boolean isLoginSuccessful = loginService.saveNewUser(user);
            if (isLoginSuccessful) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        } catch (LoginException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
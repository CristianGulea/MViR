package ro.ubb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.model.Code;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public ResponseEntity<Code> findAllGenres(){
        String code = UUID.randomUUID().toString().replaceAll("-", "");
        code = code.substring(0, 30);
        return new ResponseEntity<>(new Code(code), HttpStatus.OK);
    }

}

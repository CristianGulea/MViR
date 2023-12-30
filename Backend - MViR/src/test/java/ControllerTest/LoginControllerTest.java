package ControllerTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import ro.ubb.controller.LoginController;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    private static LoginController loginController;

    @BeforeAll
    public static void setUp(){
        loginController = new LoginController();
    }

    @Test
    public void testFindAllGenres(){
        assertSame(loginController.findAllGenres().getStatusCode(), HttpStatus.OK);
    }
}

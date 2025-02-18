package identity.TuanHuy.controller;

import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;


@SpringBootTest
@AutoConfigureMockMvc // create one mock request to controller , scope test just in controller
public class UserControllerTest {

    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserCreationRequest userCreationRequest;

    private UserResponse userResponse;

    private LocalDate dob;
    @BeforeEach
    void initDate(){
        dob = LocalDate.of(2003,9,07);
    // this is method run before @Test run
        userCreationRequest = UserCreationRequest.builder()
                .username("le van d")
                .email("levand@gmail.com")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("fjdaofeo8feiajfeuw0")
                .username("le van d")
                .email("levand@gmail.com")
                .dob(dob)
                .build();
    }

    @Test
    void createUser(){
        // given , the meaning is : know and predict it that way , it is predefined input data
        // when ,
        // then
        log.info("hello world test");
    }
}


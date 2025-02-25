package identity.TuanHuy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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

        dob = LocalDate.of(1900,9,02);
//        dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));


        // this is method run before @Test run
        userCreationRequest = UserCreationRequest.builder()
                .username("nguyenvanf")
                .email("nguyenvanf@gmail.com")
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
    void createUser_validRequest_successfully() throws Exception {
        // given , the meaning is : know and predict it that way , it is predefined input data
        // to convert object userCreationRequest from String -> using ObjectMappper
        // mặc định ObjectMapper ko hỗ trọ điều localDate nên phải dùng thêm jackson-datatype-jsr310

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // support datatype LocalDate
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        String content = objectMapper.writeValueAsString(userCreationRequest);
        System.out.println("JSON being sent: " + content);


        // làm cái này để ko gọi trực tiếp vô hàm userService
        Mockito.when(userService.CreateUser(ArgumentMatchers.any() ))
                        .thenReturn(userResponse);


        // when , là tạo 1 request để tạo đc thì dùng mockMVC
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))

                // this is THEN : andExpect là http status code
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code")
                .value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id")
                .value("fjdaofeo8feiajfeuw0"))
        ;
        log.info("hello world test");
//✔ Client gửi dd-MM-yyyy → Jackson parse ra LocalDate → MapStruct giữ nguyên LocalDate → JPA lưu thành yyyy-MM-dd trong DB.
    }


    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        // given , the meaning is : know and predict it that way , it is predefined input data
        // to convert object userCreationRequest from String -> using ObjectMappper
        // mặc định ObjectMapper ko hỗ trọ điều localDate nên phải dùng thêm jackson-datatype-jsr310
        userCreationRequest.setUsername("ng");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // support datatype LocalDate
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        String content = objectMapper.writeValueAsString(userCreationRequest);
        System.out.println("JSON being sent: " + content);


        // làm cái này để ko gọi trực tiếp vô hàm userService
        Mockito.when(userService.CreateUser(ArgumentMatchers.any() ))
                .thenReturn(userResponse);


        // when , là tạo 1 request để tạo đc thì dùng mockMVC
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))

                // this is THEN : andExpect là http status code
                .andExpect(MockMvcResultMatchers.status().isBadRequest())

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].message")
                        .value("Username must be at least 4 characters"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code")
                        .value(1002))

        ;
        log.info("hello world test");
        //✔ Client gửi dd-MM-yyyy → Jackson parse ra LocalDate → MapStruct giữ nguyên LocalDate → JPA lưu thành yyyy-MM-dd trong DB.
    }


}


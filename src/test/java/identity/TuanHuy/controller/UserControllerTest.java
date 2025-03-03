    package identity.TuanHuy.controller;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
    import identity.TuanHuy.configuration.SecurityConfig;
    import identity.TuanHuy.dto.request.UserUpdateRequest;
    import identity.TuanHuy.dto.response.ApiResponse;
    import identity.TuanHuy.dto.response.UserResponse;
    import identity.TuanHuy.dto.request.UserCreationRequest;
    import identity.TuanHuy.service.UserService;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.ArgumentMatchers;
    import org.mockito.Mockito;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.boot.test.mock.mockito.MockBean;
    import org.springframework.context.annotation.Import;
    import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
    import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
    import java.time.LocalDate;
    import java.util.HashSet;
    import java.util.Set;

//            Arrange: Thiết lập dữ liệu test và mock behavior
//            Act: Thực hiện hành động cần test thông qua mockMvc
//            Assert: Kiểm tra kết quả trả về

    @Import(SecurityConfig.class)
    @SpringBootTest
    @AutoConfigureMockMvc // create one mock request to controller , scope test just in controller
    public class UserControllerTest {
        // hàm nhận vô T , trả ra ApiResponse<T>
        private <T> ApiResponse<T> createApiResponse(int code , String message , T result){
            return ApiResponse.<T>builder()
                    .message(message)
                    .code(code)
                    .result(result)
                    .build()
                    ;
        }

        private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserService userService;

        private UserCreationRequest userCreationRequest;
        private UserUpdateRequest userUpdateRequest;
        private UserResponse userResponse;
        private LocalDate dob;
        private String userId;
        private ApiResponse apiResponse;


        @BeforeEach
        void initDate(){
            Set<String> setRoles = new HashSet<>();
            setRoles.add("USER");
            setRoles.add("ADMIN");
            setRoles.add("EDITOR");

            Set<String> setPermissions = new HashSet<>();
            setRoles.add("permission user");
            setRoles.add("permission admin");
            setRoles.add("permission editor");

            userId = "fjdaofeo8feiajfeuw0";
            dob = LocalDate.of(1900,9,02);
    //      dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            userCreationRequest = UserCreationRequest.builder()
                    .username("nguyenvanf")
                    .email("nguyenvanf@gmail.com")
                    .password("12345678")
                    .dob(dob)
                    .build();

            userResponse = UserResponse.builder()
                    .id(userId)
                    .username("nguyenvanf")
                    .email("nguyenvanf@gmail.com")
                    .dob(dob)
                    .roles(setRoles)
                    .permissions(setPermissions)
                    .emailVerified(false)
                    .profileImage("https://placehold.co/600x400")
                    .build();

            userUpdateRequest = UserUpdateRequest.builder()
                    .dob(dob)
                    .email("nguyenvand@gmail.com")
                    .password("12345678")
                    .username("nguyenvand")
                    .emailVerified(false)
                    .profileImage("https://placehold.co/600x400")
                    .build();

        }

        @Test
        void createUser_validRequest_successfully() throws Exception {
            // given , the meaning is : know and predict it that way , it is predefined input data
            // Purpose: turning the userCreationRequest object into the JSON chain to send in mock.perform
            // mặc định ObjectMapper ko hỗ trọ điều localDate nên phải dùng thêm jackson-datatype-jsr310

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // support datatype LocalDate
            // objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            // objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            String content = objectMapper.writeValueAsString(userCreationRequest);
            System.out.println("JSON being sent: " + content);


            // làm cái này để ko gọi trực tiếp vô hàm userService
            // using Object ArgumentMatchers.any() to accept any object
            Mockito.when(userService.CreateUser(userCreationRequest))
                            .thenReturn(userResponse);



            // WHEN , là tạo 1 request để tạo đc thì dùng mockMVC
            mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(content))

                    // th is is THEN : andExpect là http status code
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

        @Test
        void deleteUser_validRequest_successfully() throws Exception{
            // GIVEN
            Mockito.when(userService.DeleteUser(userId)).thenReturn("user deleted successfully");
            // WHEN
            mockMvc.perform(
                    MockMvcRequestBuilders
                    .delete("/api/users/{userId}",userId)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)) // is content-type: application/json;
                    // THEN
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("user deleted successfully"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("user deleted successfully"))
                    .andDo(result -> log.info("Response: {}", result.getResponse().getContentAsString()));
                    Mockito.verify(userService, Mockito.times(1)).DeleteUser(userId);
        }

        @Test
        void updateUser_validRequest_successfully() throws Exception{
            // GIVEN
            ApiResponse<UserUpdateRequest> userResponseApiResponse = createApiResponse(200,"User updated successfully",userUpdateRequest);
            Mockito.when(userService.UpdateUser(userUpdateRequest, userId)).thenReturn(userResponse);
            // WHEN
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .put("/api/users/{userId}",userId)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    // THEN
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User updated successfully"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(userResponseApiResponse))
                    ;
                    // VERIFY: Kiểm tra xem phương thức UpdateUser có được gọi đúng 1 lần hay không
                    Mockito.verify(userService,Mockito.times(1)).UpdateUser(userUpdateRequest,userId);
        }


    }


package identity.TuanHuy.service;


import identity.TuanHuy.dto.request.UserCreationRequest;
import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.entity.Role;
import identity.TuanHuy.entity.RoleEnum;
import identity.TuanHuy.entity.Users;
import identity.TuanHuy.repository.UserRepository;
import org.hibernate.mapping.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when; // note !!!
import java.time.LocalDate;


@SpringBootTest
public class UserServiceTest {
    // test service thật nên cần 1 object service thật
//
//
//    @Autowired
//    private UserService userService;
//
//    private Users user;
//
//    private LocalDate dob;
//
//    private UserCreationRequest userCreationRequest;
//
//    private UserResponse userResponse;
//
//    // trong trường hơp này thì mock userRepository
//    @MockBean
//    private UserRepository userRepository;
//
//
//    @BeforeEach
//    void initDate(){
//
//        dob = LocalDate.of(1900,9,02);
////        dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//
//
//        // this is method run before @Test run
//        userCreationRequest = UserCreationRequest.builder()
//                .username("nguyenvanf")
//                .email("nguyenvanf@gmail.com")
//                .password("12345678")
//                .dob(dob)
//                .build();
//
//        userResponse = UserResponse.builder()
//                .id("fjdaofeo8feiajfeuw0")
//                .username("le van d")
//                .email("levand@gmail.com")
//                .dob(dob)
//                .build();
//
//        user = Users.builder()
//                .id("fjdaofeo8feiajfeuw0")
//                .username("le van d")
//                .email("levand@gmail.com")
//                .dob(dob)
//
//                .build();
//
//    }
//
//
//    @Test
//    void createUser_validRequest_success(){
//    // GIVEN
//    // WHEN
//        Users user;
//        when(userRepository.existByUsername(anyString())).thenReturn(false);
////        when(userRepository.save(any())).thenReturn(user);
//    // THEN
//    }
}

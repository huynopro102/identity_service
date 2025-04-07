package identity.TuanHuy.service;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import identity.TuanHuy.dto.response.AuthenticationResponse;
import identity.TuanHuy.dto.response.IntrospectResponse;
import identity.TuanHuy.dto.request.AuthenticationRequest;
import identity.TuanHuy.dto.request.IntrospectRequest;
import identity.TuanHuy.dto.response.UserResponse;
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final RedisTemplate<String ,String> redisTemplate;
    private  String SESSION_PREFIX = "session:";
    private  Duration EXPIRATION = Duration.ofHours(2);
    private final UserRepository userRepository;


    @Value("${spring.jwt.signerKey}")
    @NonFinal // để không inject cái này vô constructor
    protected String SIGNER_KEY ;


    public void saveSession(String username , String token){
        String key = SESSION_PREFIX + username;
        redisTemplate.opsForHash().put(key,"token",token);
        redisTemplate.expire(key,EXPIRATION);
        log.info("Stored session keys: " + redisTemplate.keys("session:*"));
        log.info("Token for user: " + redisTemplate.opsForHash().get("session:"+username, "token"));
        log.info("redis write");
    }

    public String getSession(String username){
        return (String) redisTemplate.opsForHash().get(SESSION_PREFIX+username,"token");
    }


    public void deleteSession(String username){
        redisTemplate.delete(SESSION_PREFIX+username);
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        // kiểm tra xem token hết hạn chưa
        Date expirytime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        var notExpired = expirytime.after(new Date());

        //
        if(verified && notExpired){
            String userId = signedJWT.getJWTClaimsSet().getSubject();
           return IntrospectResponse.builder()
                   .valid(true)
                   .userId(userId)
                   .build();
        }else{
            return IntrospectResponse.builder()
                    .valid(false)
                    .build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        log.info("signer key : "+SIGNER_KEY);

        var user = userRepository.findByEmail(request.getEmail())

                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated) {
            throw new AppException(ErrorCode.AUTHENTICATE_INVALID);
        }
        var token = generateToken(user.getId());
//        saveSession(request.getEmail(),token);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public String generateToken(String userId) {

        // Tạo thời gian hết hạn cho token (1 phút từ thời điểm hiện tại) sử dụng Instant
        Instant expirationTime = Instant.now().plus(10, ChronoUnit.MINUTES);

        // tạo 1 header , JWS có tham số là thuật toán mã hóa
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);


        // tạo payload có 2 bước 1 là tạo các claim 2 là tạo payload tọa thông qua JWSObject , các data trong body goi là claim
        JWTClaimsSet jwtClaimNames = new JWTClaimsSet.Builder()
                .subject(userId) // claim này dùng để đính nghĩa người gửi token này , ở đây tôi định nghĩa là id của user
                .issuer("huynguyen-nginx") // claim này để biết là token này đc gửi đi từ ai , thường sẽ là domin của service của mình
                .issueTime(Date.from(Instant.now()))
                .expirationTime(Date.from(expirationTime))
                .claim( "customClaim","đây là claim tự custom")
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload
                payload = new Payload(jwtClaimNames.toJSONObject());


        // tạo token này nhờ 2 cái header và payload
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        // tạo secret key và ký token
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return  jwsObject.serialize();
        } catch (KeyLengthException e){
            throw new AppException(ErrorCode.SIGNER_KEY_IS_TOO_SHORT);
        }
        catch (JOSEException e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}

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
import identity.TuanHuy.exception.AppException;
import identity.TuanHuy.exception.ErrorCode;
import identity.TuanHuy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    UserRepository userRepository;

    @NonFinal // để không inject cái này vô contructer
    protected static final String SIGNER_KEY = "ZQM5hQ9xYRxNzMA/PsJggsQllKFiOz6mDyd372mZ52OwLlj/MbpzB6DTtCp4aWuv";

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        // kiểm tra xem token hết hạn chưa
        Date expirytime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        //
        return IntrospectResponse.builder()
                .valid(verified && expirytime.after(new Date()))
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated) {
            throw new AppException(ErrorCode.AUTHENTICATE_INVALID);
        }
        var token = generateToken(request.getEmail());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public String generateToken(String username) {

        // Tạo thời gian hết hạn cho token (1 phút từ thời điểm hiện tại) sử dụng Instant
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.MINUTES);

        // tạo 1 header , JWS có tham số là thuật toán mã hóa
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);


        // tạo payload có 2 bước 1 là tạo các claim 2 là tạo payload tọa thông qua JWSObject , các data trong body goi là claim
        JWTClaimsSet jwtClaimNames = new JWTClaimsSet.Builder()
                .subject(username) // claim này dùng để đính nghĩa người gửi token này
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
        } catch (JOSEException e) {
            log.error("loi ko generate token");
            throw new RuntimeException(e);
        }

    }
}

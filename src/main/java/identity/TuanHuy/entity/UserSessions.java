package identity.TuanHuy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_sessions")
public class UserSessions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private Users user;

    @Column(name = "device_info" ) // the default value length = 255 if not defined
    private String deviceInfo;

    @Column(name = "ip_public" , length = 45)
    private String ipPublic;

    @Column(name = "ip_private" , length = 45)
    private String ipPrivate;

    @Column(name = "port")
    private Integer port;

    @Column(name = "network_name")
    private String networkName;

    @Column(name = "login_time")
    private LocalDateTime loginTime = LocalDateTime.now();

    @Column(name = "logout_time")
    private LocalDateTime logoutTime;

    @Column(name = "token")
    private String token;

    @Column(name = "is_active" , columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;


}

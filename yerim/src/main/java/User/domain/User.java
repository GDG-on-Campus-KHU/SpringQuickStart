package User.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_userid", columnNames = "userid"),
        @UniqueConstraint(name = "uk_users_email", columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // H2/MySQL 기본
    private Integer id;

    @Column(nullable = false, length = 40)
    private String userId;

    @Column(nullable = false, length = 200) // 해시 비번 저장
    private String password;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 120)
    private String roles = "USER"; // 예: "USER", "ADMIN"

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // 기본 생성자 (JPA)
    protected User() {}

    // 편의 생성자
    public User(String userid, String password, String name, String roles, Boolean enabled) {
        this.userId = userid;
        this.password = password; // 서비스에서 반드시 해시 후 주입
        this.name = name;
        this.roles = roles;
    }

    // Getters/Setters
    public Integer getId() { return id; }

    public String getUserId() { return userId; }
    public void setUserId(String userid) { this.userId = userid; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; } // 해시값으로 세팅

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}

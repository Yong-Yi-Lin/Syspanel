package org.example.springbootdemo1.pojo;

import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Entity
//@IdClass(VerificationCodeId.class)
public class VerificationCode{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String code;
    private LocalDateTime expirationTime;
    private boolean used;
    private LocalDateTime createdAt; // 注意：如果数据库字段是TIMESTAMP，你可能需要使用java.sql.Timestamp

    // Getter和Setter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    // 添加一个方法来检查验证码是否已过期
    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(expirationTime);
    }
    // 如果需要，可以添加toString、equals和hashCode方法
    @Override
    public String toString() {
        return "VerificationCode{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", expirationTime=" + expirationTime +
                ", used=" + used +
                ", createdAt=" + createdAt +
                '}';
    }
}
package org.example.springbootdemo1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Logs {
    private Integer logId;
    private String title;
    private String content;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private boolean isPublic;
    private String logInfo;
    //    private Integer userId;
    private String userName;
    public Logs(){}

    // 构造方法，通常用于创建新的日志条目，但此时不知道logId
    public Logs(String title, String content, boolean isPublic, String logInfo, String userName,LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
        this.logInfo = logInfo;
        this.userName = userName;
        // 在创建时设置时间戳
        this.createdAt = createdAt;
    }

    // 带有logId的构造方法，可能用于从数据库加载日志
    public Logs(Integer logId, String title, String content, LocalDateTime createdAt, boolean isPublic, String logInfo, String userName) {
        this.logId = logId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.isPublic = isPublic;
        this.logInfo = logInfo;
        this.userName = userName;
    }

    // Getter和Setter方法
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // 注意：通常不需要设置createdAt的setter，因为它在创建时就被设置了

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

// 可以添加其他方法，例如保存到数据库或从数据库加载等（这些通常在DAO层实现）

    // toString方法，用于打印日志信息
    @Override
    public String toString() {
        return "Logs{" +
                "logId=" + logId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", isPublic=" + isPublic +
                ", logInfo='" + logInfo + '\'' +
                ", userName=" + userName +
                '}';
    }
}
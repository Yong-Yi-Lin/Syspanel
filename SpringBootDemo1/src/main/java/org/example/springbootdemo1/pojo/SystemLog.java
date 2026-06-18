package org.example.springbootdemo1.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Getter
@Component
public class SystemLog {
    private String warnType; //警告类型
    private String warnTitle; //警告标题
    private String warnContact; //警告内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp logTime;

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getWarnTitle() {
        return warnTitle;
    }

    public void setWarnTitle(String warnTitle) {
        this.warnTitle = warnTitle;
    }

    public String getWarnContact() {
        return warnContact;
    }

    public void setWarnContact(String warn_contact) {
        this.warnContact = warn_contact;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }
}

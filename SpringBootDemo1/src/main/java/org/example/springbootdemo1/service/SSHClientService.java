package org.example.springbootdemo1.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.example.springbootdemo1.pojo.SSHClient;

public interface SSHClientService {
    public Session login(SSHClient sshClient);

    public String execCmd(SSHClient sshClient);

    public Boolean JschDisconnect(Channel channel, Session session);

//    public String sendCmd(String cmd);
}

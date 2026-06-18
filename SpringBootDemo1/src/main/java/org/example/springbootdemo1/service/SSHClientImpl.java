package org.example.springbootdemo1.service;

import com.jcraft.jsch.*;
import org.example.springbootdemo1.pojo.SSHClient;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Service
public class SSHClientImpl implements SSHClientService {
    SSHClient sshInfo = null;
    @Override
    public Session login(SSHClient sshClient) {
        String userName = sshClient.getUsername();// 用户名
        String password = sshClient.getPassword();// 密码
        String host = sshClient.getHost();// 服务器地址
        int port = sshClient.getPort();// 端口号
        sshInfo = sshClient;

        Session session = sshClient.getSession();
        try {
            session = sshClient.getJsch().getSession(userName, host, port);
            session.setPassword(password); // 设置密码
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(sshClient.getSESSION_TIMEOUT()); // 设置timeout时间
            session.connect(); // 通过Session建立链接
            System.out.println("111："+session);
            sshInfo.setSession(session);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
        // 根据用户名，主机ip，端口获取一个Session对象
        return session;
    }

    @Override
    public String execCmd(SSHClient sshClient) {
        String cmd = sshClient.getCmd();// 要运行的命令
        System.out.println("cmd:" + cmd);

//        Session session = sshClient.getSession();
        Channel channelExec = sshClient.getChannel();
        BufferedReader bufferedReader = null;
        String resp = "";
        Session session = null;
        try {
            session = login(sshClient);
//            session = sshInfo.getSession();
            System.out.println("222："+session);
            if (session == null) {
                resp = "远程连接失败";
                return resp;
            } else {
//            ((ChannelExec) channel).setCommand(command);
                if (cmd == null) {
//                    resp = "命令为空";
                    return resp;
                }
                channelExec = session.openChannel("exec");
                ((ChannelExec) channelExec).setCommand(cmd);
                channelExec.setInputStream(null);
                ((ChannelExec) channelExec).setErrStream(System.err);
                channelExec.connect();

                bufferedReader = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    resp += line + "\n";
                }
                if (!resp.isEmpty()) {
                    resp = resp.substring(0, resp.length() - 1);
                }
//                InputStream in = channelExec.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
//                String buf = null;
//                StringBuffer sb = new StringBuffer();
//                while ((buf = reader.readLine()) != null) {
//                    sb.append(buf);
//                    System.out.println(buf);// 打印控制台输出
//                }

//                reader.close();
//                channelExec.disconnect();
//                session.disconnect();
//                return resp;
            }
        } catch (JSchException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channelExec != null) {
                channelExec.disconnect();
            }
            this.JschDisconnect(channelExec,session);
        }
        return resp;
    }

    public Boolean JschDisconnect(Channel channel,Session session) {
        if (channel == null || channel.isClosed()) {
//            System.out.println("exit-status: " + channel.getExitStatus());
            if (session!=null){
                session.disconnect();
            }
            return true;
        }else {
            // 关闭通道和会话
            channel.disconnect();
            session.disconnect();
            return true;
        }
    }
}



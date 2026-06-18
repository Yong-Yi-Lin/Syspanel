//package org.example.springbootdemo1.utils;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class TimerMain {
//    public static void main(String[] args) {
//        Cpu c = new Cpu();
//        SystemInfo systemInfo = new SystemInfo();
//        Memory m = new Memory();
////        SystemGetInfo systemGetInfo = new SystemGetInfo();
//
//
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
////                long currentTimeMillis = System.currentTimeMillis();
////                System.out.println(currentTimeMillis);
//                // 假设你有一个时间戳
//                long currentTimeMillis = System.currentTimeMillis();
//// 创建SimpleDateFormat对象
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//// 将时间戳转换为Date对象，然后格式化
//                String formattedDate = sdf.format(new Date(currentTimeMillis));
//                System.out.println(formattedDate);
//
////                systemGetInfo.getSystemInfo();
////                m.getMemory_rest();
////                m.getMemory_usage();
//                // 在这里编写想要自动执行的代码
//            }
//        };
//        // 设置定时器，每隔5秒执行一次
//        timer.schedule(task, 0, 2000);
//    }
//}

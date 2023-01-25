package com.touchnight;

import com.csvreader.CsvWriter;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import java.io.*;
import java.util.Properties;

public class WIMBListener extends SimpleListenerHost {
    public static long Admin = 10000;
    public static long Group = 12345678;
    public static String CommandPrefix = "/";
    public WIMBListener() throws IOException{
        try {
            CsvWriter csvWriter = new CsvWriter("config/WIMB.properties");
            csvWriter.writeRecord(new String[]{"选手","总分"});
            csvWriter.close();
        } catch (FileNotFoundException e) {
            File Scores = new File("config/WIMBscore.csv");
            CsvWriter csvWriter = new CsvWriter("config/WIMB.properties");
            csvWriter.writeRecord(new String[]{"选手","总分"});
            csvWriter.close();
        }
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("config/WIMB.properties"));
            Admin = Long.parseLong(prop.getProperty("Admin"));
            Group = Long.parseLong(prop.getProperty("Group"));
            CommandPrefix = prop.getProperty("CommandPrefix");
            System.out.println("已加载配置");
        } catch (FileNotFoundException e) {
            Properties prop = new Properties();
            prop.setProperty("Admin", "10000");
            prop.setProperty("Group", "12345678");
            prop.setProperty("CommandPrefix", "/");
            prop.store(new FileOutputStream("config/WIMB.properties"), "Meow");
            System.out.println("已创建配置文件");
            throw e;
        }
    }
    @EventHandler
    private ListeningStatus onEvent(GroupMessageEvent event) throws Exception {
        String msg = event.getMessage().serializeToMiraiCode();
        if (event.getSubject().getId() == Group && msg.startsWith(CommandPrefix)) {
            msg = msg.substring(CommandPrefix.length());
            if (msg.startsWith("加分")) {
                addPoint(msg, event);
            }
            if (msg.equals("当前对局分数")) {
                currentPoint(msg, event);
            }
            if (msg.equals("分数总览")) {
                currentPoints(msg, event);
            }
            if (event.getSender().getId() == Admin) {
                if (msg.startsWith("减分")) {
                    minusPoint(msg, event);
                }
                if (msg.equals("添加选手")) {
                    addPlayer(msg, event);
                }
                if (msg.equals("下一场")) {
                    nextRound(msg, event);
                }
            }
        }
        return ListeningStatus.LISTENING;
    }
    private void addPoint(String msg, MessageEvent event) {

    }
}

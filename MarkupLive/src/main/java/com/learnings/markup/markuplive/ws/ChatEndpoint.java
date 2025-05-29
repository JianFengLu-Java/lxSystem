package com.learnings.markup.markuplive.ws;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
@Slf4j
@ServerEndpoint("/ws/chat")
@Component
public class ChatEndpoint {

    Logger log = org.slf4j.LoggerFactory.getLogger(ChatEndpoint.class);

    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();
    // 这里可以添加一些方法来处理WebSocket连接、消息接收和发送等操作
    // 例如：onOpen、onMessage、onClose等方法

    // 示例方法
    @OnOpen
    public void onOpen(Session session) throws IOException{
        // 将新连接的session添加到集合中
        sessions.add(session);
        // 处理连接打开事件
        log.info("New connection established: {}", session.getId());
        // 可以在这里发送欢迎消息或其他初始化操作
        try {
            session.getBasicRemote().sendText("Welcome to the chat!");
        } catch (Exception e) {
            log.error("Error sending welcome message: {}", e.getMessage());
        }
        sendAll("用户"+session.getId()+"加入了");
    }
    @OnMessage
    public void onMessage(String message,Session session) {
        log.info("Received message: {}", message);

       try{
           sendAll(message);
       }catch (IOException e){
           log.info("hhhh{}",e);
       }
        // 处理接收到的消息

    }

    @OnClose

    public void onClose(Session session) throws IOException {
        // 处理连接关闭事件
       sessions.remove(session);
        log.info("Connection closed: {}", session.getId());
        // 可以在这里发送通知或其他清理操作
        sendAll("用户"+session.getId()+"离开了");
    }

    private void sendAll (String message) throws IOException {
        synchronized (sessions){
            for(Session s : sessions){
                if (s.isOpen()){
                    s.getBasicRemote().sendText(message);
                }
            }
        }

    }
}

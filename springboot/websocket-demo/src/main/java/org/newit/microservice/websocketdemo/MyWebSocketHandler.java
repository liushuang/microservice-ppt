package org.newit.microservice.websocketdemo;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

public class MyWebSocketHandler extends TextWebSocketHandler {

    private Map<Long, WebSocketSession> userSessionMap = Maps.newConcurrentMap();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if(StringUtils.isNotBlank(session.getUri().getQuery())){
            String query = session.getUri().getQuery();
            Iterator<String> iterator = Splitter.on("&").split(query).iterator();
            while(iterator.hasNext()){
                String pair = iterator.next();
                Iterator<String> pairIte = Splitter.on("=").split(pair).iterator();
                if(Objects.equals(pairIte.next(), "myUserId") && pairIte.hasNext()){
                    Long myUserId = Long.valueOf(pairIte.next());
                    userSessionMap.put(myUserId, session);
                }
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        JSONObject json = JSONObject.parseObject(payload);
        long fromUserId = json.getLongValue("fromUserId");
        long toUserId = json.getLongValue("toUserId");
        String userMessage = json.getString("message");
        if (toUserId == 0) {
            userSessionMap.forEach((uid, webSocketSession) -> {
                try {
                    webSocketSession.sendMessage(
                            new TextMessage("from " + fromUserId + "broadcast message:" + userMessage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else if (userSessionMap.containsKey(toUserId)) {
            WebSocketSession toSession = userSessionMap.get(toUserId);
            toSession.sendMessage(new TextMessage("from " + fromUserId + " said: " + userMessage));
        }
    }
}

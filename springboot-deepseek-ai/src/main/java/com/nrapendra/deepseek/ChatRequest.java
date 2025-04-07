package com.nrapendra.deepseek;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String model;
    private List<Message> messages;

    @Data
    public static class Message {
        private String role;
        private String content;

        public Message(String user, String userMessage) {
            this.role=user;
            this.content=userMessage;
        }
    }

}

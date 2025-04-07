package com.nrapendra.deepseek;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    private String id;

    private List<Choice> choices;

    @Data
    public static class Choice {
        private ChatRequest.Message message;
    }
}

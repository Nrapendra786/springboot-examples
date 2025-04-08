package com.nrapendra.deepseek;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChatController {

    private final OpenAiChatModel chatModel;

    private final DeepSeekService deepSeekService;

    @Autowired
    public ChatController(OpenAiChatModel chatModel,DeepSeekService deepSeekService) {
        this.chatModel = chatModel;
        this.deepSeekService = deepSeekService;
    }

    @GetMapping("/ai/callDeepSeekDirect")
    public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation", this.chatModel.call(message));
    }

    @GetMapping("/ai/callDeepSeekThroughBackend")
    public Map callDeepSeek(@RequestParam(value = "message from DeepSeek") String message) {
        return Map.of("generation", this.deepSeekService.getAIResponse(message));
    }


//    @GetMapping("/ai/generateStream")
//    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
//        Prompt prompt = new Prompt(new UserMessage(message));
//        return this.chatModel.stream(prompt);
//    }
}

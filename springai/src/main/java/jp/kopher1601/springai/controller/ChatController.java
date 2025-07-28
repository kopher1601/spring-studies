package jp.kopher1601.springai.controller;

import jp.kopher1601.springai.service.ChatService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatService.chat(message);
    }

    @GetMapping("/chatmessage")
    public String chatmessage(@RequestParam("message") String message) {
        return chatService.chatmessage(message);
    }

    @GetMapping("/chatplace")
    public String chatplace(
            @RequestParam String subject,
            @RequestParam String tone,
            @RequestParam String message) {
        return chatService.chatplace(subject, tone, message);
    }

    @GetMapping("/chatjson")
    public ChatResponse chatjson(
            @RequestParam String message) {
        return chatService.chatjson(message);
    }
}

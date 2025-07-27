package jp.kopher1601.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    @Autowired
    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message) {
        return chatClient.prompt()
                .user(message) // 사용자의 메세지
                .call()
                .content(); // 요청 정보를 받는 부분
    }

    public String chatmessage(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse() // 누턴의 운동 제2법칙을 간단하게 설명하세요
                .getResult()
                .getOutput()
                .getText();
    }

    public String chatplace(String subject, String tone, String message) {
        return chatClient.prompt()
                .user(message)
                .system(sp ->
                        sp.param("subject", subject)
                                .param("tone", tone))
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }
}

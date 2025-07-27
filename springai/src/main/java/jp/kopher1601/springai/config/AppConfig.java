package jp.kopher1601.springai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AppConfig {

    // classpath -> resources 에서 시작
    @Value("classpath:prompt.txt")
    private Resource resource;

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
//                .defaultSystem("당신은 교육 튜터입니다. 개념을 명확하고 간단하게 설명하세요") // 역할 부여(System message)
                .defaultSystem(resource)
                .build();
    }

}

package jp.kopher1601.springai.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Configuration
public class AppConfig {

    private final VectorStore vectorStore;
    private final JdbcClient jdbcClient;

    // classpath -> resources 에서 시작
    @Value("classpath:data.txt")
    private Resource resource;

    @Autowired
    public AppConfig(VectorStore vectorStore, JdbcClient jdbcClient) {
        this.vectorStore = vectorStore;
        this.jdbcClient = jdbcClient;
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
//                .defaultSystem("당신은 교육 튜터입니다. 개념을 명확하고 간단하게 설명하세요") // 역할 부여(System message)
//                .defaultSystem(resource)
                .build();
    }

    @PostConstruct
    public void init() throws InterruptedException, IOException {
        Integer count=jdbcClient.sql("select count(*) from hotel_vector")
                .query(Integer.class)
                .single();
        System.out.println("No of Records in the PG Vector Store="+count);
        if(count==0){
            List<Document> documents = Files.lines(resource.getFile().toPath())
                    .map(Document::new)
                    .toList();
            TextSplitter textSplitter = new TokenTextSplitter();
            for(Document document : documents) {
                List<Document> splitteddocs = textSplitter.split(document);
                System.out.println("before adding document: " + document.getText());
                vectorStore.add(splitteddocs); // embedding
                System.out.println("Added document: " + document.getText());
                Thread.sleep(1000); // 1s
            }
            System.out.println("Application is ready to Serve the Requests");
        }
    }

}

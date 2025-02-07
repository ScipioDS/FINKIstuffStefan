package ddd.eshop.supportchat.domain.model;

import jakarta.persistence.*;

import javax.management.relation.Role;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private Role senderRole;

    public ChatMessage() {
        this.timestamp = LocalDateTime.now();
    }

    public ChatMessage(String sender, String content, Role senderRole) {
        this.sender = sender;
        this.content = content;
        this.senderRole = senderRole;
        this.timestamp = LocalDateTime.now();
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
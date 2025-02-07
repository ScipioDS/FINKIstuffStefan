package ddd.eshop.supportchat.domain.repository;

import ddd.eshop.supportchat.domain.model.ChatMessage;

import java.util.List;

public interface ChatRepository {
    List<ChatMessage> findTop50ByOrderByTimestampDesc();
    ChatMessage save(ChatMessage message);
}
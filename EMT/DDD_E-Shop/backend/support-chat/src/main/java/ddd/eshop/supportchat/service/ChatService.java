package ddd.eshop.supportchat.service;

import ddd.eshop.supportchat.domain.model.ChatMessage;

import java.util.List;

public interface ChatService {
    List<ChatMessage> getChatHistory();
    ChatMessage sendMessage(ChatMessage chatMessage);
}
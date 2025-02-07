package ddd.eshop.supportchat.service.impl;

import ddd.eshop.supportchat.domain.model.ChatMessage;
import ddd.eshop.supportchat.domain.repository.ChatRepository;
import ddd.eshop.supportchat.service.ChatService;

import java.util.List;

public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public List<ChatMessage> getChatHistory() {
        return chatRepository.findTop50ByOrderByTimestampDesc();
    }

    @Override
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatRepository.save(chatMessage);
    }
}

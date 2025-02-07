package ddd.eshop.supportchat.domain.repository.out;

import ddd.eshop.supportchat.domain.model.ChatMessage;
import ddd.eshop.supportchat.domain.repository.ChatRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaChatRepository extends JpaRepository<ChatMessage, Long>, ChatRepository {
    List<ChatMessage> findTop50ByOrderByTimestampDesc();//TODO:IMPLEMENTIRAJ JA SERVISNATA LOGIKA!
}
package oleg.klimov.WEBTestChat.services;

import oleg.klimov.WEBTestChat.entities.Message;
import oleg.klimov.WEBTestChat.entities.User;
import oleg.klimov.WEBTestChat.repositories.MessageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepos messageRepos;

    public List<Message> findAll() {
        return messageRepos.findAll();
    }

    public List<Message> findLast() {return messageRepos.findLast();}

    public void deleteMessageById(Long id) {messageRepos.deleteById(id);}

    public void updateMessage(Message message) {messageRepos.save(message);}

    public void newMessage(User author, String text, User recipient) {
        Message message=new Message();
        message.setAuthor(author);
        message.setText(text);
        message.setRecipient(recipient);
        message.setTime(LocalDateTime.now());
        messageRepos.save(message);
    }
}

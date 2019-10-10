package oleg.klimov.WEBTestChat.repositories;

import oleg.klimov.WEBTestChat.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepos extends JpaRepository<Message,Long> {
    @Query(value = "SELECT * FROM message ORDER BY ID DESC LIMIT 50", nativeQuery = true)
    List<Message> findLast();

}

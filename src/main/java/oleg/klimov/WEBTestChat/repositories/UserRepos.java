package oleg.klimov.WEBTestChat.repositories;

import oleg.klimov.WEBTestChat.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findById(long id);
}

package oleg.klimov.WEBTestChat.services;

import oleg.klimov.WEBTestChat.entities.Role;
import oleg.klimov.WEBTestChat.entities.User;
import oleg.klimov.WEBTestChat.repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepos userRepos;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepos.findByUsername(s);
    }

    public User findByUsername(String username){
       return userRepos.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepos.findAll();
    }

    public void deleteUserById(Long id){userRepos.deleteById(id);}

    public void newUser(User user) {
        user.setEnabled(true);
        user.setRoles(Collections.singleton(Role.ROLE_ADMIN));
        userRepos.save(user);
    }
    public void updateUser(User user) {
        userRepos.save(user);
    }

    public User findById(long id) {
        return userRepos.findById(id);
    }
}

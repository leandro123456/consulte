package com.lgg.nticxs.web.config.security.cookies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lgg.nticxs.web.DAO.UserDAO;

public class MyUserDetailsService implements UserDetailsService {
    private static List<UserObject> users = new ArrayList();
    private static UserDAO userdao = new UserDAO();

    public MyUserDetailsService() {
    	
    	List<com.lgg.nticxs.web.model.User> usuarios = userdao.retrieveAllUsers();
        //in a real application, instead of using local data,
        // we will find user details by some other means e.g. from an external system
    	for(com.lgg.nticxs.web.model.User us : usuarios)
    		users.add(new UserObject(us.getEmail(), new String(us.getPassword()), us.getRole()));
//        users.add(new UserObject("mike", "234", "ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserObject> user = users.stream()
                                         .filter(u -> u.name.equals(username))
                                         .findAny();
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(user.get());
    }

    private UserDetails toUserDetails(UserObject userObject) {
        return User.withUsername(userObject.name)
                   .password(userObject.password)
                   .roles(userObject.role).build();
    }

    private static class UserObject {
        private String name;
        private String password;
        private String role;

        public UserObject(String name, String password, String role) {
            this.name = name;
            this.password = password;
            this.role = role;
        }
    }
}

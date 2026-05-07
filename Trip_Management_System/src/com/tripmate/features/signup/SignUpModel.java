package com.tripmate.features.signup;

import com.tripmate.data.dto.Credential;
import com.tripmate.data.dto.User;
import com.tripmate.data.dto.UserRole;
import com.tripmate.data.repository.TripMateDB;
import java.util.List;
import java.util.UUID;

public class SignUpModel {
    private final TripMateDB db = TripMateDB.getInstance();

    public boolean register(String name, String email, String phone, String password) {
        List<User> users = db.getUsers();

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }

        boolean isFirstUser = (users.isEmpty());

        String userId = UUID.randomUUID().toString();
        User newUser = new User(userId, name, email, phone, UserRole.UserStatus.ACTIVE, System.currentTimeMillis());
        Credential credential = new Credential(userId, email, password, true);

        db.getUsers().add(newUser);
        db.getCredentials().add(credential);

        String roleId = isFirstUser ? "R_ADMIN" : "R_CUSTOMER";
        db.getUserRoles().add(new UserRole(userId, roleId));

        return true;
    }

    public boolean register(String name, String email, String phone, String password, String roleId) {
        List<User> users = db.getUsers();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }

        String userId = UUID.randomUUID().toString();
        User newUser = new User(userId, name, email, phone, UserRole.UserStatus.ACTIVE, System.currentTimeMillis());
        Credential credential = new Credential(userId, email, password, true);

        db.getUsers().add(newUser);
        db.getCredentials().add(credential);
        db.getUserRoles().add(new UserRole(userId, roleId));

        return true;
    }
}

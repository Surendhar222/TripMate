package com.tripmate.features.signin;

import com.tripmate.data.dto.Credential;
import com.tripmate.data.dto.User;
import com.tripmate.data.repository.TripMateDB;
import java.util.List;

public class SignInModel {
    private final TripMateDB db = TripMateDB.getInstance();

    public User authenticate(String email, String password) {
        List<Credential> allCredentials = db.getCredentials();
        Credential foundCredential = null;

        for (Credential c : allCredentials) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password)) {
                foundCredential = c;
                break;
            }
        }

        if (foundCredential != null) {
            List<User> allUsers = db.getUsers();
            for (User u : allUsers) {
                if (u.getUserId().equals(foundCredential.getUserId())) {
                    return u;
                }
            }
        }
        return null;
    }
}

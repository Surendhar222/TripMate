package com.tripmate.features.signup;

import com.tripmate.util.ParseHelper;
import com.tripmate.util.Validator;

public class SignUpView {

    private final SignUpModel model = new SignUpModel();

    public void signUpScreen() {
        System.out.println("\n--- TripMate Registration ---");
        registration(null);
    }

    public void adminCreateUser() {
        System.out.println("\n--- Admin: Create User ---");
        System.out.println("1. Create Admin");
        System.out.println("2. Create Customer");

        int choice = ParseHelper.readInt("Choice: ");

        String roleId = (choice == 1) ? "R_ADMIN" : "R_CUSTOMER";
        registration(roleId);
    }

    private void registration(String roleId) {
        String name = getValidName();
        String email = getValidEmail();
        String phone = getValidPhone();
        String password = getValidPassword();
        boolean success;

        if (roleId == null) {
            success = model.register(name, email, phone, password);
        } else {
            success = model.register(name, email, phone, password, roleId);
        }

        if (success) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Registration failed [This Email was Already Registered].");
        }
    }

    private String getValidName() {
        while (true) {
            String name = ParseHelper.readString("Enter full name: ");
            if (Validator.isValidName(name)) {
                return name;
            }
            System.out.println("Invalid name Enter Valid Name.");
        }
    }

    private String getValidEmail() {
        while (true) {
            String email = ParseHelper.readString("Enter email: ");
            if (Validator.isValidEmail(email)) {
                return email;
            }
            System.out.println("Invalid email, Enter Valid Mail.");
        }
    }

    private String getValidPhone() {
        while (true) {
            String phone = ParseHelper.readString("Enter phone: ");
            if (Validator.isValidPhone(phone)) {
                return phone;
            }
            System.out.println("Invalid phone, Enter 10 Digit Valid Number.");
        }
    }

    private String getValidPassword() {
        while (true) {
            String password = ParseHelper.readString("Enter password: ");

            if (Validator.isValidPassword(password)) {
                return password;
            }
            System.out.println("Weak password, Minimum 8 characters and mix of Letters, Numbers, Symbols.");
        }
    }
}
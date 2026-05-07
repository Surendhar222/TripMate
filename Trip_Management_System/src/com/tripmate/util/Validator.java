package com.tripmate.util;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]{3,50}$");

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[6-9][0-9]{9}$");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");

    private static final Pattern HOTEL_NAME_PATTERN = Pattern.compile("^[A-Za-z0-9 .,&'-]{3,50}$");

    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^.{10,200}$");


    public static boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidHotelName(String hotelName) {
        return HOTEL_NAME_PATTERN.matcher(hotelName).matches();
    }

    public static boolean isValidDescription(String description) {
        return DESCRIPTION_PATTERN.matcher(description).matches();
    }

    public static boolean isValidRating(double rating) {
        return rating >= 0 && rating <= 5;
    }

    public static boolean isValidRoomCount(int count) {
        return count > 0;
    }

    public static boolean isValidPrice(double price) {
        return price > 0;
    }
}
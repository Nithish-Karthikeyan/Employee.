package com.ideas2it.enums;

/**
 * Enums for selecting gender
 * based on the userchoice gender is assigned
 */
public enum Gender {
    MALE("1"),
    FEMALE("2");

    private String value;

    Gender(String value) {
        this.value = value;
    }
  
    public static Gender getEmployeeGender(String index) {
        Gender choice = null;
        for (Gender gender: Gender.values()) {
            if(gender.value.equals(index)) {
                choice = gender;
            }
        }        
        return choice;
    }
}
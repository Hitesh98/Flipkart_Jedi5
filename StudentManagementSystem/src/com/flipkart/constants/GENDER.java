package com.flipkart.constants;

/**
 * The enum Gender.
 */
public enum GENDER {
    /**
     * Male gender.
     */
    Male,
    /**
     * Female gender.
     */
    Female;

    /**
     * Value of gender as a String
     *
     * @param label the Gender to query as a string
     * @return the matching gender
     */
    public static GENDER valueOfGender(String label) {
        for (GENDER e : values()) {
            if (e.name().toLowerCase().equals(label.toLowerCase())) {
                return e;
            }
        }
        return null;
    }
}

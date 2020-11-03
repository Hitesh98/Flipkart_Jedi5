package com.flipkart.constants;

/**
 * The enum Usertype.
 */
public enum USERTYPE {
    /**
     * Admin usertype.
     */
    Admin,
    /**
     * Professor usertype.
     */
    Professor,
    /**
     * Student usertype.
     */
    Student;

    /**
     * Value of usertype as String.
     *
     * @param label the usertype as String to search
     * @return the matching usertype
     */
    public static USERTYPE valueOfType(String label) {
        for (USERTYPE e : values()) {
            if (e.name().toLowerCase().equals(label.toLowerCase())) {
                return e;
            }
        }
        return null;
    }
}

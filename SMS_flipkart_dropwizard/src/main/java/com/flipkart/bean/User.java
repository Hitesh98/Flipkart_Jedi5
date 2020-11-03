package com.flipkart.bean;

import com.flipkart.constants.USERTYPE;

/**
 * The type User.
 */
public class User {

    private long userId;
    private String username;
    private USERTYPE type;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() { return userId; }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) { this.userId = userId; }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Gets user type.
     *
     * @return the user type
     */
    public USERTYPE getType() { return type; }

    /**
     * Sets user type.
     *
     * @param type the user type
     */
    public void setType(USERTYPE type) { this.type = type; }

}
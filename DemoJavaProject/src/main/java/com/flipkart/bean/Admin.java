package com.flipkart.bean;

import com.flipkart.constants.GENDER;
import com.flipkart.constants.USERTYPE;

/**
 * The type Admin.
 */
public class Admin extends User {


    private int adminID;
    private String name;
    private GENDER gender;

    /**
     * Constructor to initialise userType to Admin and other fields to deafult values
     */
    public Admin() {
        super();
        this.setType(USERTYPE.Admin);
        this.adminID = 0;
        this.gender = null;
        this.name = null;
    }

    /**
     * Gets gender.
     *
     * @return the Admin's gender
     */
    public GENDER getGender() { return gender; }

    /**
     * Sets gender.
     *
     * @param gender the Admin's gender
     */
    public void setGender(GENDER gender) { this.gender = gender; }

    /**
     * Gets name.
     *
     * @return the Admin's name
     */
    public String getName() { return name; }

    /**
     * Sets name.
     *
     * @param name the Admin's name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets admin id.
     *
     * @return the admin id
     */
    public int getAdminID() {
        return adminID;
    }

    /**
     * Sets admin id.
     *
     * @param adminID the admin id
     */
    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
}

package com.flipkart.bean;

import com.flipkart.constants.GENDER;
import com.flipkart.constants.USERTYPE;

/**
 * The type Professor.
 */
public class Professor extends User {

    private int professorId;
    private String name;
    private String email;
    private GENDER gender;

    /**
     * Constructor to initialise userType to Professor and other fields to default values
     */
    public Professor() {
        super();
        this.setType(USERTYPE.Professor);
        this.professorId = 0;
        this.name = null;
        this.email = null;
        this.gender = null;
    }

    /**
     * Gets professor's gender.
     *
     * @return the professor's gender
     */
    public GENDER getGender() { return gender; }

    /**
     * Sets gender.
     *
     * @param gender the professor's gender
     */
    public void setGender(GENDER gender) { this.gender = gender; }

    /**
     * Gets professor's id.
     *
     * @return the professor's id
     */
    public int getProfessorId() { return professorId; }

    /**
     * Sets professor's id.
     *
     * @param professorId the professor's id
     */
    public void setProfessorId(int professorId) { this.professorId = professorId; }

    /**
     * Gets professor's email.
     *
     * @return the professor's email
     */
    public String getEmail() { return email; }

    /**
     * Sets professor's email.
     *
     * @param email the professor's email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets professor's name.
     *
     * @return the professor's name
     */
    public String getName() { return name; }

    /**
     * Sets professor's name.
     *
     * @param name the professor's name
     */
    public void setName(String name) { this.name = name; }

}

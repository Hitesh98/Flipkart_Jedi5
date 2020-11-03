package com.flipkart.business;

import com.flipkart.bean.User;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Views all courses in the course catalog
     */
    void viewCourseCatalog();

    /**
     * Registers user (student, professor and admin)
     *
     * @param user      User Object of the user to registerUser
     * @param password  Password to set for the user
     * @return true if user was registered, else false.
     */
    boolean registerUser(User user, String password);
}

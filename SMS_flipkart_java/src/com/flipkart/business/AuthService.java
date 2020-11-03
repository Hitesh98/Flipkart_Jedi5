package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.USERTYPE;
import com.flipkart.exception.UserNotFoundException;

/**
 * The interface Auth service.
 */
public interface AuthService {

    /**
     * Login user (student, professor and admin)
     *
     * @param username  username
     * @param password  respective password
     * @return type of user if login was successful, else null
     * @throws UserNotFoundException if no such user exists
     */
    public USERTYPE login(String username, String password) throws UserNotFoundException;

}

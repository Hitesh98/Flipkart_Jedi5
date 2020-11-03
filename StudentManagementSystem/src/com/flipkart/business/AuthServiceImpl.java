package com.flipkart.business;

import com.flipkart.constants.USERTYPE;
import com.flipkart.dao.AuthDAO;
import com.flipkart.dao.AuthDAOImpl;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

/**
 * The type Auth service.
 */
public class AuthServiceImpl implements AuthService {

    private static Logger logger = Logger.getLogger(AuthServiceImpl.class);
    private static AuthDAO authDao = new AuthDAOImpl();

    /**
     * Login user (student, professor and admin)
     *
     * @param username  username
     * @param password  respective password
     * @return type of user if login was successful, else null
     */
    @Override
    public USERTYPE login(String username, String password) {
        USERTYPE type;
        try {
        type = authDao.login(username, password);
        if (type== null) throw new UserNotFoundException("Invalid Credentials.");
        } catch (UserNotFoundException ex) {
            logger.error("User does not exist" + ex.getMessage());
            return null;
        }
        return type;
    }
}

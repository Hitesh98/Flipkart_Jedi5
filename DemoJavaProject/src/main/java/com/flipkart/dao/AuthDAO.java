package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.USERTYPE;


/**
 * The interface Auth dao.
 */
public interface AuthDAO {

    /**
     * Login User
     *
     * @param username the username
     * @param password the password
     * @return the usertype of the user logged in.
     */
    USERTYPE login(String username, String password);

    /**
     * Register student.
     *
     * @param student  the student to register
     * @param password the password
     * @return true is registered successfully, else false.
     */
// registerUser as student
    public boolean registerStudent(Student student, String password);

    /**
     * Register professor.
     *
     * @param professor the professor  to register
     * @param password  the password
     * @return true is registered successfully, else false.
     */
// registerUser as professor
    public boolean  registerProfessor(Professor professor, String password);

    /**
     * Register admin.
     *
     * @param admin    the admin  to register
     * @param password the password
     * @return true is registered successfully, else false.
     */
// registerUser as admin
    public boolean registerAdmin(Admin admin, String password);
}

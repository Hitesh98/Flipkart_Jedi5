package com.flipkart.app;

import com.flipkart.Utils.DBUtil;
import com.flipkart.bean.*;
import com.flipkart.business.AuthServiceImpl;
import com.flipkart.business.UserServiceImpl;
import com.flipkart.constants.GENDER;
import com.flipkart.constants.USERTYPE;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * The Student management system application client.
 */
public class SMSApplicationClient {

    private static Scanner sc = new Scanner(System.in);
    private static Logger logger = Logger.getLogger(SMSApplicationClient.class);

    /**
     * The Is logged in boolean variable.
     */
    static boolean isLoggedIn;
    static boolean isRunning = true;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        logger.info("Welcome to student management system!!");
        do {
            SMSApplicationClient.displayMenu();
        }while (isRunning);
        logger.info("Exited");
        sc.close();
    }

    // Show menu for user
    private static void displayMenu() {
        int choice;
        logger.info("1) login");
        logger.info("2) student registration");
        logger.info("any other number to exist");
        choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                try {
                    registerStudent();
                    break;
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            default:
                isRunning = false;
                DBUtil.closeConnection();
        }
    }


    private static void registerStudent() {
        try {

            Student student = new Student();
            logger.info("Enter usrname of your choice : ");
            student.setUsername(sc.nextLine());
            logger.info("Enter usrid of your choice : ");
            student.setUserId(Integer.parseInt(sc.nextLine()));
            logger.info("Enter student id");
            student.setStudentId(Integer.parseInt(sc.nextLine()));
            logger.info("Enter student name");
            student.setName(sc.nextLine());
            logger.info("Enter password");
            String password = sc.nextLine();
            logger.info("Enter branch");
            student.setBranch(sc.nextLine());
            logger.info("Enter gender: 'm' for male and 'f' for female");
            String s = sc.nextLine();
            if (s.equals("m"))
                student.setGender(GENDER.Male);
            else
                student.setGender(GENDER.Female);
            logger.info("Enter semester:");
            student.setSemester(Integer.parseInt(sc.nextLine()));

            UserServiceImpl userService = new UserServiceImpl();
            if(userService.registerUser(student, password)) {
                logger.info("Student registered successfully!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private static void login() {
            logger.info("Enter username");
            String username = sc.nextLine();
            logger.info("Enter password");
            String password = sc.nextLine();
            AuthServiceImpl authService = new AuthServiceImpl();
            try {
                USERTYPE userType = authService.login(username, password);
                if (userType == null) {
                    throw new UserNotFoundException("No such user exists. Try Again!");
                }
                isLoggedIn = true;
                logger.info(username + " logged in as " + userType);
                logger.info(username + " logged in at : " + (new Date()));
                switch(userType.name().toLowerCase()) {
                    case "student":
                        StudentApplicationClient studentApplicationClient = new StudentApplicationClient();
                        StudentDAOImpl studentDao = new StudentDAOImpl();

                        // get student details from db
                        Student student = studentDao.getStudentDetails(username);

                        // displayMenu and perform student menu operations
                        studentApplicationClient.displayMenu(student);
                        break;

                    case "professor":
                        ProfessorApplicationClient professorApplicationClient = new ProfessorApplicationClient();
                        ProfessorDAO professorDao = new ProfessorDAOImpl();

                        // get professor details from db
                        Professor professor = professorDao.getProfessorDetails(username);

                        // displayMenu and perform professor menu operations
                        professorApplicationClient.displayMenu(professor);
                        break;

                    case "admin":
                        AdminApplicationClient adminApplicationClient = new AdminApplicationClient();
                        adminApplicationClient.displayMenu();
                        break;

                    default:
                        logger.debug("No such user. Try Again!");
                }
            }catch (Exception e) {
                logger.error(e.getMessage());
            }
    }

    /**
     * Logout.
     */
// Logout method
    public static void logout() {
        isLoggedIn = false;
        logger.info("##################### Logged out at: " + new Date() + " ###################");
        SMSApplicationClient.displayMenu();
    }
}

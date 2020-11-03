package com.flipkart.app;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.*;
import com.flipkart.constants.GENDER;
import com.flipkart.constants.USERTYPE;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * The Admin application client.
 */
public class AdminApplicationClient {

    private static Logger logger = Logger.getLogger(AdminApplicationClient.class);
    private AdminService adminService = new AdminServiceImpl();
    private AuthService authService = new AuthServiceImpl();
    private UserService userService = new UserServiceImpl();
    private Scanner sc = new Scanner(System.in);

    /**
     * Display Admin options menu.
     */
    public void displayMenu() {
        int option;
        do {
            displayOptions();
            option = Integer.parseInt(sc.nextLine());
            switch(option) {
                case 1:
                    userService.viewCourseCatalog();
                    break;
                case 2:
                    adminService.getAllUsers();
                    break;
                case 3:
                    assignCourseToProfessor();
                    break;
                case 4:
                    addNewUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    addNewCourse();
                    break;
                case 7:
                    dropCourse();
                    break;
                case 0:
                    SMSApplicationClient.logout();
            }

        }while(SMSApplicationClient.isLoggedIn);
        sc.close();
    }

    /**
     * Show available choices
     */

    private void displayOptions() {
        logger.info("Enter your choice : ");
        logger.info("1. View all courses");
        logger.info("2. View all users");
        logger.info("3. Assign course to a professor");
        logger.info("4. Register new user");
        logger.info("5. Delete user");
        logger.info("6. Add new course");
        logger.info("7. Drop course");
        logger.info("0. Logout");

    }

    // Gather required inputs to assign a course with a professor
    private void assignCourseToProfessor() {
        logger.info("Enter Professor Id : ");
        int professorId = Integer.parseInt(sc.nextLine());
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        logger.info("Enter Course Id : ");
        int courseId = Integer.parseInt(sc.nextLine());

        adminService.assignCourseToProfessor(professor, courseId);
    }

    // Gather required inputs to add new courses
    private void addNewCourse() {
        Course course = new Course();
        logger.info("Enter Course Id : ");
        int courseId = Integer.parseInt(sc.nextLine());
        course.setCourseId(courseId);
        logger.info("Enter Course Name : ");
        String courseName = sc.nextLine();
        course.setCourseName(courseName);
        logger.info("Enter Description : ");
        String description = sc.nextLine();
        course.setDescription(description);
        logger.info("Enter fee for the course : ");
        int fees = Integer.parseInt(sc.nextLine());
        course.setFees(fees);

        adminService.addNewCourse(course);
    }

    // Gather course id to delete a course
    private void dropCourse() {
        logger.info("Enter course Id : ");
        int courseId = Integer.parseInt(sc.nextLine());
        Course course = new Course();
        course.setCourseId(courseId);
        adminService.deleteCourse(course);
    }

    // Gather required inputs to add a new user
    private void addNewUser() {

        logger.info("1 - add new admin");
        logger.info("2 - add new professor");
        logger.info("3 - add new student");
        int option = Integer.parseInt(sc.nextLine());
        logger.info("Enter username");
        String username = sc.nextLine();
        logger.info("Enter userid");
        int userid = Integer.parseInt(sc.nextLine());

        switch(option) {
            case 1:
                Admin admin = new Admin();
                admin.setUserId(userid);
                admin.setUsername(username);
                logger.info("Enter admin id : ");
                admin.setAdminID(Integer.parseInt(sc.nextLine()));
                logger.info("Enter admin name : ");
                admin.setName(sc.nextLine());
                logger.info("Enter password : ");
                String password = sc.nextLine();
                logger.info("Enter gender: 'm' for male and 'f' for female : ");
                String gender = sc.nextLine();
                if(gender.equals("m")) {
                    admin.setGender(GENDER.Male);
                }
                else if (gender.equals("f")) {
                    admin.setGender(GENDER.Female);
                } else {
                    logger.error("Enter proper gender. Try Again!");
                    break;
                }
                userService.registerUser(admin, password);
                break;

            case 2:
                Professor professor = new Professor();
                professor.setUserId(userid);
                professor.setUsername(username);
                logger.info("Enter professor id : ");
                professor.setProfessorId(Integer.parseInt(sc.nextLine()));
                logger.info("Enter professor name : ");
                professor.setName(sc.nextLine());
                logger.info("Enter professor email : ");
                professor.setEmail(sc.nextLine());
                logger.info("Enter password : ");
                password = sc.nextLine();
                logger.info("Enter gender: 'm' for male and 'f' for female : ");
                gender = sc.nextLine();
                if(gender.equals("m")) {
                    professor.setGender(GENDER.Male);
                }
                else if (gender.equals("f")) {
                    professor.setGender(GENDER.Female);
                } else {
                    logger.error("Enter proper gender. Try Again!");
                    break;
                }

                userService.registerUser(professor, password);
                break;

            case 3:
                Student student = new Student();
                student.setUserId(userid);
                student.setUsername(username);
                logger.info("Enter student id : ");
                student.setStudentId(Integer.parseInt(sc.nextLine()));
                logger.info("Enter student name : ");
                student.setName(sc.nextLine());
                logger.info("Enter password : ");
                password = sc.nextLine();
                logger.info("Enter branch : ");
                student.setBranch(sc.nextLine());
                logger.info("Enter semester:");
                student.setSemester(Integer.parseInt(sc.nextLine()));
                logger.info("Enter gender: 'm' for male and 'f' for female");
                gender = sc.nextLine();
                if(gender.equals("m")) {
                    student.setGender(GENDER.Male);
                }
                else if (gender.equals("f")) {
                    student.setGender(GENDER.Female);
                } else {
                    logger.error("Enter proper gender. Try Again!");
                    break;
                }
                userService.registerUser(student, password);
                break;
        }
    }

    private void deleteUser() {
        logger.info("Enter user id:");
        adminService.deleteUser(Integer.parseInt(sc.nextLine()));
    }
}

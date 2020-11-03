package com.flipkart.app;

import com.flipkart.bean.Professor;
import com.flipkart.business.ProfessorService;
import com.flipkart.business.ProfessorServiceImpl;
import com.flipkart.business.UserService;
import com.flipkart.business.UserServiceImpl;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * The Professor application client.
 */
public class ProfessorApplicationClient {

    private static Logger logger = Logger.getLogger(ProfessorApplicationClient.class);
    private ProfessorService professorService = new ProfessorServiceImpl();
    private ProfessorDAO professorDAO = new ProfessorDAOImpl();
    private UserService userService = new UserServiceImpl();
    private Scanner sc = new Scanner(System.in);

    /**
     * Display Professor options menu.
     *
     * @param professor the professor
     */
// Display menu for professor
    public void displayMenu(Professor professor) {
        int option;
        do {
            displayOptions();
            option = Integer.parseInt(sc.nextLine());
            switch(option) {
                case 1:
                    userService.viewCourseCatalog();
                    break;
                case 2:
                    professorService.getAssignedCourse(professor);
                    break;
                case 3:
                    professorService.viewAssignedStudents(professor);
                    break;
                case 4:
                    logger.info("Enter course Id: ");
                    int courseId = Integer.parseInt(sc.nextLine());
                    logger.info("Enter student Id: ");
                    int studentId = Integer.parseInt(sc.nextLine());
                    logger.info("Enter grades:");
                    String grades = sc.nextLine();
                    professorService.recordStudentGrades(professor, studentId, grades, courseId);
                    break;
                case 0:
                    SMSApplicationClient.logout();
            }

        }while(SMSApplicationClient.isLoggedIn);
        sc.close();
    }

    private void displayOptions() {
        logger.info("Enter your choice:");
        logger.info("1. View all courses");
        logger.info("2. View assigned courses");
        logger.info("3. View all students to teach");
        logger.info("4. Grade student");
        logger.info("0. Logout");
    }
}

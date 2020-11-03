package com.flipkart.app;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.business.StudentService;
import com.flipkart.business.StudentServiceImpl;
import com.flipkart.business.UserService;
import com.flipkart.business.UserServiceImpl;
import com.flipkart.exception.CourseNotFoundException;
import org.apache.log4j.Logger;


import java.util.Scanner;

/**
 * The Student application client.
 */
public class StudentApplicationClient {

    private static Logger logger = Logger.getLogger(StudentApplicationClient.class);
    private static Student student;
    private static StudentService studentService = new StudentServiceImpl();
    private static UserService userService = new UserServiceImpl();
    private static Scanner sc = new Scanner(System.in);

    /**
     * Display student options menu.
     *
     * @param student the student
     */
// Display the Student Menu
    public void displayMenu(Student student) {
        int option;
        do {
            this.student = student;
            displayOptions();
            option = Integer.parseInt(sc.nextLine());
            try {
                switch (option) {
                    case 1:
                        userService.viewCourseCatalog();
                        break;
                    case 2:
                        selectCourse();
                        break;
                    case 3:
                        dropCourse();
                        break;
                    case 4:
                        studentService.viewRegisteredCourses(student);
                        break;
                    case 5:
                        payFees();
                        break;
                    case 6:
                        printReportCard();
                        break;
                    case 0:
                        SMSApplicationClient.logout();
                        break;
                    default:
                        logger.error("Enter correct option. Try again!");
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }

        }while(SMSApplicationClient.isLoggedIn);
        sc.close();
    }

    // Show choices
    private static void displayOptions() {
        logger.info("Enter your choice:");
        logger.info("1. View all courses");
        logger.info("2. Select course");
        logger.info("3. Drop course");
        logger.info("4. View registered courses");
        logger.info("5. Pay fees");
        logger.info("6. View Report Card");
        logger.info("0. Logout");
    }

    // Gets course id to add for student
    private static void selectCourse() {
        if(studentService.getNumberOfCoursesRegistered(student) >= 4) {
            logger.info("Course limit reached!!");
        }
        else {
            logger.info("Enter courseId");
            int courseId = Integer.parseInt(sc.nextLine());
            studentService.selectCourse(student, courseId);
        }

    }

    // Gets course id to delete from student's registered courses
    private void dropCourse() throws CourseNotFoundException {
        Course course = new Course();
        logger.info("Enter course id:");
        int courseId = Integer.parseInt(sc.nextLine());
        course.setCourseId(courseId);
        studentService.dropCourse(course, student);
    }

    // print report card for student
    private void printReportCard() {
        studentService.viewReportCard(student);
    }

    // Get amount to be paid and make payment
    private void payFees() {
        int fee = studentService.getTotalFee(student);
        logger.info("To pay Rs."+ fee + " press 'y' to continue else press 'n'...");
        if(sc.nextLine().equals("y")) {
            logger.info("Enter 1 to pay with credit card.");
            logger.info("Enter 2 to pay with debit card.");
            logger.info("Enter 3 to pay as cash.");
            int feeMethod = Integer.parseInt(sc.nextLine());
            if(feeMethod > 0 && feeMethod < 4) {
                studentService.makePayment(student, feeMethod, fee);
            }
            else {
                logger.info("Invalid Mode of payment. Try Again!");
            }
        }
        else {
            logger.info("Payment Unsuccessful");
        }
    }
}

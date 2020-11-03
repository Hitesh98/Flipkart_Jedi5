package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * The interface Student dao.
 */
public interface StudentDAO {

    /**
     * Select course for student
     *
     * @param student  the student
     * @param courseId the course id of course to select
     */
    void selectCourse(Student student, int courseId);

    /**
     * Gets student details.
     *
     * @param username the username of student
     * @return the student details
     * @throws UserNotFoundException if the user does nto exist
     */
    Student getStudentDetails(String username)  throws UserNotFoundException;


    /**
     * View registered courses of the student
     *
     * @param student the student
     * @return the list of registere courses
     */
    List<Course> viewRegisteredCourses(Student student);

    /**
     * Drop course.
     *
     * @param courseId  the course id of course to drop
     * @param studentId the student id of student to drop the course for
     * @throws CourseNotFoundException if the course does not exist
     */
    void dropCourse(int courseId, int studentId) throws CourseNotFoundException;

    /**
     * Gets number of courses registered for by the student
     *
     * @param student the student
     * @return the number of courses registered for
     */
    int getNumberOfCoursesRegistered(Student student) ;

    /**
     * View student report card.
     *
     * @param student the student
     * @return the report card of the student
     */
    Map<String, String> viewReportCard(Student student);

    /**
     * Gets total fee.
     *
     * @param student the student
     * @return the total fee to be paid of already paid by the student
     */
    int getTotalFee(Student student);

    /**
     * Make payment boolean.
     *
     * @param student       the student
     * @param paymentMethod the payment mode
     * @param fees          the fees to be paid
     * @return true is payment was successful else false
     */
    boolean makePayment(Student student, int paymentMethod, int fees);
}

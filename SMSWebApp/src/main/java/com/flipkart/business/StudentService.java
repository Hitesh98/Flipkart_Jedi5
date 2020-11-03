package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * The interface Student service.
 */
public interface StudentService {

    /**
     * Gets the number of courses a student has registered for.
     *
     * @param student Student object of the student ot query for
     * @return number of courses
     */
    int getNumberOfCoursesRegistered(Student student);

    /**
     * Registers a student for a particular course
     * @param student Student to register the course for.
     * @param courseId CourseID of the course being selected by the student
     * @return true if course selected, else false.
     */

    boolean selectCourse(Student student, int courseId);

    /**
     * drops a course for a student
     *
     * @param course course to drop
     * @param student Student for whom the course is to be dropped
     * @throws CourseNotFoundException if No such course exists
     * @return true if course deleted, else false.
     */
    boolean dropCourse(Course course, Student student) throws CourseNotFoundException;

    /**
     * displays all courses the student has registered for.
     *
     * @param student The student for whom to query for.
     */
    List<Course> viewRegisteredCourses(Student student);

    /**
     * Displays student report card
     *
     * @param student Student whose report card is to be displayed
     */
    Map<String, String> viewReportCard(Student student);

    /**
     * Gets total fees the student needs to pay or has paid.
     *
     * @param student The student whose fees needs to be queried
     * @return The calculated fees
     */
    int getTotalFee(Student student);

    /**
     * Pays student fees.
     *
     * @param student The student making the payment
     * @param paymentMethod Mode of payment selected
     * @param fees Total fees being paid
     * @return True if payment was successful, else false
     */
    boolean makePayment(Student student, int paymentMethod, int fees);
}

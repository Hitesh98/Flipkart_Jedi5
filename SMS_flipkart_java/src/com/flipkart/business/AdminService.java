package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

/**
 * The interface Admin service.
 */
public interface AdminService {

    /**
     * Add new User
     * @param user User object of the new user to be added
     * @param password user password
     */
    void addNewUser(User user, String password);

    /**
     * Delete existing User
     * @param userId    Id of user to be deleted
     * @return true if user was successfully deleted, false if no such user exists.
     */
    boolean deleteUser(int userId);

    /**
     * Assign course to professor
     *
     * @param professor Professor object to which the course is to be assigned
     * @param courseId  course ID of the course to be assigned
     * @return true if course was assigned succesfull, else false
     */
    boolean assignCourseToProfessor(Professor professor, int courseId);

    /**
     * Add a new course in the course catalog
     *
     * @param course    new course to be added in the course catalog
     * @return true if added successfully, else false.
     */
    boolean addNewCourse(Course course);

    /**
     * Delete an existing course
     *
     * @param course The course to be deleted
     * @return true if course was deleted successfully, else false.
     */
    boolean deleteCourse(Course course);

    /**
     * Print All Users type wise
     */
    void getAllUsers();
}

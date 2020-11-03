package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;

import java.util.List;

/**
 * The interface Admin dao.
 */
public interface AdminDAO {

    /**
     * Gets All users.
     *
     * @return list of all the users
     */
    List<User> getUsers();

    /**
     * Assign course to professor.
     *
     * @param professor the professor to assign the course to
     * @param courseId  the course id of the course being assigned
     */
    void assignCourseToProfessor(Professor professor, int courseId);

    /**
     * Add new course to course catalog.
     *
     * @param course the course to add
     * @return true iff course was added successfully else false
     */
    boolean addNewCourse(Course course);

    /**
     * Update professor for course
     *
     * @param professorId the new professor id
     * @param courseId    the course id of the course to update
     * @return the boolean
     */
    boolean updateProfessorForCourse(int professorId, int courseId);

    /**
     * Delete course.
     *
     * @param course the course to delete
     */
// Delete course from course catalog
    void deleteCourse(Course course);

    /**
     * Delete user.
     *
     * @param userId the user id of user to delete
     */
//Delete a user
    void deleteUser(int userId);
}

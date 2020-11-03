package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

import java.sql.ResultSet;
import java.util.List;

/**
 * The interface Professor dao.
 */
public interface ProfessorDAO {

    /**
     * Gets all students assigned to professor.
     *
     * @param professor the professor to query students for
     */
    ResultSet getStudents(Professor professor);

    /**
     * Gets professor details.
     *
     * @param username the username of the professor
     * @return the professor details
     */
    Professor getProfessorDetails(String username);

    /**
     * Gets courses taught by professor.
     *
     * @param professorID the professor id
     * @return list of the courses taught by professor
     */
    List<Course> getCoursesTaughtByProfessor(int professorID);

    /**
     * Records student grades
     *
     * @param professor The professor recording the grades
     * @param studentId StudentID of the student whose grades need to be recorded
     * @param grade Grades being assigned to the student for the course
     * @param courseId The course for which the student is being graded
     */
    void recordStudentGrades(Professor professor, int studentId, String grade, int courseId);

    /**
     * Verify professor to course registration.
     *
     * @param professorID the professor id
     * @param courseId    the course id
     * @return true if professor has been assigned the course, else false
     */
    public boolean verifyProfessorToCourseRegistration(int professorID , int courseId);
}

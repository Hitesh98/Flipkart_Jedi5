package com.flipkart.business;

import com.flipkart.bean.Professor;

/**
 * The interface Professor service.
 */
public interface ProfessorService {

    /**
     * Method to view all the students assigned to a professor
     *
     * @param professor The professor Object of the professor to query students for.
     */
    void viewAssignedStudents(Professor professor);

    /**
     * Method to record student grades
     *
     * @param professor The professor recording the grades
     * @param studentId StudentID of the student whose grades need to be recorded
     * @param grades    Grades being assigned to the student for the course
     * @param courseId  The course for which the student is being graded
     */
    void recordStudentGrades(Professor professor, int studentId, String grades, int courseId);

    /**
     * Method to get all courses assigned to a professor
     *
     * @param professor Professor Object of the professor to query courses for.
     */
    void getAssignedCourse(Professor professor);
}

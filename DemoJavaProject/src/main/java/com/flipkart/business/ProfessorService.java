package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

/**
 * The interface Professor service.
 */
public interface ProfessorService {

    /**
     * Method to view all the students assigned to a professor
     *
     * @param professor The professor Object of the professor to query students for.
     * @return list of assigned students
     */
    List<Student> viewAssignedStudents(Professor professor);

    /**
     * Method to record student grades
     *
     * @param professor The professor recording the grades
     * @param studentId StudentID of the student whose grades need to be recorded
     * @param grades    Grades being assigned to the student for the course
     * @param courseId  The course for which the student is being graded
     * @return True if grades recorded, else false.
     */
    boolean recordStudentGrades(Professor professor, int studentId, String grades, int courseId);

    /**
     * Method to get all courses assigned to a professor
     *
     * @param professor Professor Object of the professor to query courses for.
     * @return List of assigned courses
     */
    List<Course> getAssignedCourse(Professor professor);
}

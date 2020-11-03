package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.dao.ProfessorDAO;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;

/**
 * The type Professor service.
 */
public class ProfessorServiceImpl implements ProfessorService {

    private static Logger logger = Logger.getLogger(ProfessorServiceImpl.class);
    private static ProfessorDAO professorDao = new ProfessorDAOImpl();

    /**
     * Method to view all the students assigned to a professor
     *
     * @param professor The professor Object of the professor to query students for.
     */
    @Override
    public void viewAssignedStudents(Professor professor) {
        ResultSet rs = professorDao.getStudents(professor);
        try {
            logger.info("############# Student List #############");
            logger.info("Course-Id \t Student-Id\tStudent-Name\tBranch\tgender\tSemester");
            while (rs.next()) {
                logger.info(rs.getInt("courseid") + "\t\t" + rs.getInt("studentid") + "\t\t" + rs.getString("studentname") + "\t\t" + rs.getString("branch") + "\t" + rs.getString("gender") + "\t" + rs.getInt("semester"));
            }
            logger.info("#############################################");
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Records student grades
     *
     * @param professor The professor recording the grades
     * @param studentId StudentID of the student whose grades need to be recorded
     * @param grades Grades being assigned to the student for the course
     * @param courseId The course for which the student is being graded
     */
    @Override
    public void recordStudentGrades(Professor professor, int studentId, String grades, int courseId) {
        professorDao.recordStudentGrades(professor, studentId, grades, courseId);
    }

    /**
     * Method to get all courses assigned to a professor
     *
     * @param professor Professor Object of the professor to query courses for.
     */
    @Override
    public void getAssignedCourse(Professor professor) {
        List<Course> courseList = professorDao.getCoursesTaughtByProfessor(professor.getProfessorId());
        logger.info("################## Course List #####################");
        logger.info("Course Id\tCourse Name\t\tCourse Description");
        courseList.forEach(course -> logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getDescription()));
        logger.info("#####################################################");
    }
}

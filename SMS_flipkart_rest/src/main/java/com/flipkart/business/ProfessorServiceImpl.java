package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.GENDER;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;
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
    public List<Student> viewAssignedStudents(Professor professor) {
        ResultSet rs = professorDao.getStudents(professor);
        List<Student> studentList = new ArrayList<>();
        try {
            logger.info("############# Student List #############");
            logger.info("Course-Id \t Student-Id\tStudent-Name\tBranch\tgender\tSemester");
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("studentid"));
                student.setName(rs.getString("studentname"));
                student.setBranch(rs.getString("branch"));
                student.setGender(GENDER.valueOfGender(rs.getString("gender")));
                student.setSemester(rs.getInt("semester"));
                studentList.add(student);

                logger.info(rs.getInt("courseid") + "\t\t" + rs.getInt("studentid") + "\t\t" + rs.getString("studentname") + "\t\t" + rs.getString("branch") + "\t" + rs.getString("gender") + "\t" + rs.getInt("semester"));
            }
            logger.info("#############################################");
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        }
        return studentList;
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
    public boolean recordStudentGrades(Professor professor, int studentId, String grades, int courseId) {
        return professorDao.recordStudentGrades(professor, studentId, grades, courseId);
    }

    /**
     * Method to get all courses assigned to a professor
     *
     * @param professor Professor Object of the professor to query courses for.
     */
    @Override
    public List<Course> getAssignedCourse(Professor professor) {
        List<Course> courseList = professorDao.getCoursesTaughtByProfessor(professor.getProfessorId());
        logger.info("################## Course List #####################");
        logger.info("Course Id\tCourse Name\t\tCourse Description");
        courseList.forEach(course -> logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + "\t\t" + course.getDescription()));
        logger.info("#####################################################");
        return courseList;
    }
}

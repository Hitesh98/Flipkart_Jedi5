package com.flipkart.dao;

import com.flipkart.Utils.DBUtil;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.GENDER;
import com.flipkart.constants.SQLQueries;
import com.flipkart.constants.USERTYPE;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Professor dao.
 */
public class ProfessorDAOImpl implements ProfessorDAO {

    private static Logger logger = Logger.getLogger(ProfessorDAOImpl.class);
    private Connection connection = DBUtil.getConnection();
    private PreparedStatement stmt = null;

    @Override
    public ResultSet getStudents(Professor professor) {
        List<Student> students = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(SQLQueries.GET_STUDENTS_TO_TEACH);
            stmt.setInt(1, professor.getProfessorId());
            ResultSet rs = stmt.executeQuery();
            if(rs != null) {
                return rs;
            } else {
                throw new Exception("No students found for professor : " + professor.getProfessorId());
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Course> getCoursesTaughtByProfessor(int professorID) {
        List<Course> courseList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(SQLQueries.GET_COURSE_TAUGHT_BY_PROFESSOR);
            stmt.setInt(1, professorID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt(1));
                course.setCourseName(rs.getString(2));
                course.setDescription(rs.getString(3));
                course.setFees(rs.getInt(4));
                courseList.add(course);
            }

        } catch(Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return courseList;
    }

    @Override
    public Professor getProfessorDetails(String username) {
        Professor professor = new Professor();
        try {
            stmt = connection.prepareStatement(SQLQueries.GET_USER_DETAILS);
            //stmt = connection.prepareStatement(SQLQueries.GET_PROFESSOR_DETAILS);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                professor.setUserId(rs.getInt("userid"));
                professor.setUsername(username);
                professor.setType(USERTYPE.Professor);
                stmt = connection.prepareStatement(SQLQueries.GET_PROFESSOR_DETAILS);
                stmt.setInt(1, professor.getUserId());
                rs = stmt.executeQuery();

                if (rs.next()) {
                    professor.setName(rs.getString("professorname"));
                    professor.setEmail(rs.getString("email"));
                    professor.setGender(GENDER.valueOfGender(rs.getString("gender")));
                    professor.setProfessorId(rs.getInt("professorID"));
                } else {
                    throw new UserNotFoundException("professor with username : " + username + " does not exist");
                }
            } else {
                throw new UserNotFoundException("User with username : " + username + " does not exist");
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return professor;
    }


    @Override
    public void recordStudentGrades(Professor professor, int studentId, String grade, int courseId) {
        try {
            StudentDAOImpl studentDAO = new StudentDAOImpl();
            if (!studentDAO.verifyStudentToCourseRegistration(studentId, courseId)) {
                throw new Exception("Student not registered for course. Cannot record grades");
            }
            if (!verifyProfessorToCourseRegistration(professor.getProfessorId(), courseId)) {
                throw new Exception("Professor not assigned this course. Cannot record grades");
            }
            stmt = connection.prepareStatement(SQLQueries.GRADE_STUDENT);
            stmt.setString(1, grade);
            stmt.setInt(2, studentId);
            stmt.setInt(3, courseId);
            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated > 0) {
                logger.info(studentId + " grades uploaded for course : " + courseId);
            }
            else {
                logger.info("Couldn't upload grades. Try again!");
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
    }

    public boolean verifyProfessorToCourseRegistration(int professorID , int courseId) {
        int count = 0;
        try {
            stmt = connection.prepareStatement(SQLQueries.VERIFY_PROFESSOR_WITH_COURSE);
            stmt.setInt(1,courseId);
            stmt.setInt(2, professorID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return count > 0;
    }
}

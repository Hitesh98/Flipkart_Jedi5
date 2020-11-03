package com.flipkart.dao;

import com.flipkart.Utils.DBUtil;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constants.GENDER;
import com.flipkart.constants.SQLQueries;
import com.flipkart.constants.USERTYPE;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The type Student dao.
 */
public class StudentDAOImpl implements StudentDAO {

    private static Logger logger = Logger.getLogger(StudentDAOImpl.class);
    private Connection connection = DBUtil.getConnection();
    private PreparedStatement stmt = null;

    @Override
    public void selectCourse(Student student, int courseId) {
        if (CourseLimitReached(courseId)) {
            logger.error("Course limit reached. Choose another course");
            return;
        }

        if (studentAlreadyRegistered(student.getStudentId(), courseId)) {
            logger.error("Student already registered for the course. Try Another!");
            return;
        }

        if (StudentCourseLimitReached(student.getStudentId())) {
            logger.error("Course limit per student reached.");
            return;
        }
        try {
            stmt = connection.prepareStatement(SQLQueries.REGISTER_STUDENT_FOR_COURSE);
            stmt.setInt(1, courseId);
            stmt.setInt(2, student.getStudentId());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info(student.getName() + " was registered for course : " + courseId + " successfully");
                DBUtil.closeStmt(stmt);
                return;
            }
            logger.error("Student could not be registered for the course. Please try again.");
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
    }


    @Override
    public Student getStudentDetails(String username) throws UserNotFoundException {
        Student student = new Student();
        try {
            stmt = connection.prepareStatement(SQLQueries.GET_USER_DETAILS);
            //stmt = connection.prepareStatement(SQLQueries.GET_STUDENT_DETAILS);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                student.setUserId(rs.getInt("userid"));
                student.setUsername(username);
                student.setType(USERTYPE.Student);
                stmt = connection.prepareStatement(SQLQueries.GET_STUDENT_DETAILS);
                stmt.setInt(1, student.getUserId());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    student.setName(rs.getString("studentname"));
                    student.setBranch(rs.getString("branch"));
                    student.setGender(GENDER.valueOfGender(rs.getString("gender")));
                    student.setSemester(rs.getInt("semester"));
                    student.setStudentId(rs.getInt("studentid"));
                } else {
                    throw new UserNotFoundException("student with username : " + username + " does not exist");
                }
            } else {
                throw new UserNotFoundException("User with username : " + username + " does not exist");
            }
        } catch(SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return student;
    }

    @Override
    public List<Course> viewRegisteredCourses(Student student) {
        ArrayList<Integer> courseIds = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(SQLQueries.GET_COURSE_FOR_STUDENT);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courseIds.add(rs.getInt("courseid"));
                logger.info("check: "  + rs.getInt("courseid"));
            }
            CourseCatalogDAO catalogDao = new CourseCatalogDAOImpl();
            for (int c : courseIds) {
                courses.add(catalogDao.viewCourseFromCatalog(c));
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return courses;
    }

    @Override
    public void dropCourse(int courseId, int studentId) throws CourseNotFoundException {
        try {
            stmt = connection.prepareStatement(SQLQueries.DROP_COURSE_FOR_STUDENT);
            stmt.setInt(1, courseId);
            stmt.setInt(2, studentId);
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("course with courseID : " + courseId + " was dropped for student with studentID : " + studentId);
            } else {
                throw new CourseNotFoundException("Course not found. Could not be dropped.");
            }
        } catch(SQLException ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
    }

    @Override
    public int getNumberOfCoursesRegistered(Student student) {
        int count = 0;
        try {
            stmt = connection.prepareStatement(SQLQueries.GET_COURSE_COUNT_FOR_STUDENT);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            count = rs.getInt("c");
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return count;
    }

    /**
     * Verify student to course registration boolean.
     *
     * @param studentID the student id
     * @param courseId  the course id
     * @return the boolean
     */
    public boolean verifyStudentToCourseRegistration(int studentID , int courseId) {
        int count = 0;
        try {
            stmt = connection.prepareStatement(SQLQueries.VERIFY_STUDENT_WITH_COURSE);
            stmt.setInt(1,courseId);
            stmt.setInt(2, studentID);
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

    @Override
    public Map<String, String> viewReportCard(Student student) {
        Map<String, String> reportCard = new HashMap<>();
        try {
            stmt = connection.prepareStatement(SQLQueries.VIEW_GRADES_QUERY);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                reportCard.put(rs.getString(2), rs.getString(3));
            }
            stmt.close();
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return reportCard;
    }

    @Override
    public int getTotalFee(Student student) {
        int totalFees = 0;
        try {
            stmt = connection.prepareStatement(SQLQueries.GET_TOTAL_FEE);
            stmt.setInt(1, student.getStudentId());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                totalFees = rs.getInt(1);
            }
            stmt.close();
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return totalFees;
    }

    @Override
    public boolean makePayment(Student student, int paymentMethod, int fees) {
        try {
            stmt = connection.prepareStatement(SQLQueries.MAKE_PAYMENT_QUERY);
            stmt.setInt(1, student.getStudentId());
            stmt.setInt(2, fees);
            Date date = new Date();
            stmt.setObject(3, date);
            stmt.setInt(4, paymentMethod);
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                PreparedStatement afterPayment = null;
                try {
                    afterPayment = connection.prepareStatement(SQLQueries.UPDATE_AFTER_PAYMENT);
                    afterPayment.setInt(1, student.getStudentId());
                    if(afterPayment.executeUpdate() > 0) {
                        logger.info("Payment successful");
                        return true;
                    }
                } catch(Exception ex){
                    logger.error(ex.getMessage());
                } finally{
                    //close resources
                    DBUtil.closeStmt(afterPayment);
                }
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        logger.error("Payment Failed");
        return false;
    }

    private boolean CourseLimitReached(int courseId) {
        PreparedStatement limitCheck = null;
        try {
            limitCheck = connection.prepareStatement(SQLQueries.GET_STUDENT_COUNT_FOR_COURSE);
            limitCheck.setInt(1, courseId);
            ResultSet rs = limitCheck.executeQuery();
            rs.next();
            return rs.getInt("c") >= 10;
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(limitCheck);
        }
        return true;
    }

    private boolean studentAlreadyRegistered(int studentId, int courseId) {
        PreparedStatement studentCheck = null;
        try {
            studentCheck = connection.prepareStatement(SQLQueries.VERIFY_STUDENT_WITH_COURSE);
            studentCheck.setInt(1, courseId);
            studentCheck.setInt(2, studentId);
            ResultSet rs = studentCheck.executeQuery();
            rs.next();
            return rs.getInt("c") >= 1;
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(studentCheck);
        }
        return true;
    }

    private boolean StudentCourseLimitReached(int studentId) {
        PreparedStatement studentLimitCheck = null;
        try {
            studentLimitCheck = connection.prepareStatement(SQLQueries.GET_COURSE_COUNT_FOR_STUDENT);
            studentLimitCheck.setInt(1, studentId);
            ResultSet rs = studentLimitCheck.executeQuery();
            rs.next();
            return rs.getInt("c") >= 4;
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(studentLimitCheck);
        }
        return true;
    }
}

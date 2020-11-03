package com.flipkart.dao;

import com.flipkart.Utils.DBUtil;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
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
 * The type Admin dao.
 */
public class AdminDAOImpl implements AdminDAO {

    private static Logger logger = Logger.getLogger(AdminDAOImpl.class);
    private Connection connection = DBUtil.getConnection();
    private PreparedStatement stmt = null;

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        try {
            logger.info("test1");
            if (connection == null) logger.info("connection null hai");
            stmt = connection.prepareStatement(SQLQueries.VIEW_USERS_QUERY);
            logger.info("test2");
            if (stmt == null) logger.info("test3");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setUserId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setType(USERTYPE.valueOfType(rs.getString(3)));
                userList.add(user);
            }
        } catch(Exception ex){
            logger.error(ex.getMessage() + " Can't get users.");
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return userList;
    }

    @Override
    public void assignCourseToProfessor(Professor professor, int courseId) {
        ProfessorDAO professorDAO = new ProfessorDAOImpl();
        try {

            stmt = connection.prepareStatement(SQLQueries.ASSIGN_PROFESSOR_QUERY);
            stmt.setInt(1, courseId);
            stmt.setInt(2, professor.getProfessorId());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0)
                logger.info(courseId + " assigned to Professor : " + professor.getProfessorId());
            else
                logger.error("Could not assign course to professor. Try Again.");
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
    }

    @Override
    public boolean addNewCourse(Course course) {
        try {
            stmt = connection.prepareStatement(SQLQueries.ADD_NEW_COURSE_QUERY);
            stmt.setInt(1,course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getDescription());
            stmt.setInt(4, course.getFees());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info(course.getCourseName() + " was added to course catalog");
                return true;
            } else {
                logger.error("Course could not be added. Try Again!");
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return false;
    }

    @Override
    public void deleteCourse(Course course) {
        try {
            stmt = connection.prepareStatement(SQLQueries.DELETE_COURSE_QUERY);
            stmt.setInt(1,course.getCourseId());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info(course.getCourseName() + " was deleted from course catalog");
            } else {
                logger.error("Course could not be dropped. Try Again!");
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
    }

    @Override
    public void deleteUser(long userId) {
        USERTYPE type = null;
        try {
            stmt = connection.prepareStatement(SQLQueries.GET_USER_ROLE);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                type = USERTYPE.valueOfType(rs.getString("role"));
            } else {
                throw new UserNotFoundException("No such user exists");
            }
            String userToDelteQuery;
            if (type == USERTYPE.Student) {
                userToDelteQuery = SQLQueries.DELETE_STUDENT_QUERY;
            } else if (type == USERTYPE.Professor) {
                userToDelteQuery = SQLQueries.DELETE_PROFESSOR_QUERY;
            } else {
                userToDelteQuery = SQLQueries.DELETE_ADMIN_QUERY;
            }
            stmt = connection.prepareStatement(userToDelteQuery);
            stmt.setLong(1,userId);
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                assert type != null;
                logger.info(type.name() + " was removed successfully");
            } else {
                assert type != null;
                logger.error(type.name() + " could not be removed. Try Again!");
            }
            stmt = connection.prepareStatement(SQLQueries.DELETE_USER_QUERY);
            stmt.setLong(1,userId);
            rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("User was removed successfully");
            } else {
                logger.error("User could not be removed. Try Again!");
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
    }

    @Override
    public boolean approveUser(long userId) {
        try {
            stmt = connection.prepareStatement(SQLQueries.APPROVE_USER_QUERY);
            stmt.setLong(1, userId);
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("user approved");
                return true;
            } else {
                logger.error("User could not be approved. Try again!");
                return false;
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return false;
    }

    public boolean updateProfessorForCourse(int professorId, int courseId) {
        try {
            stmt = connection.prepareStatement(SQLQueries.UPDATE_PROFESSOR_FOR_COURSE_QUERY);
            stmt.setInt(1, professorId);
            stmt.setInt(2, courseId);
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Course updated");
                return true;
            } else {
                logger.error("Course could not be updates. Try Again!");
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return false;
    }
}

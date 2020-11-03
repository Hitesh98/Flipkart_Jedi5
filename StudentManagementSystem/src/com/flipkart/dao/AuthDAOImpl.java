package com.flipkart.dao;

import com.flipkart.Utils.DBUtil;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueries;
import com.flipkart.constants.USERTYPE;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The type Auth dao.
 */
public class AuthDAOImpl implements AuthDAO {

    private static Logger logger = Logger.getLogger(AuthDAOImpl.class);
    private Connection connection = DBUtil.getConnection();
    private PreparedStatement stmt = null;

    @Override
    public USERTYPE login(String username, String password) {
        USERTYPE type = null;
        try {
            stmt = connection.prepareStatement(SQLQueries.LOGIN_USER_QUERY);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs == null) {
                throw new UserNotFoundException("Invalid credentials. Please try again");
            }
            while (rs.next()) {
                type = USERTYPE.valueOfType(rs.getString("role"));
                if (type == null) throw new UserNotFoundException("Invalid credentials. Please try again");
                logger.info(username + " logged in successfully. User type : " + type.name());
            }
            return type;
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return type;
    }

    @Override
    public boolean registerStudent(Student student, String password) {
        if (NewUserNotVerified(student.getUserId(), student.getUsername())) return false;
        if (userRegistrationUnsuccessful(student, password)) return false;
        try {
            stmt = connection.prepareStatement(SQLQueries.REGISTER_STUDENT_QUERY);
            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getBranch());
            stmt.setString(4, student.getGender().name().toLowerCase());
            stmt.setInt(5, student.getSemester());
            stmt.setInt(6, student.getUserId());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Student with name : " + student.getName() + " was added successfully");
                DBUtil.closeStmt(stmt);
                return true;
            }
            logger.error("Student could not be registered. Please try again.");
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return false;
    }

    @Override
    public boolean registerProfessor(Professor professor, String password) {
        if (NewUserNotVerified(professor.getUserId(), professor.getUsername())) return false;
        if (userRegistrationUnsuccessful(professor, password)) return false;
        try {
            stmt = connection.prepareStatement(SQLQueries.REGISTER_PROFESSOR_QUERY);
            stmt.setInt(1, professor.getProfessorId());
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getEmail());
            stmt.setString(4, professor.getGender().name().toLowerCase());
            stmt.setInt(5, professor.getUserId());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Professor with name : " + professor.getName() + " was added successfully");
                DBUtil.closeStmt(stmt);
                return true;
            }
            logger.error("Professor could not be registered. Please try again.");
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return false;
    }

    @Override
    public boolean registerAdmin(Admin admin, String password) {
        if (NewUserNotVerified(admin.getUserId(), admin.getUsername())) return false;
        if (userRegistrationUnsuccessful(admin, password)) return false;
        try {
            stmt = connection.prepareStatement(SQLQueries.REGISTER_ADMIN_QUERY);
            stmt.setInt(1, admin.getAdminID());
            stmt.setString(2, admin.getName());
            stmt.setString(3, admin.getGender().name().toLowerCase());
            stmt.setInt(4, admin.getUserId());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Admin with name : " + admin.getName() + " was added successfully");
                DBUtil.closeStmt(stmt);
                return true;
            }
            logger.error("admin could not be registered. Please try again.");
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return false;
    }

    private boolean NewUserNotVerified(int userID, String username) {
        PreparedStatement verifyUser = null;
        try {
            verifyUser = connection.prepareStatement(SQLQueries.VERIFY_USERNAME_AND_USERID);
            verifyUser.setString(1, username);
            verifyUser.setInt(2, userID);
            ResultSet rs = verifyUser.executeQuery();
            if(rs.next()) {
                if (rs.getInt("c") == 0) {
                    logger.info("Username and UserID verified successfully");
                    DBUtil.closeStmt(verifyUser);
                    return false;
                }
            }
            logger.error("username or userID already exists. Please try again!");
        }catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(verifyUser);
        }
        return true;
    }

    private boolean userRegistrationUnsuccessful(User user, String password) {
        PreparedStatement registerUser = null;
        try {
            registerUser = connection.prepareStatement(SQLQueries.REGISTER_USER_QUERY);
            registerUser.setInt(1, user.getUserId());
            registerUser.setString(2, user.getUsername());
            registerUser.setString(3, password);
            registerUser.setString(4, user.getType().name().toLowerCase());
            int rowCount = registerUser.executeUpdate();
            if (rowCount > 0) {
                logger.info("User with username : " + user.getUsername() + " was added successfully");
                DBUtil.closeStmt(registerUser);
                return false;
            }
            logger.error("User could not be registered. Please try again.");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            DBUtil.closeStmt(registerUser);
        }
        return true;
    }
}

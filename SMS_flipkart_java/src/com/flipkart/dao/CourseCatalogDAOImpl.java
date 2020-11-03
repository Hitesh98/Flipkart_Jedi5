package com.flipkart.dao;

import com.flipkart.Utils.DBUtil;
import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueries;
import com.flipkart.exception.CourseNotFoundException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Course catalog dao.
 */
public class CourseCatalogDAOImpl implements CourseCatalogDAO {

    private static Logger logger = Logger.getLogger(CourseCatalogDAOImpl.class);
    private Connection connection = DBUtil.getConnection();
    private PreparedStatement stmt = null;

    @Override
    public List<Course> viewCatalog() {
        List<Course> courseList = new ArrayList<>();

        try {
            stmt = connection.prepareStatement(SQLQueries.VIEW_ALL_COURSES);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courseList.add(createCourse(rs));
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return courseList;
    }

    @Override
    public Course viewCourseFromCatalog(int courseId) {
        PreparedStatement stmt = null;
        Course course = null;

        try {
            stmt = connection.prepareStatement(SQLQueries.GET_COURSE);
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs == null) throw new CourseNotFoundException("Invalid Course ID");
            if (rs.next()) {
                course = createCourse(rs);
            }
        } catch(Exception ex){
            logger.error(ex.getMessage());
        } finally{
            //close resources
            DBUtil.closeStmt(stmt);
        }
        return course;
    }

    private Course createCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("courseId"));
        course.setCourseName(rs.getString("courseName"));
        course.setDescription(rs.getString("description"));
        course.setFees(rs.getInt("fees"));
        return course;
    }

}

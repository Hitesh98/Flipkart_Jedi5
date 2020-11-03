package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.dao.CourseCatalogDAO;
import com.flipkart.dao.CourseCatalogDAOImpl;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type Course catalog service.
 */
public class CourseCatalogServiceImpl implements CourseCatalogService {

    private static Logger logger = Logger.getLogger(AdminServiceImpl.class);
    private static CourseCatalogDAO courseCatalogDAO = new CourseCatalogDAOImpl();

    /**
     * View all courses from course catalog
     *
     * @return List of all courses from course catalog
     */
    @Override
    public List<Course> getAllCourses() {
        return courseCatalogDAO.viewCatalog();
    }

    /**
     * View particular course from course catalog
     *
     * @param courseId courseID of the course to get from the course catalog
     * @return Course object of the required course
     */
    @Override
    public Course viewCourseFromCatalog(int courseId) {
        Course course = courseCatalogDAO.viewCourseFromCatalog(courseId);
        if (course != null) return course;
        logger.error("Invalid Course ID");
        return null;
    }
}

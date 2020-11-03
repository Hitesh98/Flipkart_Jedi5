package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;

/**
 * The interface Course catalog dao.
 */
public interface CourseCatalogDAO {

    /**
     * View All courses from course catalog.
     *
     * @return the list of all courses
     */
    public List<Course> viewCatalog();

    /**
     * View particular course from course catalog.
     *
     * @param courseId the course id of course to view
     * @return the course
     */
    public Course viewCourseFromCatalog(int courseId);
}

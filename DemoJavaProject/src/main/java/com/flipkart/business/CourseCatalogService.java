package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

/**
 * The interface Course catalog service.
 */
public interface CourseCatalogService {

    /**
     * View all courses from course catalog
     *
     * @return List of all courses from course catalog
     */
    public List<Course> getAllCourses();

    /**
     * View particular course from course catalog
     *
     * @param courseId courseID of the course to get from the course catalog
     * @return Course object of the required course
     */
    public Course viewCourseFromCatalog(int courseId);
}

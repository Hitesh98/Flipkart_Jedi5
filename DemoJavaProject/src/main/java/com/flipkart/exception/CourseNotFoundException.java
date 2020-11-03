package com.flipkart.exception;

/**
 * The type Course not found exception.
 */
public class CourseNotFoundException extends Exception {
    private String courseName;

    /**
     * Instantiates a new Course not found exception.
     *
     * @param courseName the course name
     */
    public CourseNotFoundException(String courseName) {
        super();
        this.courseName = courseName;
    }

    /**
     * Gets course name.
     *
     * @return the course name
     */
    public String getCourseName() {
        return this.courseName;
    }
}

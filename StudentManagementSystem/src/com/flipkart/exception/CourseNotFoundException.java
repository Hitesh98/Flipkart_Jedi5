package com.flipkart.exception;

public class CourseNotFoundException extends Exception {
    private String courseName;

    public CourseNotFoundException(String courseName) {
        super();
        this.courseName = courseName;
    }

    public String getCourseName() {
        return this.courseName;
    }
}

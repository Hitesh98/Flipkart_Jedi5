package com.flipkart.bean;

/**
 * The type Course.
 */
public class Course {

    private int courseId;
    private String courseName;
    private String description;
    private int fees;

    /**
     * Gets course id.
     *
     * @return the course id
     */
    public int getCourseId() { return courseId; }

    /**
     * Sets course id.
     *
     * @param courseId the course id
     */
    public void setCourseId(int courseId) { this.courseId = courseId; }

    /**
     * Gets course name.
     *
     * @return the course name
     */
    public String getCourseName() { return courseName; }

    /**
     * Sets course name.
     *
     * @param courseName the course name
     */
    public void setCourseName(String courseName) { this.courseName = courseName; }

    /**
     * Gets description.
     *
     * @return the course description
     */
    public String getDescription() { return description; }

    /**
     * Sets description.
     *
     * @param description the course description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Gets fees.
     *
     * @return the course fees
     */
    public int getFees() { return fees; }

    /**
     * Sets fees.
     *
     * @param fees the course fees
     */
    public void setFees(int fees) { this.fees = fees; }

}
package com.flipkart.bean;

import com.flipkart.constants.GENDER;
import com.flipkart.constants.USERTYPE;

/**
 * The type Student.
 */
public class Student extends User {
    private int studentId;
    private String name;
    private String branch;
    private GENDER gender;
    private int semester;

    /**
     * Constructor to initialise userType to Student and other fields to deafult values
     */
    public Student() {
        super();
        this.setType(USERTYPE.Student);
        this.studentId = 0;
        this.name = null;
        this.branch = null;
        this.gender = null;
        this.semester = 0;
    }

    /**
     * Gets semester.
     *
     * @return current semester
     */
    public int getSemester() { return semester; }

    /**
     * Sets semester.
     *
     * @param semester current semester
     */
    public void setSemester(int semester) { this.semester = semester; }

    /**
     * Gets gender.
     *
     * @return Student's Gender
     */
    public GENDER getGender() { return gender; }

    /**
     * Sets gender.
     *
     * @param gender Student's Gender
     */
    public void setGender(GENDER gender) { this.gender = gender; }

    /**
     * Gets student id.
     *
     * @return the studentID
     */
    public int getStudentId() { return studentId; }

    /**
     * Sets student id.
     *
     * @param studentId the studentID
     */
    public void setStudentId(int studentId) { this.studentId = studentId; }

    /**
     * Gets name.
     *
     * @return Student 's Name
     */
    public String getName() { return name; }

    /**
     * Sets name.
     *
     * @param name Student's Name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets branch.
     *
     * @return student 's branch
     */
    public String getBranch() { return branch; }

    /**
     * Sets branch.
     *
     * @param branch student's branch
     */
    public void setBranch(String branch) { this.branch = branch; }

}

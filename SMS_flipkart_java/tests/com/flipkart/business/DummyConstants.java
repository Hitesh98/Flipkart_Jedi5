package com.flipkart.business;

import com.flipkart.bean.*;
import com.flipkart.constants.GENDER;
import com.flipkart.constants.USERTYPE;


public class DummyConstants {
    public Professor dummyProfessor = new Professor();
    public Admin dummyAdmin = new Admin();
    public Student dummyStudent = new Student();
    public Course dummyCourse = new Course();

    public static final int suserid = 112345;
    public static final int puserid = 112346;
    public static final int auserid = 112347;
    public static final String susername = "stestUser";
    public static final String pusername = "ptestUser";
    public static final String ausername = "atestUser";
    public static final int typeid = 4321;
    public static final String name = "tester";
    public static final String branch = "testBranch";
    public static final GENDER gen = GENDER.Male;
    public static final int semester = 0;
    public static final String email = "tester@testing.com";
    public static final String password = "testpassword";
    public static final int courseid = 0;
    public static final String coursename = "testpassword";
    public static final String coursedescription = "testpassword";
    public static final int coursefees = 0;

    public DummyConstants() {
        createAdmin(dummyAdmin);
        createProfessor(dummyProfessor);
        createStudent(dummyStudent);
        createCourse(dummyCourse);
    }

    private static void createCourse(Course course) {
        course.setFees(coursefees);
        course.setCourseId(courseid);
        course.setDescription(coursedescription);
        course.setCourseName(coursename);
    }

    private static void createStudent(Student student) {

        createUser(student, USERTYPE.Student);
        student.setUserId(suserid);
        student.setUsername(susername);
        student.setStudentId(typeid);
        student.setName(name);
        student.setBranch(branch);
        student.setGender(gen);
        student.setSemester(semester);
    }

    private static void createProfessor(Professor professor) {
        createUser(professor, USERTYPE.Professor);
        professor.setUserId(puserid);
        professor.setUsername(pusername);
        professor.setProfessorId(typeid);
        professor.setName(name);
        professor.setEmail(email);
        professor.setGender(gen);
    }

    private static void createAdmin(Admin admin) {
        createUser(admin, USERTYPE.Admin);
        admin.setUserId(auserid);
        admin.setUsername(ausername);
        admin.setAdminID(typeid);
        admin.setName(name);
        admin.setGender(gen);
    }

    private static void createUser(User user, USERTYPE type) {

        user.setType(type);
    }
}

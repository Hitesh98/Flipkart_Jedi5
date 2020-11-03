package com.flipkart.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentServiceImplTest {

    private AdminService adminService;
    private StudentService studentService;
    private DummyConstants dummyConstants;

    @Before
    public void setUp() throws Exception {
        adminService = new AdminServiceImpl();
        studentService = new StudentServiceImpl();
        dummyConstants = new DummyConstants();
        adminService.addNewUser(dummyConstants.dummyProfessor, DummyConstants.password);
        adminService.addNewUser(dummyConstants.dummyStudent, DummyConstants.password);
        adminService.addNewCourse(dummyConstants.dummyCourse);
    }

    @After
    public void tearDown() throws Exception {
        adminService.deleteUser(dummyConstants.dummyStudent.getUserId());
        adminService.deleteUser(dummyConstants.dummyProfessor.getUserId());
        adminService.deleteCourse(dummyConstants.dummyCourse);
    }

    @Test
    public void getNumberOfCoursesRegistered() {
        studentService.selectCourse(dummyConstants.dummyStudent, dummyConstants.dummyCourse.getCourseId());
        adminService.assignCourseToProfessor(dummyConstants.dummyProfessor, dummyConstants.dummyCourse.getCourseId());
        int x = studentService.getNumberOfCoursesRegistered(dummyConstants.dummyStudent);
        assertEquals(x, 1);
    }

    @Test
    public void makePayment() {
        adminService.addNewUser(dummyConstants.dummyStudent, DummyConstants.password);
        adminService.addNewCourse(dummyConstants.dummyCourse);
        assertTrue(studentService.makePayment(dummyConstants.dummyStudent, 1, dummyConstants.dummyCourse.getFees()));
    }
}
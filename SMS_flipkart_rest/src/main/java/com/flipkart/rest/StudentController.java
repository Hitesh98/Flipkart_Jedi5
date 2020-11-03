package com.flipkart.rest;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.business.CourseCatalogService;
import com.flipkart.business.CourseCatalogServiceImpl;
import com.flipkart.business.StudentService;
import com.flipkart.business.StudentServiceImpl;
import com.flipkart.exception.CourseNotFoundException;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * The type Student controller.
 */
@Path("/student")
public class StudentController {

    private StudentService studentService = new StudentServiceImpl();
    private CourseCatalogService courseCatalogService = new CourseCatalogServiceImpl();
    private static Logger logger = Logger.getLogger(StudentController.class);

    /**
     * Gets registered courses.
     *
     * @param id the student id
     * @return the registered courses
     */
// View registered courses of particular student
    @GET
    @Path("/courses/registered/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getRegisteredCourses(@PathParam("studentId") int id) {
        Student student = new Student();
        student.setStudentId(id);
        return studentService.viewRegisteredCourses(student);
    }

    /**
     * View grades map.
     *
     * @param id the student id
     * @return the report card
     */
// View Report card of particular student
    @GET
    @Path("reportcard/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> viewGrades(@PathParam("studentId") int id) {
        Student student = new Student();
        student.setStudentId(id);
        return studentService.viewReportCard(student);
    }

    /**
     * Gets number of courses registered.
     *
     * @param id the student id
     * @return the number of courses registered for by the student
     */
// Get total courses a student has registered for
    @GET
    @Path("courses/registered/count/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getNumberOfCoursesRegistered(@PathParam("studentId") int id) {
        Student student = new Student();
        student.setStudentId(id);
        return studentService.getNumberOfCoursesRegistered(student);
    }


    /**
     * Gets total fee.
     *
     * @param id the student id
     * @return the total fee the student needs to pay
     */
    @GET
    @Path("fees/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getTotalFee(@PathParam("studentId") int id) {
        Student student = new Student();
        student.setStudentId(id);
        return studentService.getTotalFee(student);
    }

    /**
     * Add courses to course catalog
     *
     * @param studentId the student id
     * @param courseId  the course id
     * @return the response
     */
// select course for particular student
    @POST
    @Path("/selectcourse/{studentId}/{courseId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourses(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId) {
        logger.info("hit post service");
        logger.info("value of course id :" + courseId);
        logger.info("value of student id :" + studentId);
        String result = "Registered " + studentId + " for " + courseId;
        Student student = new Student();
        student.setStudentId(studentId);
        studentService.selectCourse(student, courseId);
        return Response.status(201).entity(result).build();
    }

    /**
     * Make payment.
     *
     * @param studentId     the student id
     * @param paymentMethod the payment method
     * @param fees          the fees
     * @return the response
     */
// make payment
    @POST
    @Path("/payment/{studentId}/{paymentMethod}/{fees}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makePayment(@PathParam("studentId") int studentId, @PathParam("paymentMethod") int paymentMethod, @PathParam("fees") int fees) {
        logger.info("hit post service");
        logger.info("value of student id :" + studentId);
        logger.info("value of payment Method :" + paymentMethod);
        logger.info("value of fees :" + fees);
        Student student = new Student();
        student.setStudentId(studentId);
        String result = "Made fee Payment for student with student Id " + studentId;
        studentService.makePayment(student, paymentMethod, fees);
        return Response.status(201).entity(result).build();
    }

    /**
     * Drop course for student
     *
     * @param studentId the student id
     * @param courseId  the course id
     * @return the response
     * @throws CourseNotFoundException the course not found exception
     */
    @DELETE
    @Path("/dropcourse/{studentId}/{courseId}")
    public Response deleteCustomer(@PathParam("studentId") int studentId, @PathParam("courseId") int courseId) throws CourseNotFoundException {
        Course course = new Course();
        Student student = new Student();
        course.setCourseId(courseId);
        student.setStudentId(studentId);
        studentService.dropCourse(course, student);
        return Response.status(200).entity("The course " + courseId + " for student " + studentId + "deleted").build();
    }
}
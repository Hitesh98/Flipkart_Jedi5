package com.flipkart.rest;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.CourseCatalogService;
import com.flipkart.business.CourseCatalogServiceImpl;
import com.flipkart.business.UserService;
import com.flipkart.business.UserServiceImpl;
import com.flipkart.constants.USERTYPE;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type User controller.
 */
@Path("/user")
public class UserController {

    private UserService userService = new UserServiceImpl();
    private CourseCatalogService courseCatalogService = new CourseCatalogServiceImpl();

    /**
     * Gets admin.
     *
     * @return the admin
     */
    @GET
    @Path("/get/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Admin getAdmin() {
        return new Admin();
    }

    /**
     * Gets professor.
     *
     * @return the professor
     */
    @GET
    @Path("/get/professor")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessor() {
        return new Professor();
    }

    /**
     * Gets student.
     *
     * @return the student
     */
    @GET
    @Path("/get/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent() {
        return new Student();
    }

    /**
     * Gets courses.
     *
     * @return the courses
     */
    @GET
    @Path("/courses/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        return courseCatalogService.getAllCourses();
    }

    /**
     * Register student.
     *
     * @param student  the student
     * @param password the password
     * @return the response
     */
    @POST
    @Path("/register/student/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Student student, @PathParam("password") String password) {
        student.setType(USERTYPE.Student);
        boolean reg = userService.registerUser(student, password);
        String result = "Student : " + student.getName() + " was Registered Successfully.";
        if (!reg) {
            result = "Unable to register student. Try again!";
            return Response.status(401).entity(result).build();
        }
        return Response.status(201).entity(result).build();
    }


}

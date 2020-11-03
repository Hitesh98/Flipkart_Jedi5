package com.flipkart.rest;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.business.AdminService;
import com.flipkart.business.AdminServiceImpl;
import com.flipkart.business.UserService;
import com.flipkart.business.UserServiceImpl;
import com.flipkart.constants.USERTYPE;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Admin controller.
 */
@Path("/admin")
public class AdminController {

    private UserService userService = new UserServiceImpl();
    private AdminService adminService = new AdminServiceImpl();
    private static Logger logger = Logger.getLogger(AdminController.class);

    /**
     * Gets all users.
     *
     * @return the list of all users
     */
    @GET
    @Path("/allusers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    /**
     * Add course.
     *
     * @param course the course
     * @return the response
     */
    @POST
    @Path("/addcourse")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(Course course) {
        logger.info("Adding new course");
        logger.info(course.getCourseId());
        logger.info(course.getCourseName());
        logger.info(course.getDescription());
        logger.info(course.getFees());
        adminService.addNewCourse(course);
        String result = "Added "  + course.getCourseName() + " to course catalog.";
        return Response.status(201).entity(result).build();
    }

    /**
     * Delete course.
     *
     * @param courseId the course id
     * @return the response
     */
    @DELETE
    @Path("/dropcourse/{courseId}")
    public Response deleteCourse(@PathParam("courseId") int courseId) {
        Course course = new Course();
        course.setCourseId(courseId);
        adminService.deleteCourse(course);
        return Response.status(200).entity("Course " + " with course id " + courseId + " successfully deleted").build();
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     * @return the response
     */
    @DELETE
    @Path("/deleteuser/{userId}")
    public Response deleteUser(@PathParam("userId") long userId) {
        adminService.deleteUser(userId);
        return Response.status(200).entity("user " + " with user id " + userId + " successfully deleted").build();
    }

    /**
     * Approve user.
     *
     * @param userId the user id
     * @return the response
     */
    @PUT
    @Path("/approveuser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveUser(@PathParam("userId") long userId) {
        adminService.approveUser(userId);
        return Response.status(200).entity("User with id : " + userId + " was approved by admin.").build();
    }

    /**
     * Assign professor to course.
     *
     * @param professorId the professor id
     * @param courseId    the course id
     * @return the response
     */
    @PUT
    @Path("/assigncoursetoprofessor/{courseId}/{professorId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignProfessorToCourse(@PathParam("professorId") int professorId, @PathParam("courseId") int courseId) {
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        adminService.assignCourseToProfessor(professor, courseId);
        return Response.status(201).entity("Course with id: " + courseId + " assigned to professor.").build();
    }

    /**
     * Register professor.
     *
     * @param professor the professor
     * @param password  the password
     * @return the response
     */
    @POST
    @Path("/register/professor/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerProfessor(Professor professor, @PathParam("password") String password) {
        professor.setType(USERTYPE.Professor);
        boolean reg = userService.registerUser(professor, password);
        String result = "Professor : " + professor.getName() + " was Registered Successfully.";
        if (!reg) {
            result = "Unable to register professor. Try again!";
            return Response.status(401).entity(result).build();
        }
        return Response.status(201).entity(result).build();
    }

    /**
     * Register admin.
     *
     * @param admin    the admin
     * @param password the password
     * @return the response
     */
    @POST
    @Path("/register/admin/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerAdmin(Admin admin, @PathParam("password") String password) {
        admin.setType(USERTYPE.Admin);
        boolean reg = userService.registerUser(admin, password);
        String result = "Admin : " + admin.getName() + " was Registered Successfully.";
        if (!reg) {
            result = "Unable to register admin. Try again!";
            return Response.status(401).entity(result).build();
        }
        return Response.status(201).entity(result).build();
    }
}

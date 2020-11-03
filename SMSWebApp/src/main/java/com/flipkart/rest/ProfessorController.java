package com.flipkart.rest;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.ProfessorService;
import com.flipkart.business.ProfessorServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Professor controller.
 */
@Path("/professor")
public class ProfessorController {

    private static ProfessorService professorService = new ProfessorServiceImpl();

    /**
     * View students list.
     *
     * @param professorId the professor id
     * @return the list of students assigned to professor
     */
// Get students being tutored by particular professor
    @GET
    @Path("/getstudents/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewStudents(@PathParam("professorId") int professorId) {
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        return professorService.viewAssignedStudents(professor);
    }

    /**
     * View courses list.
     *
     * @param professorId the professor id
     * @return the list of courses assigned to professor
     */
// Get students being tutored by particular professor
    @GET
    @Path("/getcourses/{professorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourses(@PathParam("professorId") int professorId) {
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        return professorService.getAssignedCourse(professor);
    }

    /**
     * Grade student.
     *
     * @param professorId the professor id
     * @param studentId   the student id
     * @param courseId    the course id
     * @param grade       the grade
     * @return the response
     */
    @POST
    @Path("gradestudent/{professorId}/{studentId}/{courseId}/{grade}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gradeStudent(@PathParam("professorId") int professorId, @PathParam("studentId") int studentId,
                                 @PathParam("courseId") int courseId, @PathParam("grade") String grade) {
        Professor professor = new Professor();
        professor.setProfessorId(professorId);
        professorService.recordStudentGrades(professor, studentId, grade, courseId);
        String result = "Grades for student Id " + studentId + " with course " + courseId + "updated with grade " + grade;
        return Response.status(201).entity(result).build();
    }
}
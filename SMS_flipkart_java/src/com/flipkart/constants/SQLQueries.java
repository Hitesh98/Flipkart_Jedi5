package com.flipkart.constants;

public class SQLQueries {

    // Course Catalog Queries
    public static final String VIEW_ALL_COURSES = "select courseid, coursename, fees, description from coursecatalog";
    public static final String GET_COURSE = "select * from coursecatalog where courseid = ?";

    // User related Queries
    public static final String LOGIN_USER_QUERY = "select role from user where username = ? and password = ?";
    public static final String VERIFY_USERNAME_AND_USERID = "select count(*) as c from user where username = ? OR userid = ?";
    public static final String REGISTER_USER_QUERY = "insert into user(userid, username, password, role) values (?,?,?,?)";
    public static final String GET_USER_DETAILS = "select userid from user where username = ? limit 1";

    // Student related Queries
    public static final String REGISTER_STUDENT_QUERY = "insert into student(studentid, studentname, branch, gender, semester, userid) values (?,?,?,?,?,?)";
    public static final String GET_STUDENT_COUNT_FOR_COURSE = "select count(*) as c from registeredcourses where courseid = ?";
    public static final String VERIFY_STUDENT_WITH_COURSE = "select count(*) as c from registeredcourses where courseid = ? AND studentid = ?";
    public static final String GET_COURSE_COUNT_FOR_STUDENT = "select count(*) as c from registeredcourses where studentid = ?";
    public static final String REGISTER_STUDENT_FOR_COURSE = "insert into registeredcourses(courseid, studentid) values (?,?)";
    public static final String GET_STUDENT_DETAILS = "select * from student where userid = ? limit 1";
    public static final String GET_COURSE_FOR_STUDENT = "select courseid from registeredcourses where studentid = ?";
    public static final String DROP_COURSE_FOR_STUDENT = "delete from registeredcourses where courseid = ? and studentid = ?";
    public static final String VIEW_GRADES_QUERY = "select c.courseid, c.coursename, rc.grade from coursecatalog c join registeredcourses rc on rc.courseid = c.courseid where rc.studentId = ?";
    public static final String GET_TOTAL_FEE = "select sum(c.fees) from registeredcourses rc join coursecatalog c on c.courseid = rc.courseid where rc.studentid = ?";
    public static final String MAKE_PAYMENT_QUERY = "insert into payments (studentid, feespaid, paymentdate, paymentmethod) values(?, ?, ?, ?)";
    public static final String UPDATE_AFTER_PAYMENT = "update student set isregistered = 1 where studentid = ?";

    //Professor related Queries
    public static final String REGISTER_PROFESSOR_QUERY = "insert into professor(professorid, professorname, email, gender, userid) values (?,?,?,?,?)";
    public static final String GET_STUDENTS_TO_TEACH = "select rc.studentid, rc.courseid, s.studentname, s.branch, s.gender, s.semester from student s join registeredcourses rc on rc.studentid = s.studentid where rc.courseid in (select courseid from professorcourses where professorid = ?) order by rc.courseid";
    public static final String GET_COURSE_TAUGHT_BY_PROFESSOR = "select p.courseid, c.coursename, c.description, c.fees from professorcourses p join coursecatalog c on c.courseid = p.courseid where p.professorid = ?";
    public static final String GET_PROFESSOR_DETAILS = "select * from professor where userid = ?";
    public static final String GRADE_STUDENT = "update registeredcourses set grade = ? where studentid = ? and courseid = ?";
    public static final String VERIFY_PROFESSOR_WITH_COURSE = "select count(*) as c from professorcourses where courseid = ? AND professorid = ?";

    //Admin related Queries
    public static final String REGISTER_ADMIN_QUERY = "insert into admin(adminid, adminname, gender, userid) values (?,?,?,?)";
    public static final String VIEW_USERS_QUERY = "SELECT  userId, username, role from user";
    public static final String UPDATE_PROFESSOR_FOR_COURSE_QUERY = "update professorcourses SET professorid=? WHERE courseId = ?";
    public static final String ADD_NEW_COURSE_QUERY = "insert into coursecatalog(courseid, coursename, description, fees) values (?,?, ?, ?)";
    public static final String DELETE_COURSE_QUERY = "delete from coursecatalog where courseid = ?";
    public static final String ASSIGN_PROFESSOR_QUERY = "insert into professorcourses(courseid, professorid) values (?, ?)";
    public static final String DELETE_USER_QUERY = "delete from user where userid = ?";
    public static final String GET_USER_ROLE = "select role from user where userid = ?";
    public static final String DELETE_STUDENT_QUERY = "delete from student where userid = ?";
    public static final String DELETE_PROFESSOR_QUERY = "delete from professor where userid = ?";
    public static final String DELETE_ADMIN_QUERY = "delete from admin where userid = ?";
}

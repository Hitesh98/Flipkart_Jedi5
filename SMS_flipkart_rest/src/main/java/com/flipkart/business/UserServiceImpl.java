package com.flipkart.business;

import com.flipkart.Utils.PrintableTable;
import com.flipkart.Utils.SequenceGenerator;
import com.flipkart.bean.*;
import com.flipkart.constants.USERTYPE;
import com.flipkart.dao.AuthDAO;
import com.flipkart.dao.AuthDAOImpl;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    /**
     * Views all courses in the course catalog
     */
    @Override
    public void viewCourseCatalog() {
        Logger logger = Logger.getLogger(UserServiceImpl.class);

        CourseCatalogService catalog = new CourseCatalogServiceImpl();

        List<Course> courses = catalog.getAllCourses();
        String[][] data = new String[courses.size() + 1][4];
        data[0] = new String[]{"Course-Id", "Course-Name", "Fees", "Course-Description"};
        for (int i = 0; i < courses.size(); i++) {
            data[i + 1][0] = String.valueOf(courses.get(i).getCourseId());
            data[i + 1][1] = courses.get(i).getCourseName();
            data[i + 1][2] = String.valueOf(courses.get(i).getFees());
            data[i + 1][3] = courses.get(i).getDescription();
        }
        //st.getRightJustifiedRows();
        String sb = "\n################## ALL Courses ####################" +
                new PrintableTable().printTable(data) +
                "###################################################\n";
        logger.info(sb);
        //logger.info("Course-Id\tCourse-Name\tFees\tCourse-Description");
        //courses.forEach(course -> logger.info(course.getCourseId() + "\t\t" + course.getCourseName() + " \t\t"  + course.getFees() + "\t\t" + course.getDescription()));
    }

    /**
     * Registers user (student, professor and admin)
     *
     * @param user      User Object of the user to registerUser
     * @param password  Password to set for the user
     * @return true if user was registered, else false.
     */
    @Override
    public boolean registerUser(User user, String password) {
        AuthDAO authDao = new AuthDAOImpl();
        user.setUserId(new SequenceGenerator().nextId());
        if (user.getType().equals(USERTYPE.Admin)) {
            return authDao.registerAdmin((Admin) user, password);
        } else if (user.getType().equals(USERTYPE.Student)) {
            return authDao.registerStudent((Student) user, password);
        } else if (user.getType().equals(USERTYPE.Professor)){
            return authDao.registerProfessor((Professor) user, password);
        } else {
            return false;
        }
    }
}

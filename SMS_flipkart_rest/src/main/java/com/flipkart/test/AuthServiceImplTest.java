package com.flipkart.test;

import com.flipkart.business.AdminServiceImpl;
import com.flipkart.business.AuthServiceImpl;
import com.flipkart.constants.USERTYPE;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class AuthServiceImplTest {


    private AuthServiceImpl mockedAuthService;
    private AdminServiceImpl mockedAdminService;
    private DummyConstants mockDummyConstants;



    @Before
    public void setUp() throws Exception {
        mockedAuthService = new AuthServiceImpl();
        mockedAdminService = new AdminServiceImpl();
        mockDummyConstants = new DummyConstants();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginStudent() {
        mockedAdminService.addNewUser(mockDummyConstants.dummyStudent, DummyConstants.password);
        USERTYPE usertype = mockedAuthService.login(DummyConstants.susername, DummyConstants.password);
        assertEquals(USERTYPE.Student, usertype);
        mockedAdminService.deleteUser(DummyConstants.suserid);
    }

    @Test
    public void loginProfessor() {
        mockedAdminService.addNewUser(mockDummyConstants.dummyProfessor, DummyConstants.password);
        USERTYPE usertype = mockedAuthService.login(DummyConstants.pusername, DummyConstants.password);
        assertEquals(USERTYPE.Professor, usertype);
        mockedAdminService.deleteUser(DummyConstants.puserid);
    }

    @Test
    public void loginAdmin() {
        mockedAdminService.addNewUser(mockDummyConstants.dummyAdmin, DummyConstants.password);
        USERTYPE usertype = mockedAuthService.login(DummyConstants.ausername, DummyConstants.password);
        assertEquals(USERTYPE.Admin, usertype);
        mockedAdminService.deleteUser(DummyConstants.auserid);
    }

    @Test
    public void loginInvalidUser() {
        USERTYPE usertype = mockedAuthService.login("anyrandomusername", "randompassword");
        assertNull(usertype);
    }
}
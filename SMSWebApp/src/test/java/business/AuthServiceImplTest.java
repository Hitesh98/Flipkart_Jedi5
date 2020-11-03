package business;

import com.flipkart.business.AdminServiceImpl;
import com.flipkart.business.AuthServiceImpl;
import com.flipkart.constants.USERTYPE;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AuthServiceImplTest {

    @Mock
    private AuthServiceImpl mockedAuthService;

    @Mock
    private AdminServiceImpl mockedAdminService;

    @Mock
    private DummyConstants mockDummyConstants;



    @Before
    public void setUp() throws Exception {
        mockedAuthService = mock(AuthServiceImpl.class);
        mockedAdminService = mock(AdminServiceImpl.class);
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
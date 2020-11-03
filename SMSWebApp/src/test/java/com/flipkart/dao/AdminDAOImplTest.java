package com.flipkart.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class AdminDAOImplTest {
    @Mock
    DataSource mockDataSource;
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement mockStmt;
    @Mock
    ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(mockStmt);
        doNothing().when(mockStmt).setString(anyInt(), anyString());
        when(mockStmt.execute()).thenReturn(Boolean.TRUE);
        when(mockStmt.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
    }

    @Test(expected = SQLException.class)
    public void testGetUsersWithPreparedStmtException() throws SQLException {

        //mock
        when(mockConn.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException());
    }

}
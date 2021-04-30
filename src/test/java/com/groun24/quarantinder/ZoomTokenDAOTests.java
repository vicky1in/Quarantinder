package com.groun24.quarantinder;

import com.groun24.quarantinder.Modal.ZoomToken;
import com.groun24.quarantinder.dao.ZoomTokenDAO;
import com.groun24.quarantinder.dao.ZoomTokenDAOImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("zoomtest") // RUN with configuration
@RunWith(SpringJUnit4ClassRunner.class)
public class ZoomTokenDAOTests {

    @Autowired
    ZoomToken zoomToken;

    @Test
    public void zoomTokenDAOCreateTest() {
        // this tests the create save and get tokens method
        ZoomToken zoomTokenMock = mock(ZoomToken.class); // create mock
        when(zoomTokenMock.getId()).thenReturn(1);
        when(zoomTokenMock.getAccessToken()).thenReturn("tokenHolder");

        ZoomTokenDAO zoomTokenDAO = new ZoomTokenDAOImpl();

        zoomTokenDAO.createAndSave(zoomTokenMock.getAccessToken()); // call method
        assertEquals(1, zoomTokenDAO.getTokens().get(0).getId());
        assertEquals("tokenHolder", zoomTokenDAO.getTokens().get(0).getAccessToken());
        //assert the result is true
    }

    @Test
    public void zoomTokenDAOUpdateTest() {
        // this tests the update and get tokens method
        ZoomToken zoomTokenMock = mock(ZoomToken.class); // create mock
        when(zoomTokenMock.getId()).thenReturn(1);
        when(zoomTokenMock.getAccessToken()).thenReturn("tokenHolder");

        ZoomTokenDAO zoomTokenDAO = new ZoomTokenDAOImpl();
        zoomTokenDAO.createAndSave(zoomTokenMock.getAccessToken());
        when(zoomTokenMock.getId()).thenReturn(1);
        when(zoomTokenMock.getAccessToken()).thenReturn("newHolder");// modify the mock

        zoomTokenDAO.updateToken(zoomTokenMock); // call method

        assertEquals(1, zoomTokenDAO.getTokens().get(0).getId());
        assertEquals("newHolder", zoomTokenDAO.getTokens().get(0).getAccessToken());
    }

    @Test
    public void zoomTokenDAOUpDeleteTest() {
        // this tests the delete and get tokens method
        ZoomToken zoomTokenMock = mock(ZoomToken.class); // create mock
        when(zoomTokenMock.getId()).thenReturn(1);
        when(zoomTokenMock.getAccessToken()).thenReturn("tokenHolder");// set up mock
        ZoomTokenDAO zoomTokenDAO = new ZoomTokenDAOImpl();
        zoomTokenDAO.createAndSave(zoomTokenMock.getAccessToken());
        zoomTokenDAO.deleteToken(1); // call methods

        assertEquals(0, zoomTokenDAO.getTokens().size()); //assert result
     }

    @Test
    public void zoomTokenDAOUpSetIDTest() {

        ZoomToken zoomTokenMock = mock(ZoomToken.class);
        when(zoomTokenMock.getId()).thenReturn(1);
        when(zoomTokenMock.getAccessToken()).thenReturn("tokenHolder");
        ZoomTokenDAO zoomTokenDAO = new ZoomTokenDAOImpl();

        zoomTokenDAO.createAndSave(zoomTokenMock.getAccessToken());
        zoomTokenDAO.deleteToken(1);

        assertEquals(0, zoomTokenDAO.getTokens().size());
    }

}

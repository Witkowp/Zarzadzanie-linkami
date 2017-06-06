/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkmanager;

import com.mysql.jdbc.Connection;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rozanovk
 */
public class BazaDanychTest {

    @Test
    public void testGetFromSQL() throws Exception {
        System.out.println("getFromSQL");
        List<Data> expResult = new LinkedList<>();
        Data data = new Data(2,"facebook", "https://www.facebook.com/", "/home/facebook ");
        expResult.add(data);
        List<Data> result = BazaDanych.getFromSQL("test");
        if (!(data.equals(result.get(0)))){
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of insertIntoSQL method, of class BazaDanych.
     */
    @Test
    public void testInsertIntoSQL() throws Exception {
        System.out.println("insertIntoSQL");
        String url = "";
        String nazwa = "";
        String sciezkaDoPliku = "";
        long expResult = 0L;
        long result = BazaDanych.insertIntoSQL("test",url, nazwa, sciezkaDoPliku);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of getConnection method, of class BazaDanych.
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        Connection result = BazaDanych.getConnection();
        if (Connection.class.isInstance(result) != true){
            fail("The test case is a prototype.");
        }
        
    }
    
}

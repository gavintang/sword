/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.util;

import java.util.Date;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Gavin
 */
public class DateFormatUtilNGTest {
    
    public DateFormatUtilNGTest() {}

    @Test
    public void testToShortDayFormat() {
        Date date = new Date(1399436953000l);
        String expResult = "May 7";
        String result = DateFormatUtil.toShortDayFormat(date);
        assertEquals(result, expResult);
    }

    @Test
    public void testToLongDayFormat() {
        Date date = new Date(1399436953000l);
        String expResult = "2014-05-07";
        String result = DateFormatUtil.toDayFormat(date);
        assertEquals(result, expResult);
    }

    @Test
    public void testToDaytimeFormat() {
        Date date = new Date(1399436953000l);
        String expResult = "2014-05-07 12:29 PM";
        String result = DateFormatUtil.toDaytimeFormat(date);
        assertEquals(result, expResult);
    }

    @Test
    public void testToTimeFormat() {
        Date date = new Date(1399436953000l);
        String expResult = "12:29:13 PM";
        String result = DateFormatUtil.toTimeFormat(date);
        assertEquals(result, expResult);
    }

    @Test
    public void testToShorttimeFormat() {
        Date date = new Date(1399436953000l);
        String expResult = "12:29 PM";
        String result = DateFormatUtil.toShorttimeFormat(date);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testNullDate() {
        
        Date date = null;
        String expResult = "N/A";
        String result = DateFormatUtil.toShortDayFormat(date);
        assertEquals(result, expResult);
        
        result = DateFormatUtil.toDayFormat(date);
        assertEquals(result, expResult);
        
        result = DateFormatUtil.toDaytimeFormat(date);
        assertEquals(result, expResult);
        
        result = DateFormatUtil.toTimeFormat(date);
        assertEquals(result, expResult);
        
        result = DateFormatUtil.toShorttimeFormat(date);
        assertEquals(result, expResult);
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
    
}

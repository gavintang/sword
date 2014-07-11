/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */
package com.gtang.longclaw.core.util.validate;

import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Gavin
 */
public class LongValidationTest {

    final static Logger logger = Logger.getLogger(LongValidationTest.class.getName());

    @BeforeClass
    public void setup (){
        ValidationUtil.registry(MockDataObject.class);
    }

    @Test
    public void testLongValidation4Null() {
        MockDataObject myobj = new MockDataObject(null, MockDataObject.VALID_LONG);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "longA[cannot be null or blank.] ");
        }
        
        myobj = new MockDataObject(MockDataObject.VALID_INT, null);

        try {
            myobj.validate();
        } catch (ValidationException ex) {
            Assert.fail();
        }
    }

    @Test
    public void testLongValidation4Min() {
        MockDataObject myobj = new MockDataObject(9l, MockDataObject.VALID_LONG);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "longA[cannot be less than 10] ");
        }
        
        myobj = new MockDataObject(10l, MockDataObject.VALID_LONG);

        try {
            myobj.validate();
        } catch (ValidationException ex) {
            Assert.fail();
        }
    }
    
    @Test
    public void testLongValidation4Max() {
        MockDataObject myobj = new MockDataObject(101l, MockDataObject.VALID_LONG);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "longA[cannot be greater than 100] ");
        }
        
        myobj = new MockDataObject(100l, MockDataObject.VALID_LONG);

        try {
            myobj.validate();
        } catch (ValidationException ex) {
            Assert.fail();
        }
    }
    
}

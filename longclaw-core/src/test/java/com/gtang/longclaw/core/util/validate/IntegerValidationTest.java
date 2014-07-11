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
public class IntegerValidationTest {

    final static Logger logger = Logger.getLogger(IntegerValidationTest.class.getName());

    @BeforeClass
    public void setup (){
        ValidationUtil.registry(MockDataObject.class);
    }
    
    @Test
    public void testIntegerValidation4Null() {
        MockDataObject myobj = new MockDataObject(null, MockDataObject.VALID_INT);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "intA[cannot be null or blank.] ");
        }
        
        myobj = new MockDataObject(MockDataObject.VALID_INT, null);

        try {
            myobj.validate();
        } catch (ValidationException ex) {
            Assert.fail();
        }
    }

    @Test
    public void testIntegerValidation4Min() {
        MockDataObject myobj = new MockDataObject(9, MockDataObject.VALID_INT);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "intA[cannot be less than 10] ");
        }
        
        myobj = new MockDataObject(10, MockDataObject.VALID_INT);

        try {
            myobj.validate();
        } catch (ValidationException ex) {
            Assert.fail();
        }
    }
    
    @Test
    public void testIntegerValidation4Max() {
        MockDataObject myobj = new MockDataObject(101, MockDataObject.VALID_INT);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "intA[cannot be greater than 100] ");
        }
        
        myobj = new MockDataObject(100, MockDataObject.VALID_INT);

        try {
            myobj.validate();
        } catch (ValidationException ex) {
            Assert.fail();
        }
    }
    
}

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
public class StringValidationTest {

    final static Logger logger = Logger.getLogger(StringValidationTest.class.getName());

    @BeforeClass
    public void setup (){
        ValidationUtil.registry(MockDataObject.class);
    }
    
    @Test
    public void testStringValidation4NullString() {
        MockDataObject myobj = new MockDataObject(null, MockDataObject.VALID_STR);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "strA[cannot be null or blank.] ");
        }
        
        myobj = new MockDataObject(MockDataObject.VALID_STR, null);
        try {
            myobj.validate();
        } catch (ValidationException ex) {
            Assert.fail();
        }
    }
    
    @Test
    public void testStringValidation4EmptyString() {
        MockDataObject myobj = new MockDataObject("", MockDataObject.VALID_STR);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "strA[cannot be null or blank.] ");
        }
    }
    
    @Test
    public void testStringValidation4LengthMin() {
        MockDataObject myobj = new MockDataObject("a", MockDataObject.VALID_STR);

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "strA[cannnot be less than 2.] ");
        }
    }
    
    @Test
    public void testStringValidation4LengthMax() {
        MockDataObject myobj = new MockDataObject(MockDataObject.VALID_STR, "abcdef");

        try {
            myobj.validate();
            Assert.fail();
        } catch (ValidationException ex) {
            Assert.assertEquals(ex.getMessage(), "strB[cannot be longer than 5.] ");
        }
    }
    
}

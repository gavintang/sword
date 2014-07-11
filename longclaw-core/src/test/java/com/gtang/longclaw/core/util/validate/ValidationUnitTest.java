/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */
package com.gtang.longclaw.core.util.validate;

import com.gtang.longclaw.core.data.AbstractThing;
import java.util.List;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Gavin
 */
public class ValidationUnitTest {

    final static Logger logger = Logger.getLogger(ValidationUnitTest.class.getName());

    @Test
    public void testRegistryAndValidate (){
        
        //0. Registry Validator
        ValidationUtil.registry(MyClassA.class);
        ValidationUtil.registry(MyClassB.class);
        
        //1. Check out registry
        Validators vsA = ValidationUtil.getValidators(MyClassA.class);
        Assert.assertNotNull(vsA);
        Assert.assertEquals(vsA.getValidatorSize(), 2);
        
        Validators vsB = ValidationUtil.getValidators(MyClassB.class);
        Assert.assertNotNull(vsB);
        Assert.assertEquals(vsB.getValidatorSize(), 2);
        
        //2. Do validation
        List<ValidationException> exList = vsA.validate(new MyClassA(9, null));
        Assert.assertEquals(exList.size(), 2);

        exList = vsB.validate(new MyClassB("A", 10));
        Assert.assertEquals(exList.size(), 1);
        
        //3. clearup
        ValidationUtil.remove(MyClassA.class);
        vsA = ValidationUtil.getValidators(MyClassA.class);
        Assert.assertNull(vsA);
    }
    
    private static class MyClassA extends AbstractThing {

        private Integer intA;
        private Long longA;

        public MyClassA(Integer intA, Long longA) {
            this.intA = intA;
            this.longA = longA;
        }
        
        @IntegerValidation(min = 10, max = 100, hasRange = true, nullable = false)
        public Integer getIntA() {
            return intA;
        }

        @LongValidation(min = 10l, max = 100l, hasRange = true, nullable = false)
        public Long getLongA() {
            return longA;
        }

    }
    
    private static class MyClassB extends AbstractThing {

        private String strA;
        private Integer intA;

        public MyClassB(String strA, Integer intA) {
            this.strA = strA;
            this.intA = intA;
        }
        
        @StringValidation(min = 2, nullable = false)
        public String getStrA() {
            return strA;
        }

        @IntegerValidation(min = 10, max = 100, hasRange = true, nullable = false)
        public Integer getIntA() {
            return intA;
        }

    }

}

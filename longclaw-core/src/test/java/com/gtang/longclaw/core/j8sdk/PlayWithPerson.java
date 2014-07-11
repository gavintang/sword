/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.j8sdk;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.IntConsumer;
import org.apache.commons.lang3.time.DateUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Gavin
 */
public class PlayWithPerson {
    
    private final static Random randomUtil = new Random();
    
    private Set<Person> personSet;
    
    public PlayWithPerson() {
    }
    
    @Test
    public void forEach() {
        personSet.stream()
                .forEach(e -> {
                    System.out.println(e);
        });
    }
    
    @Test
    public void filter() {
        personSet.stream()
                .filter(e -> e.getSex() == Gender.MAIL)
                .forEach(e -> {
                    System.out.println(e);
                });
    }
    
    @Test
    public void average () {
        double average = personSet.stream()
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();
        
        BigDecimal avgAge = new BigDecimal(average);
        
        System.out.println(avgAge.toPlainString());
    }
    
    @Test
    public void sum () {
        forEach();
        
        Integer total = personSet.stream()
                .map(Person::getAge)
                .reduce(1, (a,b) -> a+b)
                ;

        System.out.println(total);
    }
    
    @Test
    public void averageCollect () {
        forEach();
        
        Averager collect1 = personSet.stream()
                .filter(e -> e.getSex() == Gender.MAIL)
                .map(Person::getAge)
                .collect(Averager::new, Averager::accept, Averager::combine)
                ;
        
        System.out.println(collect1.average());
        
        Averager collect2 = personSet.stream()
                .filter(e -> e.getSex() == Gender.FEMALE)
                .map(Person::getAge)
                .collect(Averager::new, Averager::accept, Averager::combine)
                ;
        
        collect1.combine(collect2);
        
        System.out.println(collect1.average());

    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {}

    @AfterClass
    public static void tearDownClass() throws Exception {}

    @BeforeMethod
    public void setUpMethod(Method method) throws Exception {
        
        String testName = method.getName();
        System.out.println(String.format("[%s] starting...", testName));
        
        if(personSet == null) {
            personSet = new HashSet<>();
            personSet.add(generatePerson("Tom", Gender.MAIL));
            personSet.add(generatePerson("Janny", Gender.FEMALE));
            personSet.add(generatePerson("Abel", Gender.UNKNOWN));
            personSet.add(generatePerson("Janny", Gender.FEMALE));
            personSet.add(generatePerson("Dick", Gender.MAIL));
        }
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println();
    }

    private static Person generatePerson(String name, Gender sex){
        
        Person p = new Person();
        p.setName(name);
        p.setId(randomUtil.nextInt(1000) + 10000l);
        p.setSex(sex);
        
        p.setAge(randomUtil.nextInt(20) + 10);
        
        Date dob = new Date();
        dob = DateUtils.addDays(dob, -1 * randomUtil.nextInt(365));
        dob = DateUtils.addYears(dob, p.getAge() * -1);
        p.setDateOfBirth(dob);
        
        return p;
    }
    
    private static class Averager implements IntConsumer {

        private int total = 0;
        private int count = 0;
        
        @Override
        public void accept(int value) {
            System.out.println("accept: " + this.toString());
            
            total += value;
            count++;
        }
        
        public void combine(Averager other) {
            System.out.println("conbine: " + this.toString() + " and " + other.toString());
            
            total += other.total;
            count += other.count;
        }
        
        public double average() {
            return count > 0 ? ((double) total) / count : 0.0;
        }

        @Override
        public String toString() {
            return "Averager{" + "total=" + total + ", count=" + count + '}';
        }

    }
}

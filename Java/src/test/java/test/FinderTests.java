package test;

import algorithm.Couple;
import algorithm.ExtremityAge;
import algorithm.Finder;
import algorithm.Person;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FinderTests {

    Person sue = new Person();
    Person greg = new Person();
    Person sarah = new Person();
    Person mike = new Person();

    @Before
    public void setup() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        sue.name = "Sue";
        sue.birthDate = formatter.parse("1950-01-01");
        greg.name = "Greg";
        greg.birthDate = formatter.parse("1952-05-01");
        sarah.name = "Sarah";
        sarah.birthDate = formatter.parse("1982-01-01");
        mike.name = "Mike";
        mike.birthDate = formatter.parse("1979-01-01");
    }

    @Test
    public void returns_Empty_Results_When_Given_Empty_List() {
        List<Person> list = new ArrayList<>();
        Finder finder = new Finder(list);

        Couple result = finder.find(ExtremityAge.ClosestAge);

        assertNull(result.getOldestPerson());
        assertNull(result.getYoungestPerson());
    }

    @Test
    public void returns_Empty_Results_When_Given_One_FT() {
        List<Person> list = new ArrayList<>();
        list.add(sue);

        Finder finder = new Finder(list);

        Couple result = finder.find(ExtremityAge.ClosestAge);

        assertNull(result.getOldestPerson());
        assertNull(result.getYoungestPerson());
    }

    @Test
    public void returns_One_Two_For_Two_FTs() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(ExtremityAge.ClosestAge);

        assertEquals(sue, result.getOldestPerson());
        assertEquals(greg, result.getYoungestPerson());
    }

    @Test
    public void returns_Two_Two_For_Two_FTs() {
        List<Person> list = new ArrayList<>();
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(ExtremityAge.FarthestAges);

        assertEquals(greg, result.getOldestPerson());
        assertEquals(mike, result.getYoungestPerson());
    }

    @Test
    public void returns_Two_Two_For_Four_FTs() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(ExtremityAge.FarthestAges);

        assertEquals(sue, result.getOldestPerson());
        assertEquals(sarah, result.getYoungestPerson());
    }

    @Test
    public void returns_One_Two_For_Four_FTs() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(ExtremityAge.ClosestAge);

        assertEquals(sue, result.getOldestPerson());
        assertEquals(greg, result.getYoungestPerson());
    }

}

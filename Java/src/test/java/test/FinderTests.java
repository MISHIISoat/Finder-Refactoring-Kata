package test;

import algorithm.Couple;
import algorithm.FilterCategory;
import algorithm.Finder;
import algorithm.Person;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FinderTests {

    Person sue;
    Person greg;
    Person sarah;
    Person mike;

    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setup() throws ParseException {
        sue = new Person("Sue", FORMATTER.parse("1950-01-01"));
        greg = new Person("Greg", FORMATTER.parse("1952-05-01"));
        sarah = new Person("Sarah", FORMATTER.parse("1982-01-01"));
        mike = new Person("Mike", FORMATTER.parse("1979-01-01"));
    }

    @Test
    public void returns_Couple_Without_Persons_When_Given_Empty_List() {
        List<Person> list = new ArrayList<>();
        Finder finder = new Finder(list);

        Couple result = finder.find(FilterCategory.ClosestAges);

        assertNull(result.getSecondPerson());
        assertNull(result.getFirstPerson());
    }

    @Test
    public void returns_Couple_Without_Persons_When_Given_One_Person() {
        List<Person> list = new ArrayList<>();
        list.add(sue);

        Finder finder = new Finder(list);

        Couple result = finder.find(FilterCategory.ClosestAges);

        assertNull(result.getSecondPerson());
        assertNull(result.getFirstPerson());
    }

    @Test
    public void returns_One_Couple_With_Persons_When_Given_Two_Persons() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(FilterCategory.ClosestAges);

        assertNotNull(result.getSecondPerson());
        assertNotNull(result.getFirstPerson());
    }

    @Test
    public void returns_One_Couple_Ordered_By_Age_When_Given_Two_Persons() {
        List<Person> list = new ArrayList<>();
        list.add(greg);
        list.add(sue);

        Finder finder = new Finder(list);

        Couple result = finder.find(FilterCategory.ClosestAges);

        assertEquals(sue, result.getSecondPerson());
        assertEquals(greg, result.getFirstPerson());
    }

    @Test
    public void returns_One_Couple_When_Given_Two_Persons() {
        List<Person> list = new ArrayList<>();
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(FilterCategory.FarthestAges);

        assertEquals(greg, result.getSecondPerson());
        assertEquals(mike, result.getFirstPerson());
    }

    @Test
    public void returns_Farthest_Ages_Couple_When_Given_More_Than_Two_Persons() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(FilterCategory.FarthestAges);

        assertEquals(sue, result.getSecondPerson());
        assertEquals(sarah, result.getFirstPerson());
    }

    @Test
    public void returns_Closest_Ages_Couple_When_Given_More_Than_Two_Persons() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(FilterCategory.ClosestAges);

        assertEquals(sue, result.getSecondPerson());
        assertEquals(greg, result.getFirstPerson());
    }

    @Test
    public void returns_Couple_With_Person_Name_First_Letter_As_Close_As_Possible_To_A() {
        List<Person> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        Couple result = finder.find(FilterCategory.ClosestNameToA);

        assertEquals(greg, result.getFirstPerson());
        assertEquals(mike, result.getSecondPerson());
    }
}

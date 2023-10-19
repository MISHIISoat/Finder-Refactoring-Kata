package test;

import algorithm.F;
import algorithm.FT;
import algorithm.Finder;
import algorithm.Thing;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FinderTests {

    Thing sue = new Thing();
    Thing greg = new Thing();
    Thing sarah = new Thing();
    Thing mike = new Thing();

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
        List<Thing> list = new ArrayList<>();
        Finder finder = new Finder(list);

        F result = finder.Find(FT.One);

        assertNull(result.P1);
        assertNull(result.P2);
    }

    @Test
    public void returns_Empty_Results_When_Given_One_FT() {
        List<Thing> list = new ArrayList<>();
        list.add(sue);

        Finder finder = new Finder(list);

        F result = finder.Find(FT.One);

        assertNull(result.P1);
        assertNull(result.P2);
    }

    @Test
    public void returns_One_Two_For_Two_FTs() {
        List<Thing> list = new ArrayList<>();
        list.add(sue);
        list.add(greg);
        Finder finder = new Finder(list);

        F result = finder.Find(FT.One);

        assertEquals(sue, result.P1);
        assertEquals(greg, result.P2);
    }

    @Test
    public void returns_Two_Two_For_Two_FTs() {
        List<Thing> list = new ArrayList<>();
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        F result = finder.Find(FT.Two);

        assertEquals(greg, result.P1);
        assertEquals(mike, result.P2);
    }

    @Test
    public void returns_Two_Two_For_Four_FTs() {
        List<Thing> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        F result = finder.Find(FT.Two);

        assertEquals(sue, result.P1);
        assertEquals(sarah, result.P2);
    }

    @Test
    public void returns_One_Two_For_Four_FTs() {
        List<Thing> list = new ArrayList<>();
        list.add(sue);
        list.add(sarah);
        list.add(mike);
        list.add(greg);
        Finder finder = new Finder(list);

        F result = finder.Find(FT.One);

        assertEquals(sue, result.P1);
        assertEquals(greg, result.P2);
    }

}

package com.guido;

import com.guido.Exceptions.CannotAcessDataBase;
import com.guido.Exceptions.CategoryDoesNotExist;
import com.guido.Exceptions.EmailNotAvalable;
import com.guido.Exceptions.InvalidCredentials;
import com.guido.Exceptions.LocationDoesNotExit;
import com.guido.Exceptions.TripDoesNotExist;
import com.guido.JDBC.JDBCQueries;
import com.guido.Model.Category;
import com.guido.Model.Location;
import com.guido.Model.Trip;

import junit.framework.TestCase;

import java.util.Set;

public class JDBCQueriesTest extends TestCase {
    JDBCQueries jdbc;
    public void setUp() throws Exception {
        super.setUp();
        jdbc = new JDBCQueries();
    }

    public void tearDown() throws Exception {
        jdbc.close();
    }

    public void testLogin_user() throws InvalidCredentials {
        jdbc.login_user("157", "blanc@gmail.com");
    }

    public void testRegister_user() throws CannotAcessDataBase, EmailNotAvalable {
        jdbc.register_user("caldas", "122", "shaggy@gmail.com");
    }

    public void testUpdate_name() throws CannotAcessDataBase {
        jdbc.update_name("caldas", 5);
    }

    public void testUpdate_email() throws CannotAcessDataBase {
        jdbc.update_email("shaggy@gmail.com", 5);
    }

    public void testUpdate_password() throws CannotAcessDataBase {
        jdbc.update_password("122", 5);
    }

    public void testGet_categories() throws CannotAcessDataBase {
        Set<Category> s = jdbc.get_categories();
        s.forEach(c -> System.out.println(c.toString()));
    }

    public void testAdd_category_user() throws CannotAcessDataBase {
        jdbc.add_category_user(6,3);
    }

    public void testRemove_category_user() throws CannotAcessDataBase {
        jdbc.remove_category_user(5,2);
    }

    public void testGet_location() throws LocationDoesNotExit {
        System.out.println(jdbc.get_location(1).toString());
    }

    public void testGet_locations() {
        Set<Location> s = jdbc.get_locations();
        s.forEach(l -> System.out.println(l.toString()));
    }

    public void testGet_trip() throws TripDoesNotExist {
        System.out.println(jdbc.get_trip(1).toString());
    }

    public void testGet_trips() {
        Set<Trip> s = jdbc.get_trips();
        s.forEach(t -> System.out.println(t.toString()));
    }

    public void testGet_locations_from_trip() throws LocationDoesNotExit {
        Set<Location> s = jdbc.get_locations_from_trip(1);
        s.forEach(l -> System.out.println(l.toString()));
    }

    public void testGet_categories_from_user() throws CategoryDoesNotExist {
        Set<Category> s = jdbc.get_categories_from_user(1);
        s.forEach(c -> System.out.println(c.toString()));
    }
}
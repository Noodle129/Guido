package com.guido.JDBC;

import com.guido.Exceptions.CannotAcessDataBase;
import com.guido.Exceptions.CategoryDoesNotExist;
import com.guido.Exceptions.EmailNotAvalable;
import com.guido.Exceptions.InvalidCredentials;
import com.guido.Exceptions.LocationDoesNotExit;
import com.guido.Exceptions.TripDoesNotExist;
import com.guido.Model.Category;
import com.guido.Model.Location;
import com.guido.Model.Trip;
import com.guido.Model.User;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

public class JDBCQueries implements Serializable {
    private Connection con;

    public JDBCQueries() throws SQLException {
        this.con = JDBCHelper.getConnection();
    }
    public void close() throws SQLException {
        JDBCHelper.closeConnection(con);
    }

    public static final String INSERT_USER
            = "INSERT INTO user(name,password,email) VALUES(?,?,?)";

    public static final String LOGIN_USER
            = "SELECT * FROM user WHERE email=? AND password=?";

    public static final String UPDATE_NAME
            = "UPDATE user SET name=? WHERE id=?";

    public static final String UPDATE_EMAIL
            = "UPDATE user SET email=? WHERE id=?";

    public static final String UPDATE_PASSWORD
            = "UPDATE user SET password=? WHERE id=?";

    public static final String GET_CATEGORIES
            = "SELECT * FROM CATEGORY";

    public static final String GET_CATEGORY
            = "SELECT * FROM CATEGORY WHERE id=?";

    public static final String ADD_CATEGORY_TO_USER
            = "INSERT INTO user_category_relationship(user_id,category_id) VALUES(?,?)";

    public static final String REMOVE_CATEGORY_FROM_USER
            = "DELETE FROM user_category_relationship WHERE user_id=? AND category_id=?";

    public static final String GET_LOCATION
            = "SELECT * FROM location WHERE id=?";

    public static final String GET_LOCATIONS
            = "SELECT * FROM location";

    public static final String GET_TRIP
            = "SELECT * FROM trip WHERE id=?";

    public static final String GET_TRIPS
            = "SELECT * FROM trip";

    public static final String GET_LOCATIONS_FROM_TRIP
            = "SELECT * FROM trips_location_relationship WHERE Trip_ID=?";

    public static final String GET_CATEGORIES_FROM_USER
            = "SELECT * FROM user_category_relationship WHERE User_ID=?";

    public User login_user(String lg_pass, String lg_mail) throws InvalidCredentials {
        PreparedStatement ps = null;
        User u = new User();
        try {
            ps = con.prepareStatement(LOGIN_USER);
            ps.setString(1, lg_mail);
            ps.setString(2, lg_pass);
            ResultSet rs = ps.executeQuery();

           if(!rs.next()) throw new InvalidCredentials();
            u.setName(rs.getString(Tables.USER_NAME_COL));
            u.setEmail(rs.getString(Tables.USER_EMAIL_COL));
            u.setId(rs.getInt(Tables.USER_ID_COL));
            u.setPassword(rs.getString(Tables.USER_PASSWORD_COL));

        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return u;
    }

    public void register_user(String name, String password, String email) throws EmailNotAvalable {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(INSERT_USER);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.execute();
        }
        catch (SQLIntegrityConstraintViolationException e) {
            throw new EmailNotAvalable(email);
        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }

        }
    }

    //TODO EXCEPTION
    public void update_name(String name, int id) throws CannotAcessDataBase {
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            ps = con.prepareStatement(UPDATE_NAME);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.execute();
        }
        catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }

        }
    }

    //TODO EXCEPTION
    public void update_email(String email, int id) throws CannotAcessDataBase {
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            ps = con.prepareStatement(UPDATE_EMAIL);
            ps.setString(1, email);
            ps.setInt(2, id);
            ps.execute();
        }
        catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }

        }
    }

    //TODO EXCEPTION
    public void update_password(String password, int id) throws CannotAcessDataBase {
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            ps = con.prepareStatement(UPDATE_PASSWORD);
            ps.setString(1, password);
            ps.setInt(2, id);
            ps.execute();
        }
        catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }

        }
    }

    public Set<Category> get_categories() throws CannotAcessDataBase {
        Set<Category> categoryMap = new HashSet<>();
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            ps = con.prepareStatement(GET_CATEGORIES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categoryMap.add(new Category(rs.getInt(Tables.CAT_ID_COL),
                                             rs.getString(Tables.CAT_NAME_COL)));
            }

        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        }
        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return categoryMap;
    }

    //TODO EXCEPTION
    public void add_category_user(int uid, int cid) throws CannotAcessDataBase {
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            ps = con.prepareStatement(ADD_CATEGORY_TO_USER);
            ps.setInt(1, uid);
            ps.setInt(2, cid);
            ps.execute();
        }
        catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
    }

    //TODO EXCEPTION
    public void remove_category_user(int uid, int cid) throws CannotAcessDataBase {
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            ps = con.prepareStatement(REMOVE_CATEGORY_FROM_USER);
            ps.setInt(1, uid);
            ps.setInt(2, cid);
            ps.execute();
        }
        catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        }
        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }

        }
    }

    public Category get_category(int id) throws CategoryDoesNotExist {
        PreparedStatement ps = null;
        Category c = new Category();
        try {
            ps = con.prepareStatement(GET_CATEGORY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()) throw new CategoryDoesNotExist(id);
            c.setId(rs.getInt(Tables.CAT_ID_COL));
            c.setName(rs.getString(Tables.CAT_NAME_COL));
        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return c;
    }

    public Location get_location(int id) throws LocationDoesNotExit {
        PreparedStatement ps = null;
        Location l = new Location();
        try {
            ps = con.prepareStatement(GET_LOCATION);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()) throw new LocationDoesNotExit(id);
            l.setId(rs.getInt(Tables.LOC_ID_COL));
            l.setName(rs.getString(Tables.LOC_NAME_COL));
            l.setAddress(rs.getString(Tables.LOC_ADDRESS_COL));
            l.setX(rs.getDouble(Tables.LOC_X_COL));
            l.setY(rs.getDouble(Tables.LOC_Y_COL));
            l.setAdmin_id(rs.getInt(Tables.LOC_ADMIN_ID_COL));
        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return l;
    }

    public Set<Location> get_locations(){
        PreparedStatement ps = null;
        Set<Location> ans = new HashSet<>();
        try {
            ps = con.prepareStatement(GET_LOCATIONS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Location l = new Location();
                l.setId(rs.getInt(Tables.LOC_ID_COL));
                l.setName(rs.getString(Tables.LOC_NAME_COL));
                l.setAddress(rs.getString(Tables.LOC_ADDRESS_COL));
                l.setX(rs.getDouble(Tables.LOC_X_COL));
                l.setY(rs.getDouble(Tables.LOC_Y_COL));
                l.setAdmin_id(rs.getInt(Tables.LOC_ADMIN_ID_COL));
                ans.add(l);
            }
        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return ans;
    }

    public Trip get_trip(int id) throws TripDoesNotExist {
        PreparedStatement ps = null;
        Trip t = new Trip();
        try {
            ps = con.prepareStatement(GET_TRIP);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()) throw new TripDoesNotExist(id);
            t.setId(rs.getInt(Tables.TRIP_ID_COL));
            t.setName(rs.getString(Tables.TRIP_NAME_COL));
            t.setAdmin_id(rs.getInt(Tables.TRIP_ADMIN_ID_COL));
        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return t;
    }

    public Set<Trip> get_trips(){
        PreparedStatement ps = null;
        Set<Trip> ans = new HashSet<>();
        try {
            ps = con.prepareStatement(GET_TRIPS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Trip t = new Trip();
                t.setName(rs.getString(Tables.TRIP_NAME_COL));
                t.setId(rs.getInt(Tables.TRIP_ID_COL));
                t.setAdmin_id(rs.getInt(Tables.TRIP_ADMIN_ID_COL));
                ans.add(t);
            }
        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return ans;
    }

    public Set<Location> get_locations_from_trip(int trip_id) throws LocationDoesNotExit {
        Set<Location> ans = new HashSet<>();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(GET_LOCATIONS_FROM_TRIP);
            ps.setInt(1, trip_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ans.add(get_location(rs.getInt(Tables.TLR_LOC_ID_COL)));
            }
        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return ans;
    }

    public Set<Category> get_categories_from_user(int user_id) throws CategoryDoesNotExist {
        Set<Category> ans = new HashSet<>();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(GET_CATEGORIES_FROM_USER);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ans.add(get_category(rs.getInt(Tables.UCR_CAT_ID_COL)));
            }
        } catch (SQLException e) {
            JDBCHelper.printSQLExcep(e);
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                JDBCHelper.printSQLExcep(e);
            }
        }
        return ans;
    }
}

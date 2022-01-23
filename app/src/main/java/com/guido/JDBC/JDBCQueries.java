package com.guido.JDBC;

import com.google.android.material.tabs.TabLayout;
import com.guido.Exceptions.CannotAcessDataBase;
import com.guido.Exceptions.EmailNotAvalable;
import com.guido.Exceptions.InvalidCredentials;
import com.guido.Exceptions.LocationDoesNotExit;
import com.guido.Model.Category;
import com.guido.Model.Location;
import com.guido.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

public class JDBCQueries {
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

    public static final String ADD_CATEGORY_TO_USER
            = "INSERT INTO user_category_relationship(user_id,category_id) VALUES(?,?)";

    public static final String REMOVE_CATEGORY_FROM_USER
            = "DELETE FROM user_category_relationship WHERE user_id=? AND category_id=?";

    public static final String GET_LOCATION
            = "SELECT FROM location WHERE id=?";

    public static final String GET_TRIP
            = "SELECT FROM trip WHERE id=?";

    public static final String GET_LOCATIONS_FROM_TRIP
            = "";

    public static final String GET_CATEGORIES_FROM_USER
            = "";

    public User login_user(String lg_pass, String lg_mail) throws InvalidCredentials {
        PreparedStatement ps = null;
        User u = new User();
        try {
            ps = con.prepareStatement(LOGIN_USER);
            ps.setString(1, lg_mail);
            ps.setString(2, lg_pass);
            ResultSet rs = ps.executeQuery();

           if(!rs.next()) throw new InvalidCredentials();
            u.setName(rs.getString(TableConsts.USER_NAME_COL));
            u.setEmail(rs.getString(TableConsts.USER_EMAIL_COL));
            u.setId(rs.getInt(TableConsts.USER_ID_COL));
            u.setPassword(rs.getString(TableConsts.USER_PASSWORD_COL));

        } catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }
        }
        return u;
    }
    public void register_user(String name, String password, String email) throws CannotAcessDataBase, EmailNotAvalable {
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            ps = con.prepareStatement(INSERT_USER);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.execute();
            con.commit();
        }
        catch (SQLIntegrityConstraintViolationException e) {
            throw new EmailNotAvalable(email);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
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
            con.commit();
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
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
            con.commit();
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
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
            con.commit();
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
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
                categoryMap.add(new Category(rs.getInt(TableConsts.CAT_ID_COL),
                                             rs.getString(TableConsts.CAT_NAME_COL)));
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        }
        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
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
            con.commit();
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
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
            con.commit();
        }
        catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        }

        finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }

        }
    }

    public Location get_location(int id) throws LocationDoesNotExit {
        PreparedStatement ps = null;
        Location l = new Location();
        try {
            ps = con.prepareStatement(GET_LOCATION);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(!rs.next()) throw new LocationDoesNotExit(id);
            l.setId(rs.getInt(TableConsts.LOC_ID_COL));
            l.setName(rs.getString(TableConsts.LOC_NAME_COL));
            l.setAddress(rs.getString(TableConsts.LOC_ADDRESS_COL));
            l.setX(rs.getFloat(TableConsts.LOC_X_COL));
            l.setY(rs.getFloat(TableConsts.LOC_Y_COL));
            l.setAdminID(rs.getInt(TableConsts.LOC_ADMIN_ID_COL));
        } catch (SQLException e) {
            System.err.println("Error: " + e.getErrorCode());
            System.err.println("State: " + e.getSQLState());
            System.err.println("Message: " + e.getMessage());
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }
        }
        return l;
    }

}

package com.guido.JDBC;

import com.guido.Exceptions.CannotAcessDataBase;
import com.guido.Exceptions.EmailNotAvalable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class JDBCQueries {
    public static final String INSERT_USER
            = "INSERT INTO user(name,password,email) VALUES(?,?,?)";

    public static final String CHECK_LOGIN_AND_GET_USER
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
    
    public static void register_user(String name, String password, String email) throws CannotAcessDataBase, EmailNotAvalable {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            con.setAutoCommit(false);
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
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }

        }
    }

    //TODO EXCEPTION
    public static void update_name(String name, int id) throws CannotAcessDataBase {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            con.setAutoCommit(false);
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
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }

        }
    }

    //TODO EXCEPTION
    public static void update_email(String email, int id) throws CannotAcessDataBase {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            con.setAutoCommit(false);
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
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }

        }
    }

    //TODO EXCEPTION
    public static void update_password(String password, int id) throws CannotAcessDataBase {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            con.setAutoCommit(false);
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
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }

        }
    }

    //TODO EXCEPTION
    public static void add_category_user(int uid, int cid) throws CannotAcessDataBase {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            con.setAutoCommit(false);
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
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }

        }
    }

    //TODO EXCEPTION
    public static void remove_category_user(int uid, int cid) throws CannotAcessDataBase {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) throw new CannotAcessDataBase();
            con.setAutoCommit(false);
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
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getErrorCode());
                System.err.println("State: " + e.getSQLState());
                System.err.println("Message: " + e.getMessage());
            }

        }
    }

}

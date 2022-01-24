package com.guido;

import org.junit.Test;

import com.guido.Exceptions.InvalidCredentials;
import com.guido.JDBC.JDBCQueries;
import java.sql.SQLException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws InvalidCredentials, SQLException {
        JDBCQueries jdbc = new JDBCQueries();
        jdbc.close();
    }
}
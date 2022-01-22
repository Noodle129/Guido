package com.guido;

import org.junit.Test;

import static org.junit.Assert.*;

import com.guido.Exceptions.CannotAcessDataBase;
import com.guido.Exceptions.EmailNotAvalable;
import com.guido.JDBC.JDBCQueries;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws CannotAcessDataBase, EmailNotAvalable {
        JDBCQueries.register_user("Azula","019","azula@besta.com");
    }
}
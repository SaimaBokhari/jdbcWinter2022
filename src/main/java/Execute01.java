import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Before starting this class, add Driver postgre repository in pom xml file between <dependencies>.. copy paste it from maven repository site
public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // We need to follow these 5 steps:

        // 1. step: Registration to the driver
        Class.forName("org.postgresql.Driver");



        // 2. step: Create connection with the database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ahmed", "Ahmed", "Coding2022");

        // 3. step: Create statement
         Statement st = con.createStatement();

        // 4. step: Execute the query


        /*
        execute() method can be used in DDL (Data Definition Language) ==> table creation, drop table, alter table
        It can also be used in DQL (Data Query Language) e.g. SELECT functions
        1. If you use execute() method in DDL, you'll get false every time.
        2. If you use execute() method in DQL, you'll get false or true
                   When you use execute() method in DQL, if you get ResultSet Object as return, you
                   will get true otherwise you will get false.
         */


        // Example 1: Create a workers table with the columns' worker_id, worker_name, worker_salary
        String sql1 = "CREATE TABLE workers( worker_id VARCHAR (50), worker_name VARCHAR (20), worker_salary INT)";
        boolean sqlResult =  st.execute(sql1);
        System.out.println(sqlResult);

        // Example 2: Alter table by adding worker_address column into the workers table
        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR (80)";
        st.execute(sql2);

        // Example 3: Drop the table

        String sql3 = "DROP TABLE workers";
        st.execute(sql3);

        // 5 step: Close the connection and statement
        con.close();;
        st.close();

    }
}

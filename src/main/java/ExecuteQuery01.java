import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // We need the first 3 steps for every class:

        // 1. step: Registration to the driver
        Class.forName("org.postgresql.Driver");



        // 2. step: Create connection with the database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ahmed", "Ahmed", "Coding2022");

        // 3. step: Create statement
        Statement st = con.createStatement();

        //1.Example: Select the country names whose region id's are 1
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        // If you use execute() method, you will true/false as return.
        // But I want to see the records for this question, so we have to use executeQuery()

        boolean result1 = st.execute(sql1);
        System.out.println("result1 = " + result1);

        // To see the records, we have another method which is executeQuery()
        ResultSet resultSet1 = st.executeQuery(sql1);

        // then we use a while loop with the name of the column coz that query has a lot of columns, so we
        // have to specify which column data we need
        while(resultSet1.next()){
            System.out.println(resultSet1.getString("country_name"));
        }

        //2.Example: Select the country_id and country_name whose region_id's are greater than 2
        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id>2";
        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()){
            System.out.println(resultSet2.getString("country_id")+"-> "+resultSet2.getString("country_name"));
        }

        System.out.println("------------------------");
        //3.Example: Select all columns whose number_of_employees is the lowest from companies table
        String sql3 = "SELECT company FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees)FROM companies)";

        ResultSet resultSet3 = st.executeQuery(sql3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getString("company"));
        }

        // we can get the cursor on the next value without while loop as well

//        resultSet3.next();
//        System.out.println(resultSet3.getString("company"));

        System.out.println("------------------");
        //4.Example: Select all columns whose number_of_employees is the lowest from companies table
        String sql4 = "SELECT * FROM companies WHERE number_of_employees = (SELECT MIN(number_of_employees)FROM companies)";

        // we can use column numbers as well to get the output
        ResultSet resultSet4 = st.executeQuery(sql4);
        while (resultSet4.next()){
            System.out.println(resultSet4.getInt(1)+" - " +resultSet4.getString(2)+" - "+ resultSet4.getInt(3));
        }

        // 5 step: Close the connection and statement
        con.close();;
        st.close();



    }
}

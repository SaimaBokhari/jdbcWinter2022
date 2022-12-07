import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // We need to follow these 5 steps:

        // 1. step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        // 2. step: Create connection with the database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ahmed", "Ahmed", "Coding2022");

        // 3. step: Create statement
        Statement st = con.createStatement();

        // 4. step: send your queries


        //1.Example: Find the company and number_of_employees whose number_of_employees is the second highest from the companies table
        // 1st way (easy): By using OFFET and FETCH NEXT
        String sql1 = "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY";

        ResultSet resultSet1 = st.executeQuery(sql1);
        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company")+"- "+resultSet1.getString("number_of_employees"));
        }

        System.out.println("----------------");

        // 2nd way: By using sub-query
        String sql2 = "SELECT company, number_of_employees \n" +
                "FROM companies \n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "               FROM companies\n" +
                "               WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "               FROM companies));";
        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("company")+" - " +resultSet2.getInt("number_of_employees"));
        }

        System.out.println("-------------------");
        //2.Example: Find the company names and number of employees whose number of employees is less than the average number of employees

        String sql3 = "SELECT company, number_of_employees \n" +
                "FROM companies \n" +
                "WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies);\n" +
                "\n";
        ResultSet resultSet3 = st.executeQuery(sql3);
        while (resultSet3.next()){
            System.out.println(resultSet3.getString("company")+"- " +resultSet3.getInt("number_of_employees"));
        }

       // last step: Close the connection and statement
        con.close();;
        st.close();



    }
}

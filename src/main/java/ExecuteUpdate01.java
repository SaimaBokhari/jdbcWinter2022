import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // We need to follow these 5 steps:

        // 1. step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        // 2. step: Create connection with the database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ahmed", "Ahmed", "Coding2022");

        // 3. step: Create statement
        Statement st = con.createStatement();

        // 4. step: send your queries

        //1.Example: Update the number of employees to 16000 if the number of employees is less than the average number of employees

        String sql1 = "UPDATE companies SET number_of_employees = 16000 WHERE number_of_employees < ((SELECT AVG (number_of_employees) FROM companies))";

        // to update and  see how many columns are updates
        int numOfRecordsUpdated = st.executeUpdate(sql1);
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated);

        // to print them on the console
        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet1 = st.executeQuery(sql2);
        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"- "+ resultSet1.getString(2)+"- "+resultSet1.getInt(3));
        }

        // last step: Close the connection and statement
        con.close();;
        st.close();



    }
}

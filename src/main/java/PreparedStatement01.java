import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // We need to follow these 5 steps:

        // 1. step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        // 2. step: Create connection with the database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Ahmed", "Ahmed", "Coding2022");

        // 3. step: Create statement
        Statement st = con.createStatement();

        //1.Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement

        //1. Step: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2. Step: Create Prepared Statement Object
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. Step: Assign the values by using 'setInt(), setString() ...' methods.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4. Step: Execute the Query
        int numOfRecordsUpdated1 = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated1 = " + numOfRecordsUpdated1);

        // To see the updated table
        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet1  = st.executeQuery(sql2);
        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1)+"- "+resultSet1.getString(2)+"- "+resultSet1.getInt(3));
        }

        System.out.println("------------------");
        //2.Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int numOfRecordsUpdated2 = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated2 = " + numOfRecordsUpdated2);


        String sql3 = "SELECT * FROM companies";
        ResultSet resultSet2  = st.executeQuery(sql3);
        while (resultSet2.next()){
            System.out.println(resultSet2.getInt(1)+"- "+resultSet2.getString(2)+"- "+resultSet2.getInt(3));
        }

        // last step: Close the connection and statement
        con.close();;
        st.close();
        pst1.close();


    }
}

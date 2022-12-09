public class Runner {
    public static void main(String[] args) {
        // 1. step: Registration to the driver
        // 2. step: Create connection with the database

        JdbcUtils.connectToDatabase("localhost", "Ahmed", "Ahmed", "Coding2022");


        // 3. step: Create Statement
        JdbcUtils.createStatement();

        // 4. step: Execute the query
        JdbcUtils.createTable("students","name VARCHAR (20)", "id int", "address Varchar (50)", "tel BIGINT");

        // Drop the table
         JdbcUtils.dropTable("students");


        // 5 step: Close the connection and statement
         JdbcUtils.closeConnectionAndStatement();





    }
}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// We are creating this Utils class because we don't want the system to throw exception and stop
// To do that we need to handle the exception using try/catch block
// So we create methods with try/catch block for all the steps that we need to do in normal class

public class JdbcUtils {

    // We need to follow these 5 steps:
    // 1. step: Registration to the driver
    // 2. step: Create connection with the database


    private static Connection connection;
    private static Statement statement;

    public static Connection connectToDatabase(String hostName, String dataBaseName,String username, String password){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" +hostName+ ":5432/" +dataBaseName, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connected");
        return connection;

    }

    // 3. step: Create Statement

    public static Statement createStatement(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Statement created");
        return statement;
    }

    // 4. step: Execute the query
    public static boolean execute(String query){
        boolean isExecute;
        try {
           isExecute = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Query executed");
        return isExecute;
    }

    // 5 step: Close the connection and statement

    public static void closeConnectionAndStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(connection.isClosed() && statement.isClosed()){
                System.out.println("Connection and Statement are closed");
            }else{
                System.out.println("Connection and Statement are not closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Drop the table
    public static void dropTable(String tableName){

        try {
            statement.execute("DROP TABLE " + tableName);
            System.out.println("Table named " + tableName + " is dropped.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to create a table
    public static void createTable(String tableName, String...columnName_DataType ){

        StringBuilder columnName_DataTypeInString = new StringBuilder("");
        for(String w: columnName_DataType ){
            columnName_DataTypeInString.append(w).append(",");
        }

        columnName_DataTypeInString.deleteCharAt(columnName_DataTypeInString.lastIndexOf(","));

        try {
            statement.execute( "CREATE TABLE "+ tableName +"(" + columnName_DataTypeInString +  ")");
            System.out.println("Table "+ tableName + " is created." );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    // to insert values/data into a table

    public static void insertDataToTable(String tableName, String... columnName_value){
        //syntax for inserting data into a table
        // String query = "INSERT INTO tableName(columnName1, columnName2, ...) VALUES(value1, value2, ...)";

        String query = "INSERT INTO "+tableName+"(columnName1, columnName2, ...) VALUES(value1, value2, ...)";

        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Create a method to get column data into a list
    public static List<Object> getColumnList(String columnName, String tableName){
        ResultSet resultSet = null;
        List<Object> columnData = new ArrayList<>();

        String query ="SELECT "+columnName+" FROM "+tableName;

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                columnData.add(resultSet.getObject(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return columnData;
    }







}

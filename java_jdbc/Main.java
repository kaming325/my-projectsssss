import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main{
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/testing";
    
    static final String USER = "root";
    static final String PASS = "Dllm4439";
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            String command = "select * from test_java";
            ResultSet result = statement.executeQuery(command);
            while(result.next())  
                System.out.println(result.getInt(1)+"  "+result.getString(2)+"  ");  
            connection.close();  
        
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
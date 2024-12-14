package identity.TuanHuy.UI;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseConnection {
    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        System.out.println("hello world");
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "12345";
        String query = "select * from author";
        try {

            // bước 1 tạo 1 connection bằng interface connection
             connection = DriverManager.getConnection(jdbcUrl,username,password);
             System.out.println(connection.getCatalog() + "connected");

            // bước 2 tạo 1 statement , đây là 1 đối tượng để mình gửi nhờ câu lệnh query
             statement = connection.createStatement();

            // bước 3 : execute query
             resultSet = statement.executeQuery(query);

             while(resultSet.next()){ // Di chuyển con trỏ đến hàng tiếp theo
                    int id = resultSet.getInt("id"); // Lấy giá trị từ cột "id"
                    String biography = resultSet.getString("biography");
                 System.out.println("id = "+id+" biography "+biography);
             }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                if(resultSet != null) resultSet.close();
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}

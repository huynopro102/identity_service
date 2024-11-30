package identity.TuanHuy.UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.net.URL;
public class db_insert {
    public static void main(String[] args) throws FileNotFoundException {
        PreparedStatement mediaStatement = null;
        PreparedStatement songStatement = null;
        Connection connection = null;
        // syntax try--witch--resources
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","12345");
            // luôn luôn để AutoCommit = false
            connection.setAutoCommit(false);

            // tính giá trị của kích thước file , tên file , suffix file
            URL path_resources = db_insert.class.getClassLoader().getResource("static/video/24H.mp4");
            File file = new File(path_resources.getFile());
            Long size_file = null;
            String name_file = null;
            String suffix = null;
            if(file.exists()){
                    size_file = file.length();
                    name_file = file.getName();
                    suffix = "mp4";
            }else{
                throw new Exception("ko tim thay file");
            }

            String insertMediaQuery = "insert into media(type, file_path, file_size, file_name, file_extension, upload_date) values (?,?,?,?,?,?)";
            // Statement.RETURN_GENERATED_KEYS là lấy khóa chính ra
            mediaStatement = connection.prepareStatement(insertMediaQuery,Statement.RETURN_GENERATED_KEYS);
            mediaStatement.setString(1,"AUDIO");
            mediaStatement.setString(2,"audio/Synthwave goose - Blade Runner 2049.mp3");
            mediaStatement.setLong(3,9086569L);
            mediaStatement.setString(4,"Synthwave goose - Blade Runner 2049.mp3");
            mediaStatement.setString(5,"mp3");
            mediaStatement.setDate(6, new java.sql.Date(new java.util.Date().getTime())); // Sử dụng java.sql.Date


            int affectedRows = mediaStatement.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("no rows affected co loi");
            }

            // lấy id của bảng media
            long mediaId ;
            try(ResultSet generatedkeys = mediaStatement.getGeneratedKeys()){
                if(generatedkeys.next()){
                    mediaId = generatedkeys.getLong(1);
                    System.out.println("value "+mediaId);
                }else{
                    throw new SQLException("take mediaId generatedKeys failed ");
                }
            }

            // thêm bảng song
            String insertSongQuery  = "insert into song(genre,release_date,title,author_id,media_id) values (?,?,?,?,?)";
            songStatement = connection.prepareStatement(insertMediaQuery);
            songStatement.setString(1,"");

            System.out.println("Media inserted successfully with ID: "+mediaId);

            // cam ket thanh cong
            connection.close();

        } catch (SQLException e) {
           try{
               if (connection != null) {
                   connection.rollback();
                   System.out.println("Transaction rolled back due to error: " + e.getMessage());
               }
           }catch (Exception exRoolback){
               exRoolback.printStackTrace();
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

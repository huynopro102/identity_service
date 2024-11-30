package identity.TuanHuy.UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class check_len {
    public static void main(String[]  args){
        URL resource = check_len.class.getClassLoader().getResource("static/video/24H.mp4");
        // Lấy đường dẫn thư mục hiện tại của file .java đang chạy
        String currentPath = new File("").getAbsolutePath();
        System.out.println("Đường dẫn hiện tại: " + currentPath);
        System.out.println(resource);
        System.out.println(resource.getPath());
        File file = new File(resource.getFile());
        if(file.exists() ){
            try {
                System.out.println("co");
                byte[] arr = new byte[1024];
                FileInputStream fileInputStream = new FileInputStream(resource.getFile());
                System.out.println(fileInputStream.available());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("ko co");
        }
    }
}

package identity.TuanHuy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NewmanRunner {
    public static void main(String[] args){
        System.out.println("hello newman");
        // Đường dẫn đến file collection
        String collectionPath = "E:\\intellij_repo\\TuanHuy identity\\TuanHuy\\identity.postman_collection.json";

        // Đường dẫn đến Newman
        String newmanPath = "E:\\intellij_repo\\TuanHuy identity\\TuanHuy\\node_modules\\.bin\\newman.cmd"; // Đường dẫn tới Newman

        // Command để chạy Newman
        String command = newmanPath + " run " + collectionPath;
        System.out.println("Command: " + command); // In lệnh ra

        try {
            // Tách lệnh thành các phần tử
            ProcessBuilder processBuilder = new ProcessBuilder(newmanPath, "run", collectionPath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Đọc đầu ra
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Đợi quá trình kết thúc
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

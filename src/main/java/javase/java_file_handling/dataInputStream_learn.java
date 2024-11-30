package javase.java_file_handling;

import java.io.*;

public class dataInputStream_learn {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\student_data.bin");
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeUTF("janna opera");
        dataOutputStream.writeDouble(9.7);

        dataOutputStream.writeInt(2);
        dataOutputStream.writeUTF("harry potter");
        dataOutputStream.writeDouble(5.7);
        dataOutputStream.close();
        fileOutputStream.close();

        // đọc dữ liệu
        DataInputStream dataInputStream = new DataInputStream(new FileInputStream("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\student_data.bin"));
        while (dataInputStream.available() > 0){
            int id = dataInputStream.readInt();
            String name = dataInputStream.readUTF();
            double age = dataInputStream.readDouble();
            float age1 = dataInputStream.readFloat();
            System.out.println(name);
            System.out.println(age);
            System.out.println(id);
            // nếu ko có trường dữ liệu thì hiển thị lỗi
            System.out.println(age1);
        }
    }
}

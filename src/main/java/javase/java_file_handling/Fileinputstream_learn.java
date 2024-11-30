package javase.java_file_handling;
/*
 * Lớp FileInputStream trong java đọc được các byte từ một input file.
 * Nó được sử dụng để đọc dữ liệu theo định dạng byte (các byte stream)
 * như dữ liệu hình ảnh, âm thanh, video vv. Bạn cũng có thể đọc các dữ liệu có định dạng ký tự.
 * Tuy nhiên, để đọc các dòng ký tự (các character stream), bạn nên sử dụng lớp FileReader.
 */
import java.io.*;

public class Fileinputstream_learn {
    public static void main(String[] args) throws IOException {
        System.out.println("hello world");
        File file = new File("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\file_audio.mp3");
        if(file.exists()){
            System.out.println("file tồn tại");
        }else{
            System.out.println("file ko tồn tại");
            file.createNewFile();
            System.out.println("tạo file thành công");
        }
        byte[] buffer = new byte[(int)file.length()];
        FileInputStream fileInputStream = new FileInputStream("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\file_audio.mp3");
        System.out.println("độ dài đối tượng file mảng byte là : "+ file.length());
        System.out.println("độ dài đối tượng file mảng byte là : "+ fileInputStream.available());
        int a;
        int count = 0;
        while (( a = fileInputStream.read(buffer) ) != -1){
            count++;
        }
        fileInputStream.close();
        System.out.println("copy into array buffer "+buffer.length);

        // tạo file thành công
        File copy_file = new File("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\copy_file.mp3");
        if(!copy_file.exists()){
            copy_file.createNewFile();
            System.out.println("Tạo file copy thành công");
        }
        // sử dụng FileOuputStream để ghi file dạng byte
        FileOutputStream fileOutputStream = new FileOutputStream(copy_file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\copy_file.mp3"));
//        fileOutputStream.write(buffer);
//        fileOutputStream.close();
        bufferedOutputStream.write(buffer);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        fileOutputStream.close();
        System.out.println("copy file.mp3 thành công sang copy_file.mp3");

    }
}

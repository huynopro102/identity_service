package javase.java_file_handling;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class main {
    public static void main(String[] args) throws IOException {

        File directory = new File("huynguyen-nginx");
        FileWriter fileWriter = new FileWriter("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\filewriter.txt");
        Scanner scanner = new Scanner(System.in);
        int a;
        do {
            System.out.println("""
                1 / java.io.File
                2 / java.io.FileReader
                3 / java.io.FileWriter
                0 / exit
                """);
            a = scanner.nextInt();
            scanner.nextLine();
            switch (a) {
                case 1:
                    if (directory.exists()) {
                        System.out.println("file ton tai");
                    } else {
                        System.out.println("file khong ton tai");
                        directory.mkdir();
                        System.out.println("da tao file thanh cong");
                    }
                    if (directory.exists() && directory.isDirectory()) {
                        System.out.println("đây là directory and exists");
                    }
                    String[] files = directory.list();
                    for (String file : files) {
                        System.out.println(file);
                    }
                    if (directory.exists()) {
                        String access = scanner.nextLine();
                        if (access.equals("dong y")) {
                            directory.delete();
                            System.out.println("xoa directory name is : " + directory.getName());
                        }
                    }
                    break;
                case 2:
                        File file = new File("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\filereader.txt");
                            if(file.exists()){
                                System.out.println("file đã tồn tại");
                                ArrayList arrayList = new ArrayList();
                                int[] mangint = new int[100];
                                try {
                                    FileReader fileReader = new FileReader("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\filereader.txt");
                                    int n ;
                                    while ( (n = fileReader.read()) != -1 ){
                                        arrayList.add((char)n);
                                        fileReader.close();
                                    }
                                    for (int i = 0 ;i<arrayList.size();i++){
                                        System.out.print(arrayList.get(i));
                                    }
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }else{
                                try {
                                    if(file.createNewFile()){
                                        System.out.println("tao file name is : "+file.getName());
                                    }else{
                                        System.out.println("tạo file name is : " +file.getName()+" thất bại");
                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    break;
                case 3:
                    File file2 = new File("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\filewriter.txt");
                    if(file2.exists()){
                        System.out.println("writer file đã tồn tại");
                        try {
                            fileWriter.write("""
                                            nguyen van d
                                            xuong dong lan 1
                                            tran van u
                                            xuong dong lan 2
                                            """);
                            fileWriter.append("///");
                            fileWriter.write("jkl");
                            fileWriter.close();
                            System.out.println("đã ghi file thành công ");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        System.out.println("file không tồn tại");
                        try {
                            if(file2.createNewFile()){
                                System.out.println("tạo file thành công");
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                default:
                    System.out.println("exited");
                    break;
            }
        }while(a!=0);

    }
}

package javase.java_file_handling;

import java.io.*;
import java.util.ArrayList;


class student implements Serializable{
    private static final long serialVersionUID = 1L;
    private String msv;
    private String tensv;
    private int tuoisv;

    @Override
    public String toString() {
        return "student{" +
                "msv='" + msv + '\'' +
                ", tensv='" + tensv + '\'' +
                ", tuoisv=" + tuoisv +
                '}';
    }

    public student(String msv , String tensv , int tuoisv){
        this.msv = msv;
        this.tensv = tensv;
        this.tuoisv = tuoisv;
    }

}
public class objectInputStream {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<student> danhsach = new ArrayList<>();
        danhsach.add(new student("001","nguyen van d",17));
        danhsach.add(new student("002","nguyen van c",27));
        danhsach.add(new student("003","nguyen van e",30));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\student.dat"));
        objectOutputStream.writeObject(danhsach);

        FileInputStream fileInputStream = new FileInputStream("E:\\identity_service\\src\\main\\java\\javase\\java_file_handling\\student.dat");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<student> read_danhsach = (ArrayList<student>) objectInputStream.readObject();

        for (student sv : read_danhsach){
            System.out.println(sv.toString());
        }
        objectOutputStream.close();
        fileInputStream.close();
    }
}

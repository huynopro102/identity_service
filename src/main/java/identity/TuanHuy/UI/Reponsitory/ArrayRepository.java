package identity.TuanHuy.UI.Reponsitory;


import org.springframework.stereotype.Repository;

@Repository
public class ArrayRepository {
    // giả lập hành động connect đến database
    public int[] getArray() {
    return new int[]{10, 20, 30, 40, 50};
    }
}

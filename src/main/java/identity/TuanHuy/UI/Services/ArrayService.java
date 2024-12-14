package identity.TuanHuy.UI.Services;

import identity.TuanHuy.UI.Reponsitory.ArrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrayService {

    @Autowired
    private ArrayRepository arrayRepository;

    // tính tổng các phần tử trong mảng
    public int getArraySum(){
        int arraySum = 0;
        int[] arrayint = arrayRepository.getArray();
        for(int k=0;k<arrayint.length ; k++){
            arraySum += arrayint[k];
        }
        return arraySum;
    }

}

package identity.TuanHuy;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication // this is an annotation automatically scans classes with @Service , @Repository , @Component,@Controller
public class TuanHuyApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TuanHuyApplication.class, args);
		TuanHuyApplication app = context.getBean(TuanHuyApplication.class);

		System.out.println("hello world");
		Client client = new Client();
		client.processMessage("client send");


	}

	public int[] twoSum (int[] nums , int target){
				return algorithmHashMap(nums,target);
	}

	public int[] algorithmHashMap(int[] nums , int target){
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int i =0 ;i<nums.length ; i++){
				int complement = target - nums[i];
				if(map.containsKey(complement)){
					// 9 - 2 = (7)
					// 9 - (7) = 2
					return new int[]{map.get(complement),i};
					// map.get(key) just return key not value
				}
				map.put(nums[i],i);
		}
		return new int[]{};
	}

	public int[] algorithmBruteForce(int[] nums , int target){
		for(int i=0;i<nums.length;i++){
			for(int k=i+1 ; k < nums.length ; k++){
				if(nums[i]+nums[k]==target){
					return new int[]{i,k};
				}
			}
		}
		return new int[]{};
	}

	public int[] algorithmTwoPointers(int[] nums , int target){
		int[][] indexedNums = new int[nums.length][2];
		for(int i =0 ; i< nums.length;i++){
			indexedNums[i][0] = nums[i]; // lưu key
			indexedNums[i][1] = i; // lưu index trước khi sắp xếp
		}

		// sap xep theo tang dan
		Arrays.sort(indexedNums,(a,b) -> Integer.compare(a[0],b[0]));

		int left=0;
		int right=nums.length-1;
		while(left < right){
			int sum = indexedNums[left][0] + indexedNums[right][0];
			if(sum == target){
				return new int[]{indexedNums[left][1],indexedNums[right][1]};
			}else if (sum < target){
				//Tại sao sum < target phải tăng left mà không phải giảm right?
				left++;
			}else{
				right--;
			}
		}
		return new int[]{};
	}

}

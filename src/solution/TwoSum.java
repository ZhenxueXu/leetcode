package solution;

import java.util.HashMap;

/**
 * @author Think
 * @className TwoSum
 * @date 2019/5/5
 *
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 **/
public class TwoSum {
	public int[] twoSum(int[] numbers, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] res = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			int diff =  target - numbers[i];
			Integer value = map.get(diff);
			if (value != null && value != i){
				if (value > i){
					res[0] = i + 1;
					res[1] = value + 1;
				}else {
					res[0] = value + 1;
					res[1] = i + 1;
				}
				return res;
			}
			map.put(numbers[i], i);
		}
		return res;
	}
}

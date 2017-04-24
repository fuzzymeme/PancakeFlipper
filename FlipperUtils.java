package pancakeflipperrevisited;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlipperUtils {

	public static String toStringSequence(PancakeSequence seq, PancakeStack stack) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("(");
		for(int i = seq.getPosition(); i < seq.getPosition() + seq.getLength(); i++){
			buffer.append(stack.get(i));
			if(i < (seq.getPosition() + seq.getLength()) - 1){
				buffer.append(" ");
			}
		}
		buffer.append(")");
		return buffer.toString();
	}
	
	public static Integer[] getPermutation(int length) {
		
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
					
		return list.toArray(new Integer[list.size()]); 
		
	}
	
	public static BigInteger getFactorial(int size) {
				
		BigInteger fact = BigInteger.valueOf(1);

	    for (int i = 1; i <= size; i++)
	        fact = fact.multiply(BigInteger.valueOf(i));
		
		return fact;		
	}
	
	
	public static void main(String[] args) {
		
		int length = 4000;
		System.out.println(length + ":" + FlipperUtils.getFactorial(length));
	}
}

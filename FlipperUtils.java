package pancakeflipperrevisited;

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

}

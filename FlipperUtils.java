package pancakeflipperrevisited;

import java.awt.Point;
import java.util.List;

public class FlipperUtils {


//	public static boolean isCorrectlyStacked(List<Integer> stack){
//
//		int i = 0;
//		while(i < stack.size()){
//			if(stack.get(i) != i){
//				return false;
//			}
//			i++;
//		}	
//		return true;
//	}

//	public static Point find(int topPancakeSize, PancakeStack stack){
//
//		Point pairsPostions = new Point(-1, -1);
//		for(int i = 0; i < stack.size(); i++){
//			if(stack.get(i) == (topPancakeSize -1)){
//				pairsPostions.x = stack.size() - i;
//			}
//			if(stack.get(i) == (topPancakeSize +1)){
//				pairsPostions.y = stack.size() - i;
//			}
//		}
//
//		return pairsPostions;
//	}

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

}

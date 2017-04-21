package pancakeflipperrevisited;

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

}

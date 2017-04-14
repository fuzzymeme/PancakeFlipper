package pancakeflipperrevisited;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PancakeStack {
	
	protected List<Integer> stack; // TODO Better private??
	private int flipCount = 0;
	
	public PancakeStack(){
		stack = new ArrayList<Integer>();
	}
	
	public PancakeStack(List<Integer> initialStack){
		this();
		stack.addAll(initialStack);
	}

	public void initialise(int stackSize){
		
		stack = new ArrayList<Integer>(stackSize);
		for(int i = 0; i < stackSize; i++){
			stack.add(i);
		}
	}
	
	public void shuffleStack(){
		Collections.shuffle(stack);
	}
	
	public int get(int index){

		if(stack != null && index >= stack.size()){
			throw new IndexOutOfBoundsException("Index (" + index + ") out out bounds");
		}
		return stack.get(index);
	}
	
	/** 
	 * Flips the pancake stack 
	 * @param flipPoint - the point where to flip counting back from the right/end of the list
	 */
	// TODO takes the flip point counting from the right - kinda awkward
	public void flip(int flipPoint) {

		List<Integer> flipped = new LinkedList<Integer>();
		flipped.addAll(reverse(stack.subList(0, stack.size() - flipPoint)));
		flipped.addAll(stack.subList(stack.size() - flipPoint, stack.size()));
		stack = flipped;
		flipCount++;
	}
	
	public int getFlipCount(){
		return flipCount;
	}
	
//	protected void swap(int r, int s){
//		int tmp = stack.get(r);
//		stack.set(r, stack.get(s));
//		stack.set(s, tmp);
//	}
	
	private List<Integer> reverse(List<Integer> list){

		Collections.reverse(list);
		return list;
	}
	
	public int size(){
		return stack.size();
	}
	
	public boolean stackEquals(List<Integer> other) {

		if (other == null)
			return false;
		if (stack == null) {
			if (other != null)
				return false;
		} else if (!stack.equals(other))
			return false;
		return true;
	}

	public boolean isCorrectlyStacked(){
		
		int i = 0;
		while(i < stack.size()){
			if(stack.get(i) != i){
				return false;
			}
			i++;
		}	
		return true;
	}

	@Override
	public String toString() {
		return stack.toString();
	}
	
}

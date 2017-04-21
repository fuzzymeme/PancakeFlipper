package pancakeflipperrevisited;

import java.util.ArrayList;
import java.util.Arrays;

public class MockSequencedPancakeStack extends SequencedPancakeStack {
	
	public MockSequencedPancakeStack(Integer... initialStack){
		super(Arrays.asList(initialStack));
		seqs = new ArrayList<PancakeSequence>();
	}
	
	public void addSequence(int position, int bottom, int top) {
		seqs.add(new PancakeSequence(position, bottom, top));
	}
	
	public void addSequence(PancakeSequence seq) {
		seqs.add(seq);
	}	
}

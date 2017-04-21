package pancakeflipperrevisited;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SequenceBasedStackTests {

	@Test
	public void test() {
		SequencedPancakeStack stack = new SequencedPancakeStack(0, 1, 4, 2, 3);
		
		int totalSequencedLength = 0;
		for(PancakeSequence seq: stack.getSequences()){
			totalSequencedLength += seq.getLength();
		}
		assertEquals(5, totalSequencedLength);
	}

}

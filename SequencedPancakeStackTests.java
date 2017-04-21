package pancakeflipperrevisited;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SequencedPancakeStackTests {

	@Test
	public void test_GivenStack_WhenConstructed_ThenCreatesCorrectSequences() {
		SequencedPancakeStack pStack = new SequencedPancakeStack(Arrays.asList(1, 0, 5, 4, 6, 3, 2));
		
		assertEquals(new PancakeSequence(0, 1, 0), pStack.getSequences().get(0));
		assertEquals(new PancakeSequence(2, 5, 4), pStack.getSequences().get(1));
		assertEquals(new PancakeSequence(4, 6, 6), pStack.getSequences().get(2));
		assertEquals(new PancakeSequence(5, 3, 2), pStack.getSequences().get(3));
	}

	@Test
	public void test_GivenTwoEqualStacks_WhenEqualsCalled_ThenReturnsTrue() {
		SequencedPancakeStack pStack1 = new SequencedPancakeStack(Arrays.asList(1, 0, 5, 4, 6, 3, 2));
		SequencedPancakeStack pStack2 = new SequencedPancakeStack(Arrays.asList(1, 0, 5, 4, 6, 3, 2));
		
		assertEquals(pStack1, pStack2);
	}

}

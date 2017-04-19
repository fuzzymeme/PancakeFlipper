package pancakeflipperrevisited;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SequenceBasedFlipperTests {

	private SequenceBasedFlipper flipper = new SequenceBasedFlipper();
	
	@Test
	public void test_GivenSimpleStack_WhenAskedForFlipCount_ThenReturns4() {
		assertEquals(4, getFlipCountForStack(4, 3, 1, 2, 0));
	}

	@Test
	public void test_GivenCompletedStack_WhenAskedForFlipCount_ThenReturns0() {
		assertEquals(0, getFlipCountForStack(0, 1, 2, 3, 4));
	}

	@Test
	public void test_GivenStack_WhenAskedForFlipCount_ThenReturns3() {
		assertEquals(3, getFlipCountForStack(0, 1, 3, 2, 4));
	}

	@Test
	public void test_GivenExamples() {
		assertEquals(1, getFlipCountForStack(3, 2, 1, 0, 4));
		assertEquals(1, getFlipCountForStack(2, 1, 0, 3, 4));
		assertEquals(1, getFlipCountForStack(1, 0, 2, 3, 4));
	}

	// Helper
	private int getFlipCountForStack(Integer...a) {		
		SequencedPancakeStack pStack = new SequencedPancakeStack(Arrays.asList(a));	
		
		OptionalInt returnOptional = flipper.flipUntilCorrectlyStacked(pStack);
		if(!returnOptional.isPresent()) {
			fail("Couldn't find a solution for " + a);
		}
		return returnOptional.getAsInt();
	}

}

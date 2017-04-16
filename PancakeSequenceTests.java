package pancakeflipperrevisited;

import static org.junit.Assert.*;

import org.junit.Test;

import pancakeflipperrevisited.PancakeSequence.DeltaSize;

public class PancakeSequenceTests {

	@Test
	public void test_GivenDecreasingSequenceWithoutDeltaSet_WhenToStringCalled_ThenCorrectStringReturned() {
		PancakeSequence seq = new PancakeSequence(4, 5, 3, DeltaSize.NA);
		
		assertEquals("(5 4 3)", seq.toString());
	}

	@Test
	public void test_GivenDecreasingSequenceWithDeltaSet_WhenToStringCalled_ThenCorrectStringReturned() {
		PancakeSequence seq = new PancakeSequence(4, 5, 3, DeltaSize.DECREASING);
		
		assertEquals("(5 4 3)", seq.toString());
	}
	
	
	@Test
	public void test_GivenIncreasingSequenceWithoutDeltaSet_WhenToStringCalled_ThenCorrectStringReturned() {
		PancakeSequence seq = new PancakeSequence(4, 2, 6, DeltaSize.NA);
		
		assertEquals("(2 3 4 5 6)", seq.toString());
	}

	@Test
	public void test_GivenIncreasingSequenceWithDeltaSet_WhenToStringCalled_ThenCorrectStringReturned() {
		PancakeSequence seq = new PancakeSequence(4, 2, 6, DeltaSize.INCREASING);
		
		assertEquals("(2 3 4 5 6)", seq.toString());
	}

}

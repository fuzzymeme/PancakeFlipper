package pancakeflipperrevisited;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.OptionalInt;

import org.junit.Test;

public class SequenceBasedFlipperTests {

	private SequenceBasedFlipper flipper = new SequenceBasedFlipper();
	
	@Test
	public void test_GivenStackWithSingleMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(1, 0, 5, 4, 6, 2, 3);
		pStack.addSequence(0, 1, 0);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 2, 3);
		
		PancakeSequence expectedMatch = new PancakeSequence(5, 2, 3);
		
		PancakeSequence match = flipper.getMatchForCase37(pStack);

		assertEquals(expectedMatch, match);
	}

	@Test
	public void test_GivenStackWithSingleMoveOptionWithDeltaMinusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(2, 3, 5, 4, 6, 1, 0);
		pStack.addSequence(0, 2, 3);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 1, 0);
		
		PancakeSequence expectedMatch = new PancakeSequence(5, 1, 0);
		
		PancakeSequence match = flipper.getMatchForCase37(pStack);

		assertEquals(expectedMatch, match);
	}
	
	@Test
	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(0, 1, 5, 4, 6, 2, 3);
		pStack.addSequence(0, 0, 1);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 2, 3);
		
		PancakeSequence expectedSeq1 = new PancakeSequence(0, 0, 1);
		PancakeSequence expectedSeq2 = new PancakeSequence(5, 2, 3);
		SequencePair expectedSeqPair = new SequencePair(expectedSeq1, expectedSeq2);
		
		SequencePair seqPair = flipper.getMatchForCase18(pStack);

		assertEquals(expectedSeqPair, seqPair);
	}
	
	@Test
	public void test_GivenStackWithTwoMoveOptionWithDeltaMinusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(3, 2, 5, 4, 6, 1, 0);
		pStack.addSequence(0, 3, 2);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 1, 0);
		
		PancakeSequence expectedSeq1 = new PancakeSequence(0, 3, 2);
		PancakeSequence expectedSeq2 = new PancakeSequence(5, 1, 0);
		SequencePair expectedSeqPair = new SequencePair(expectedSeq1, expectedSeq2);
		
		SequencePair seqPair = flipper.getMatchForCase18(pStack);
		
		assertEquals(expectedSeqPair, seqPair);
	}
	
	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_match_caseSingleFlipToBase() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(6, 5, 0, 1, 4, 3, 2);
		pStack.addSequence(0, 6, 5);
		pStack.addSequence(2, 0, 1);
		pStack.addSequence(4, 4, 4);
		pStack.addSequence(5, 3, 2);
		
		PancakeSequence expectedSeq = new PancakeSequence(0, 6, 5);
		
		PancakeSequence seq = flipper.getMatchForCaseSingleToBase(pStack);

		assertEquals(expectedSeq, seq);
	}
	
	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_match_caseSingleFlipToBaseWithSingleItemSequence() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(6, 0, 1, 5, 4, 3, 2);
		pStack.addSequence(0, 6, 6);
		pStack.addSequence(1, 0, 1);
		pStack.addSequence(4, 5, 4);
		pStack.addSequence(5, 3, 2);
		
		PancakeSequence expectedSeq = new PancakeSequence(0, 6, 6);
		
		PancakeSequence seq = flipper.getMatchForCaseSingleToBase(pStack);

		assertEquals(expectedSeq, seq);
	}
	
	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_match_case26_plus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(0, 1, 5, 4, 6, 3, 2);
		pStack.addSequence(0, 0, 1);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 3, 2);
		
		PancakeSequence expectedSeq1 = new PancakeSequence(0, 0, 1);
		PancakeSequence expectedSeq2 = new PancakeSequence(5, 3, 2);
		SequencePair expectedSeqPair = new SequencePair(expectedSeq1, expectedSeq2);
		
		SequencePair seqPair = flipper.getMatchForCase26(pStack);

		assertEquals(expectedSeqPair, seqPair);
	}
	
	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaMinusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_match_case26_minus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(3, 2, 5, 4, 6, 0, 1);
		pStack.addSequence(0, 3, 2);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 0, 1);
		
		PancakeSequence expectedSeq1 = new PancakeSequence(0, 3, 2);
		PancakeSequence expectedSeq2 = new PancakeSequence(5, 0, 1);
		SequencePair expectedSeqPair = new SequencePair(expectedSeq1, expectedSeq2);
		
		SequencePair seqPair = flipper.getMatchForCase26(pStack);
		
		assertEquals(expectedSeqPair, seqPair);
	}

	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_match_case45_plus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(1, 0, 5, 4, 3, 2);
		pStack.addSequence(0, 1, 0);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 3, 2);
		
		PancakeSequence expectedSeq1 = new PancakeSequence(0, 1, 0);
		PancakeSequence expectedSeq2 = new PancakeSequence(4, 3, 2);
		SequencePair expectedSeqPair = new SequencePair(expectedSeq1, expectedSeq2);
		
		SequencePair seqPair = flipper.getMatchForCase45(pStack);

		assertEquals(expectedSeqPair, seqPair);
	}
	
	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaMinusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_match_case45_minus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(2, 3, 5, 4, 0, 1);
		pStack.addSequence(0, 2, 3);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 0, 1);
		
		PancakeSequence expectedSeq1 = new PancakeSequence(0, 2, 3);
		PancakeSequence expectedSeq2 = new PancakeSequence(4, 0, 1);
		SequencePair expectedSeqPair = new SequencePair(expectedSeq1, expectedSeq2);
		
		SequencePair seqPair = flipper.getMatchForCase45(pStack);

		assertEquals(expectedSeqPair, seqPair);
	}
	
	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_flip_caseSingleFlipToBase() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(6, 5, 0, 1, 4, 3, 2);
		pStack.addSequence(0, 6, 5);
		pStack.addSequence(2, 0, 1);
		pStack.addSequence(4, 4, 2);
		
		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(2, 3, 4, 1, 0, 5, 6);
		expectedStack.addSequence(5, 5, 6);
		expectedStack.addSequence(3, 1, 0);
		expectedStack.addSequence(0, 2, 4);
		expectedStack.setFlipCount(1);
				
		flipper.flipForCaseSingleToBase(pStack);

		assertEquals(expectedStack, pStack);
	}

	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaMinusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_flips_Case18() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(0, 1, 5, 4, 6, 2, 3);
		pStack.addSequence(0, 0, 1);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 2, 3);

		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(6, 4, 5, 0, 1, 2, 3);
		expectedStack.addSequence(1, 4, 5);
		expectedStack.addSequence(0, 6, 6);
		expectedStack.addSequence(3, 0, 3);
		expectedStack.setFlipCount(2);
		
		SequencePair seqPair = flipper.getMatchForCase18(pStack);
		flipper.flipForCase18(pStack, seqPair);
		
		assertEquals(expectedStack, pStack);
	}

	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_flips_Case18_Plus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(3, 2, 5, 4, 6, 1, 0);
		pStack.addSequence(0, 3, 2);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 1, 0);

		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(6, 4, 5, 3, 2, 1, 0);
		expectedStack.addSequence(1, 4, 5);
		expectedStack.addSequence(0, 6, 6);
		expectedStack.addSequence(3, 3, 0);
		expectedStack.setFlipCount(2);
		
		SequencePair seqPair = flipper.getMatchForCase18(pStack);
		flipper.flipForCase18(pStack, seqPair);
		
		assertEquals(expectedStack, pStack);
	}
	
	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaMinusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_flips_Case26_minus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(3, 2, 5, 4, 6, 0, 1);
		pStack.addSequence(0, 3, 2);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 0, 1);

		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(5, 4, 6, 0, 1, 2, 3);
		expectedStack.addSequence(0, 5, 4);
		expectedStack.addSequence(2, 6, 6);
		expectedStack.addSequence(3, 0, 3);
		expectedStack.setFlipCount(2);
		
		SequencePair seqPair = flipper.getMatchForCase26(pStack);
		flipper.flipForCase26(pStack, seqPair);
		
		assertEquals(expectedStack, pStack);
	}

	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_flips_Case26_Plus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(0, 1, 5, 4, 6, 3, 2);
		pStack.addSequence(0, 0, 1);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 3, 2);

		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(5, 4, 6, 3, 2, 1, 0);
		expectedStack.addSequence(0, 5, 4);
		expectedStack.addSequence(2, 6, 6);
		expectedStack.addSequence(3, 3, 0);
		expectedStack.setFlipCount(2);
		
		SequencePair seqPair = flipper.getMatchForCase26(pStack);
		flipper.flipForCase26(pStack, seqPair);
		
		assertEquals(expectedStack, pStack);
	}
	
	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaMinusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_flips_Case45_minus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(1, 0, 5, 4, 3, 2);
		pStack.addSequence(0, 1, 0);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 3, 2);

		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(5, 4, 3, 2, 1, 0);
		expectedStack.addSequence(0, 5, 4);
		expectedStack.addSequence(2, 3, 0);
		expectedStack.setFlipCount(3);
		
		SequencePair seqPair = flipper.getMatchForCase45(pStack);
		flipper.flipForCase45(pStack, seqPair);
		
		assertEquals(expectedStack, pStack);
	}

	@Test
//	public void test_GivenStackWithTwoMoveOptionWithDeltaPlusOne_WhenAskedToFindThatOption_ThenReturnsCorrectOption() {
	public void test_flips_Case45_Plus() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(1, 0, 5, 4, 3, 2);
		pStack.addSequence(0, 1, 0);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 3, 2);
		
		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(5, 4, 3, 2, 1, 0);
		expectedStack.addSequence(0, 5, 4);
		expectedStack.addSequence(2, 3, 0);
		expectedStack.setFlipCount(3);
		
		SequencePair seqPair = flipper.getMatchForCase45(pStack);
		flipper.flipForCase45(pStack, seqPair);
		
		assertEquals(expectedStack, pStack);
	}

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
	public void test_GivenExample_WhenAskedForFlipCount_ThenReturnsTheExpectedCount() {
		assertEquals(1, getFlipCountForStack(3, 2, 1, 0, 4));
		assertEquals(1, getFlipCountForStack(2, 1, 0, 3, 4));
		assertEquals(1, getFlipCountForStack(1, 0, 2, 3, 4));
		assertEquals(4, getFlipCountForStack(1, 0, 2, 4, 3));
		assertEquals(4, getFlipCountForStack(0, 1, 4, 5, 2, 3));
		assertEquals(4, getFlipCountForStack(2, 1, 0, 4, 3, 5));
		assertEquals(7, getFlipCountForStack(0, 4, 1, 5, 3, 2));
		assertEquals(6, getFlipCountForStack(1, 4, 0, 5, 3, 2));
		assertEquals(7, getFlipCountForStack(0, 2, 1, 5, 6, 4, 3));
	}
	
	@Test
	public void test_GivenTwoEqualStacks_WhenEqualsCalled_ThenReturnsTrue() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(1, 0, 5, 4, 6, 3, 2);
		pStack.addSequence(0, 1, 0);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 3, 2);
				
		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(1, 0, 5, 4, 6, 3, 2);
		expectedStack.addSequence(0, 1, 0);
		expectedStack.addSequence(2, 5, 4);
		expectedStack.addSequence(4, 6, 6);
		expectedStack.addSequence(5, 3, 2);

		assertEquals(expectedStack, pStack);
	}

	@Test
//	public void test_GivenStack_WhenAskedToFlip_ThenSpotsCorrectFlipAndMakesCorrectFlip() {
	public void test_One() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(1, 0, 5, 4, 6, 3, 2);
		pStack.addSequence(0, 1, 0);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
		pStack.addSequence(5, 3, 2);
				
		flipper.makeNextFlip(pStack);		
		
		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(6, 1, 0, 5, 4, 3, 2);
		expectedStack.addSequence(1, 1, 0);
		expectedStack.addSequence(0, 6, 6);
		expectedStack.addSequence(3, 5, 2);
		
		expectedStack.setFlipCount(2);

		assertEquals(expectedStack, pStack);
	}
	
	@Test
	public void test_Two() {
//		public void test_GivenStackWithAlteredSeqOrdering_WhenAskedToFlip_ThenSpotsCorrectFlipAndMakesCorrectFlip() {
		
		MockSequencedPancakeStack pStack = new MockSequencedPancakeStack(1, 0, 5, 4, 6, 3, 2);
		pStack.addSequence(0, 1, 0);
		pStack.addSequence(5, 3, 2);
		pStack.addSequence(2, 5, 4);
		pStack.addSequence(4, 6, 6);
				
		flipper.makeNextFlip(pStack);		
		
		MockSequencedPancakeStack expectedStack = new MockSequencedPancakeStack(6, 1, 0, 5, 4, 3, 2);
		expectedStack.addSequence(1, 1, 0);
		expectedStack.addSequence(0, 6, 6);
		expectedStack.addSequence(3, 5, 2);
		
		expectedStack.setFlipCount(2);
		
		assertEquals(expectedStack, pStack);
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

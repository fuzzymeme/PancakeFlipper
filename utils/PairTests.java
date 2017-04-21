package pancakeflipperrevisited.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import pancakeflipperrevisited.PancakeSequence;

public class PairTests {

	@Test
	public void test_ConstructingAPairConstructsTheCorrectObject() {
		
		PancakeSequence seq1 = new PancakeSequence(0, 1, 2);
		PancakeSequence seq2 = new PancakeSequence(5, 12, 8);

		PancakeSequence expectedSeq1 = new PancakeSequence(0, 1, 2);
		PancakeSequence expectedSeq2 = new PancakeSequence(5, 12, 8);
		
		
		Pair<PancakeSequence> pancakeSequencePair = new Pair<>(seq1, seq2);
		
		assertEquals(expectedSeq1, pancakeSequencePair.getOne());
		assertEquals(expectedSeq2, pancakeSequencePair.getOther());		
	}

	@Test
	public void test_Equals_PassesWithMatchingPancakeSequences() {
		
		PancakeSequence seq1 = new PancakeSequence(0, 1, 2);
		PancakeSequence seq2 = new PancakeSequence(5, 12, 8);

		PancakeSequence seq3 = new PancakeSequence(0, 1, 2);
		PancakeSequence seq4 = new PancakeSequence(5, 12, 8);
		
		Pair<PancakeSequence> pancakeSequencePair1 = new Pair<>(seq1, seq2);
		Pair<PancakeSequence> pancakeSequencePair2 = new Pair<>(seq3, seq4);
		
		assertEquals(pancakeSequencePair1, pancakeSequencePair2);
		assertEquals(pancakeSequencePair1, pancakeSequencePair1);
		assertEquals(pancakeSequencePair2, pancakeSequencePair2);
	}
	
	@Test
	public void test_Equals_FailsWithNonMatchingPancakeSequences() {
		
		PancakeSequence seq1 = new PancakeSequence(0, 1, 2);
		PancakeSequence seq2 = new PancakeSequence(5, 12, 9);

		PancakeSequence seq3 = new PancakeSequence(0, 1, 2);
		PancakeSequence seq4 = new PancakeSequence(5, 12, 8);
		
		Pair<PancakeSequence> pancakeSequencePair1 = new Pair<>(seq1, seq2);
		Pair<PancakeSequence> pancakeSequencePair2 = new Pair<>(seq3, seq4);
		
		assertNotEquals(pancakeSequencePair1, pancakeSequencePair2);
	}
	
	@Test
	public void test_Equals_FailsWithDifferentTypesOfPairs() {
		
		Pair<String> pair1 = new Pair<>("Foo", "Bar");
		Pair<Integer> pair2 = new Pair<>(2, 4);
		
		assertNotEquals(pair1, pair2);
	}

}

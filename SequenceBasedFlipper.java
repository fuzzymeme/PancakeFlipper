package pancakeflipperrevisited;

import java.util.OptionalInt;

import pancakeflipperrevisited.utils.Pair;

import static pancakeflipperrevisited.Recorder.*;

public class SequenceBasedFlipper {

	public OptionalInt flipUntilCorrectlyStacked(SequencedPancakeStack pStack) {

		clearMessages();
		recordMessage("No." + pStack.getFlipCount() + " " + pStack);
		
		// Flip until correctly stacked
		boolean complete = pStack.isCorrectlyStacked();
		boolean flipSucceeded = true;
		int i = 0;
		while(!complete && flipSucceeded){	

			flipSucceeded = makeNextFlip(pStack);
			
			complete = pStack.isCorrectlyStacked();
			
			i++;
//			if(i > 1) {
//				System.exit(0);
//			}
		}

		if(complete){
			System.out.println("Complete after " + pStack.getFlipCount() + " flips");
			showMessages();
		}
		else{
			System.out.println("***************************");
			System.out.println("Flipping failed to complete");
			System.out.println("***************************");
			System.out.println("Stack: " + pStack);
			showMessages();
			return OptionalInt.empty();
		}

		return OptionalInt.of(pStack.getFlipCount());
	}
	
	public boolean makeNextFlip(SequencedPancakeStack pStack) {
		
//		System.out.println("---- makeNextFlip -----");

		// If one seq that simply needs to be flipped
		if(pStack.getSequenceCount() == 1){
			flipWithRecord(pStack, pStack.size());
			return true;
		}
		
		// TODO Have a flip to base for when the highest number appears at the top. Reuse flipCase37
		PancakeSequence match = getMatchForCaseSingleToBase(pStack);
		if(match != null){
//			System.out.println("getMatchForCaseSingleToBase");
			flipForCaseSingleToBase(pStack);
			return true;
		}
		
		// Spot any single move solutions
		match = getMatchForCase37(pStack);
		if(match != null){
//			System.out.println("getMatchForCase37");
			flipForCase37(pStack, match);
			return true;
		}
		
		// Two move solutions case 18
		Pair<PancakeSequence> seqPair = getMatchForCase18(pStack);
		if(seqPair != null){
//			System.out.println("getMatchForCase18");
			flipForCase18(pStack, seqPair);
			return true;
		}
		
		// Two move solutions case 26
		seqPair = getMatchForCase26(pStack);
		if(seqPair != null){
//			System.out.println("getMatchForCase26");
			flipForCase26(pStack, seqPair);
			return true;
		}
		
		// Three move solutions case 45
		seqPair = getMatchForCase45(pStack);
		if(seqPair != null){
//			System.out.println("getMatchForCase45");
			flipForCase45(pStack, seqPair);
			return true;
		}
		
		// Spot any two move solutions
/*		System.out.println("----- Looking for two move solutions v1");
		PancakeSequence topSeq = pStack.getTopSequence();
		System.out.println("Top: " + topSeq.getTopSize());
		match = pStack.getSequenceWithTopToBottomSizeDeltaOne(topSeq);

		int firstMove;
		if(match != null) {
			firstMove = topSeq.getPosition() + topSeq.getLength();
			if(firstMove != topSeq.getPosition()){
				System.out.println("Found two part move match with: " + topSeq + " and " + match);
				System.out.println("First Move: " + firstMove + ", second move will be at " + topSeq.getPosition());
				List<Integer> moves = new ArrayList<Integer>();
				moves.add(firstMove);

				flipWithRecord(pStack, firstMove);

				int secondMove = match.getPosition();
				flipWithRecord(pStack, secondMove);
				pStack.mergeSequences(topSeq, match);
				return true;
			}
		}
		
		System.out.println("----- Looking for two move solutions v2");
		for(PancakeSequence seq: pStack.getSequences()){
			
			PancakeSequence seq1, seq2;
			
			System.out.println("Top: " + seq.getTopSize());
			match = pStack.getSequenceWithTopSizeDeltaOne(seq.getTopSize());
			
			if(match != null) {
				
				if(seq.getPosition() < match.getPosition()) {
					seq1 = seq; seq2 = match;
				} else {
					seq2 = seq; seq1 = match;					
				}
				System.out.println("Found two part move match with: " + seq1 + " and " + seq2);

				firstMove = seq2.getPosition() + seq2.getLength();
				if(firstMove != seq1.getPosition()){

					flipWithRecord(pStack, firstMove);

					int secondMove = seq1.getPosition();
					flipWithRecord(pStack, secondMove);
					pStack.mergeSequences(seq1, seq2);
					return true;
				}
			}
		}
		
		System.out.println("----- Looking for two move solutions v3");
		for(PancakeSequence seq: pStack.getSequences()){
			
			PancakeSequence seq1, seq2;
			
			System.out.println("Top: " + seq.getTopSize());
			match = pStack.getSequenceWithTopToBottomSizeDeltaOne(seq);
			
			if(match != null) {
				
				if(seq.getPosition() < match.getPosition()) {
					seq1 = seq; seq2 = match;
				} else {
					seq2 = seq; seq1 = match;					
				}
				System.out.println("Found match with: " + seq1 + " and " + seq2);

				firstMove = seq1.getPosition() + seq1.getLength();
				if(firstMove != seq1.getPosition()){

					flipWithRecord(pStack, firstMove);

					int secondMove = seq2.getPosition();
					flipWithRecord(pStack, secondMove);
					pStack.mergeSequences(seq1, seq2);
					return true;
				}
			}
		}

		
		System.out.println("----- Looking for 2 flip move to base solutions");
		PancakeSequence firstSeq = pStack.getSequences().get(0);
		System.out.println("Top: " + firstSeq.getTopSize());
		match = firstSeq;
		int firstMove = firstSeq.getPosition() + firstSeq.getLength();
		int secondMove = pStack.size();
		if(firstSeq.getTopSize() == pStack.size() - 1 && firstMove != secondMove){
			System.out.println("Found 2 move to base action: " + match);
			
			flipWithRecord(pStack, firstMove);			
			flipWithRecord(pStack, secondMove);
			
			return true;
		}
		
		int firstMove, secondMove;
		// Spot three move solutions
		// Bottom to top matches		
		System.out.println("----- Looking for 3 move solutions");
		for(PancakeSequence seq: pStack.getSequences()){
			System.out.println("Bottom: " + seq.getBottomSize());
			match = pStack.getSequenceWithTopSizeDeltaOne(seq.getBottomSize());
			if(match != null && match != seq){
				
				PancakeSequence seq1, seq2;
				if(seq.getPosition() < match.getPosition()) {
					seq1 = seq; seq2 = match;
				} else {
					seq2 = seq; seq1 = match;					
				}
				
				System.out.println("Found three part move match with: " + seq1 + " and " +  seq2);
				firstMove = seq1.getPosition() + seq1.getLength();
				flipWithRecord(pStack, firstMove);
				
				secondMove = seq2.getPosition() + seq2.getLength();
				flipWithRecord(pStack, secondMove);
				
				int thirdMove = seq1.getPosition();
				flipAndMerge(pStack, thirdMove, seq1, seq2);
				return true;
			}
		}
		
		System.out.println("----- Looking for 3 flip move to base solutions");
		for(PancakeSequence seq: pStack.getSequences()){
			System.out.println("Bottom: " + seq.getBottomSize());
			match = seq;
			if(seq.getBottomSize() == pStack.size() - 1 && !seqOnBase(seq, pStack.size())){
				System.out.println("Found 3 move to base: " + match);
				firstMove = seq.getPosition() + seq.getLength();
				flipWithRecord(pStack, firstMove);
				
				secondMove = match.getLength();
				flipWithRecord(pStack, secondMove);
				
				int thirdMove = pStack.size();
				flipAndMerge(pStack, thirdMove, seq, match);
				
				return true;
			}
		}
		*/	
		return false;
	}
	
	public PancakeSequence getMatchForCaseSingleToBase(SequencedPancakeStack pStack) {
		PancakeSequence topSeq = pStack.getTopSequence();
				
		if(topSeq.getBottomSize() == pStack.size() - 1) {
			return topSeq;
		}
		return null;
	}	
	
	public PancakeSequence getMatchForCase37(SequencedPancakeStack pStack) {
		PancakeSequence topSeq = pStack.getTopSequence();
		PancakeSequence match = pStack.getSequenceWithBottomSizeDeltaOne(topSeq.getBottomSize());
		return match;
	}
	
	public Pair<PancakeSequence> getMatchForCase18(SequencedPancakeStack pStack) {
		
		for(PancakeSequence seq: pStack.getSequences()){
			PancakeSequence match = pStack.getSequenceWithTopToBottomSizeDeltaOne(seq);
			if(match != null) {
//				System.out.println("Pos: " + seq.getPosition() + " and " + match.getPosition());
				if(seq.getPosition() < match.getPosition()) {
					return new Pair<PancakeSequence>(seq, match);
				} else {
					return new Pair<PancakeSequence>(match, seq);					
				}				
			}
		}
		return null;
	}
	
	public Pair<PancakeSequence> getMatchForCase26(SequencedPancakeStack pStack) {
		
		for(PancakeSequence seq: pStack.getSequences()){
			PancakeSequence match = pStack.getSequenceWithTopToTopSizeDeltaOne(seq);
			if(match != null) {
//				System.out.println("Pos: " + seq.getPosition() + " and " + match.getPosition());
				if(seq.getPosition() < match.getPosition()) {
					return new Pair<PancakeSequence>(seq, match);
				} else {
					return new Pair<PancakeSequence>(match, seq);					
				}				
			}
		}
		return null;
	}
	
	public Pair<PancakeSequence> getMatchForCase45(SequencedPancakeStack pStack) {
		
		for(PancakeSequence seq: pStack.getSequences()){
			PancakeSequence match = pStack.getSequenceWithBottomToTopSizeDeltaOne(seq);
			if(match != null) {
//				System.out.println("Pos: " + seq.getPosition() + " and " + match.getPosition());
				if(seq.getPosition() < match.getPosition()) {
					return new Pair<PancakeSequence>(seq, match);
				} else {
					return new Pair<PancakeSequence>(match, seq);					
				}				
			}
		}
		return null;
	}
	
	public void flipForCaseSingleToBase(SequencedPancakeStack pStack) {
		flipWithRecord(pStack, pStack.size());
	}
	
	public void flipForCase37(SequencedPancakeStack pStack, PancakeSequence lowerSeq) {
		PancakeSequence topSeq = pStack.getTopSequence();

		int moveIndex = lowerSeq.getPosition();
		flipAndMerge(pStack, moveIndex, topSeq, lowerSeq);
	}

	public void flipForCase18(SequencedPancakeStack pStack, Pair<PancakeSequence> seqPair) {
		PancakeSequence upperSeq = seqPair.getOne();
		PancakeSequence lowerSeq = seqPair.getOther();
		int moveIndex = upperSeq.getPosition() + upperSeq.getLength();
		flipWithRecord(pStack, moveIndex);
		
		flipForCase37(pStack, lowerSeq);
	}
	
	public void flipForCase26(SequencedPancakeStack pStack, Pair<PancakeSequence> seqPair) {
		PancakeSequence upperSeq = seqPair.getOne();
		PancakeSequence lowerSeq = seqPair.getOther();
		int moveIndex = lowerSeq.getPosition() + lowerSeq.getLength();
		flipWithRecord(pStack, moveIndex);
		
		flipForCase37(pStack, upperSeq);
	}
	
	public void flipForCase45(SequencedPancakeStack pStack, Pair<PancakeSequence> seqPair) {
		PancakeSequence upperSeq = seqPair.getOne();
		int moveIndex = upperSeq.getPosition() + upperSeq.getLength();
		flipWithRecord(pStack, moveIndex);
		
		flipForCase26(pStack, seqPair);
	}

	public boolean seqOnBase(PancakeSequence seq, int stackSize) {
		return seq.getPosition() + seq.getLength() == stackSize;
	}
	
	public void flipAndMerge(SequencedPancakeStack pStack, int flipPosition, PancakeSequence seq, PancakeSequence match) {
		flip(pStack, flipPosition);
		pStack.mergeSequences(seq, match);
		recordMessage("No." + pStack.getFlipCount() + " " + pStack);
	}
	
	public void flipWithRecord(SequencedPancakeStack pStack, int flipPosition) {
		flip(pStack, flipPosition);
		recordMessage("No." + pStack.getFlipCount() + " " + pStack);
	}
	
	public void flip(SequencedPancakeStack pStack, int flipPosition) {		
		pStack.flip(pStack.size() - flipPosition);
		pStack.flipSequences(flipPosition);
	}
	
	public static void main(String[] args) {
		SequenceBasedFlipper flipper = new SequenceBasedFlipper();
		SequencedPancakeStack pStack = new SequencedPancakeStack(1, 3, 5, 0, 4, 6, 2);
		
		flipper.flipUntilCorrectlyStacked(pStack);
	}
}

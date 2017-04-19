package pancakeflipperrevisited;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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
			System.out.println("Completeness: " + complete);
			
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
	
	private boolean makeNextFlip(SequencedPancakeStack pStack) {
		
		System.out.println("---- makeNextFlip -----");

		// If one seq that simply needs to be flipped
		if(pStack.getSequenceCount() == 1){
			System.out.println("Just need one flip to finish");
			pStack.flip(0);
			recordMessage("No." + pStack.getFlipCount() + " " + pStack);
			System.out.println("After simple Flip: " + pStack);
			return true;
		}
		
		// Spot any single move solutions
		PancakeSequence topSeq = pStack.getTopSequence();
		System.out.println("Bottom: " + topSeq.getBottomSize());
		PancakeSequence match = pStack.getSequenceWithBottomSizeDeltaOne(topSeq.getBottomSize());
		if(match != null){
			System.out.println("Found single part move match with: " + match);

			int moveIndex = match.getPosition();
			flipAndMerge(pStack, moveIndex, topSeq, match);

			return true;
		}
		
		// Spot any two move solutions
		System.out.println("----- Looking for two move solutions");
		// TODO The sequences need to be ordered for this to work!!
		List<List<Integer>> doubleMoveFlips = new ArrayList<List<Integer>>();
		for(PancakeSequence seq: pStack.getSequences()){
			System.out.println("Top: " + seq.getTopSize());
			match = pStack.getSequenceWithTopSizeDeltaOne(seq.getTopSize());
			
			int firstMove;
			if(match != null) {
				firstMove = match.getPosition() + match.getLength();
				if(firstMove != seq.getPosition()){
					System.out.println("Found two part move match with: " + match);
					System.out.println("First Move: " + firstMove + ", second move will be at " + seq.getPosition());
					List<Integer> moves = new ArrayList<Integer>();
					moves.add(firstMove);

					doubleMoveFlips.add(moves);

					flipWithRecord(pStack, firstMove);

					int secondMove = seq.getPosition();
					flipWithRecord(pStack, secondMove);
					pStack.mergeSequences(seq, match);
					return true;
				}
			}
		}
		
		// Spot three move solutions
		// Bottom to top matches
		System.out.println("----- Looking for 3 move solutions");
		for(PancakeSequence seq: pStack.getSequences()){
			System.out.println("Bottom: " + seq.getBottomSize());
			match = pStack.getSequenceWithTopSizeDeltaOne(seq.getBottomSize());
			if(match != null && match != seq){
				System.out.println("Found three part move match with: " + match);
				int firstMove = seq.getPosition() + seq.getLength();
				flipWithRecord(pStack, firstMove);
				
				int secondMove = pStack.size();
				flipWithRecord(pStack, secondMove);
				
				int thirdMove = match.getLength();
				flipAndMerge(pStack, thirdMove, seq, match);
				return true;
			}
		}
		
		System.out.println("----- Looking for 3 flip move to base solutions");
		for(PancakeSequence seq: pStack.getSequences()){
			System.out.println("Bottom: " + seq.getBottomSize());
			match = seq;
			if(seq.getBottomSize() == pStack.size() - 1 && !seqOnBase(seq, pStack.size())){
				System.out.println("Found 3 move to base: " + match);
				int firstMove = seq.getPosition() + seq.getLength();
				flipWithRecord(pStack, firstMove);
				
				int secondMove = match.getLength();
				flipWithRecord(pStack, secondMove);
				
				int thirdMove = pStack.size();
				flipAndMerge(pStack, thirdMove, seq, match);
				
				return true;
			}
		}
		
		System.out.println("----- Looking for 2 flip move to base solutions");
		PancakeSequence seq = pStack.getSequences().get(0);
		System.out.println("Top: " + seq.getTopSize());
		match = seq;
		if(seq.getTopSize() == pStack.size() - 1){
			System.out.println("Found 2 move to base action: " + match);
			int firstMove = seq.getLength();
			flipWithRecord(pStack, firstMove);

			int secondMove = pStack.size();
			flipWithRecord(pStack, secondMove);
			
			return true;
		}
	
		return false;
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
		System.out.println("Stack before flip: " + pStack);
		pStack.flip(pStack.size() - flipPosition);
		pStack.flipSequences(flipPosition);
		System.out.println("Stack after flip: " + pStack);
	}

	public static void main(String[] args) {
		SequenceBasedFlipper flipper = new SequenceBasedFlipper();
		SequencedPancakeStack pStack = new SequencedPancakeStack(Arrays.asList(1, 0, 2, 3, 4));
		
		flipper.flipUntilCorrectlyStacked(pStack);
	}
}

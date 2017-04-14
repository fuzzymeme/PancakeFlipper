package pancakeflipperrevisited;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import static pancakeflipperrevisited.Recorder.*;

public class SequenceBasedFlipper {

//	private List<PancakeSequence> seqs = new ArrayList<PancakeSequence>();
	
	public int flipUntilCorrectlyStacked(SequencedPancakeStack pStack) {

		clearMessages();
		recordMessage("No." + pStack.getFlipCount() + " " + pStack);
		
		// Flip until correctly stacked
		boolean complete = pStack.isCorrectlyStacked();
		boolean flipSucceeded = true;
		int i = 0;
		while(!complete && flipSucceeded){	

			flipSucceeded = makeNextFlip(pStack);
			if(flipSucceeded){
//				flipCount++; // TODO Multiple flips in makeNextFlip
//				recordMessage("No." + pStack.getFlipCount() + " " + pStack);
			}

			complete = pStack.isCorrectlyStacked();
			System.out.println("Completeness: " + complete);
			
//			if(i > 3){
//				System.exit(0);
//			}
			i++;
		}

		if(complete){
			System.out.println("Complete after " + pStack.getFlipCount() + " flips");
			showMessages();
		}
		else{
			System.out.println("Flipping failed to complete");
			System.out.println("Stack: " + pStack);
			showMessages();
			System.exit(0);
		}

		return pStack.getFlipCount();
	}
	
	private boolean makeNextFlip(SequencedPancakeStack pStack) {
		
		System.out.println("---- makeNextFlip -----");
		if(pStack.isCorrectlyStacked()){
			System.out.println("Complete");
			return true;
		}

		// If one seq that simply needs to be flipped
		if(pStack.getSequenceCount() == 1){
			System.out.println("Just need one flip to finish");
//			PancakeSequence pStack = seqs.get(0);	
//			System.out.println("Before Flip: " + pStack);
			pStack.flip(0);
			recordMessage("No." + pStack.getFlipCount() + " " + pStack);
			System.out.println("After simple Flip: " + pStack);
//			System.exit(0);
			return true;
		}
		
		// Spot any one move solutions
		List<Integer> singleMoveFlips = new ArrayList<Integer>();
		PancakeSequence topSeq = pStack.getTopSequence();
//		for(PancakeSequence seq: pStack.getSequences()){ // TODO Encapsulate !!!
			System.out.println("Bottom: " + topSeq.getBottomSize());
			PancakeSequence match = pStack.getSequenceWithBottomSizeDeltaOne(topSeq.getBottomSize());
			if(match != null){
				System.out.println("Found simple move match with: " + match);
				singleMoveFlips.add(pStack.size() - match.getPosition());
				// TODO can drop out here as it can only flip once before having to recalculate
				System.out.println("Before flip: " + pStack);
				pStack.flip(pStack.size() - match.getPosition());
				recordMessage("No." + pStack.getFlipCount() + " " + pStack);
				System.out.println("After flip: " + pStack);
				pStack.flipSequences(match.getPosition());
				pStack.mergeSequences(topSeq, match);
				System.out.println("After merge: " + pStack);

				return true;
			}
//		}
		
		// Spot any two move solutions
		System.out.println("----- Looking for two move solutions");
		// TODO The sequences need to be ordered for this to work!!
//		System.exit(0);
		List<List<Integer>> doubleMoveFlips = new ArrayList<List<Integer>>();
		for(PancakeSequence seq: pStack.getSequences()){
			System.out.println("Top: " + seq.getTopSize());
			match = pStack.getSequenceWithTopSizeDeltaOne(seq.getTopSize());
			
			int firstMove = match.getPosition() + match.getLength();
			if(match != null && firstMove != seq.getPosition()){
				System.out.println("Found match with: " + FlipperUtils.toStringSequence(match, pStack));				
				System.out.println("First Move: " + firstMove + ", second move will be at " + seq.getPosition());
				List<Integer> moves = new ArrayList<Integer>();
				moves.add(firstMove);
//				moves.add(secondMove);
				doubleMoveFlips.add(moves);
				System.out.println("Stack before first flip: " + pStack);
				pStack.flip(pStack.size() - firstMove);
				pStack.flipSequences(firstMove);
				recordMessage("No." + pStack.getFlipCount() + " " + pStack);
				System.out.println("Stack after first flip: " + pStack);
//				pStack.mergeSequences(seq, match);
				int secondMove = seq.getPosition();
				System.out.println("Second Move: " + secondMove);
				pStack.flip(pStack.size() - secondMove);
				pStack.flipSequences(secondMove);
				pStack.mergeSequences(seq, match);
				recordMessage("No." + pStack.getFlipCount() + " " + pStack);
				System.out.println("Stack after second flip: " + pStack);
				return true;
			}
		}
		
		// Spot three move solutions
		// Bottom to top matches
		System.out.println("Looking for 3 move solutions");
		List<List<Integer>> tripleMoveFlips = new ArrayList<List<Integer>>();
		for(PancakeSequence seq: pStack.getSequences()){
			System.out.println("Bottom: " + seq.getBottomSize());
			match = pStack.getSequenceWithTopSizeDeltaOne(seq.getBottomSize());
			if(match != null && match != seq){
				System.out.println("Found match with: " + FlipperUtils.toStringSequence(match, pStack));
				int firstMove = seq.getPosition() + seq.getLength();
				System.out.println("Stack before first flip: " + pStack);
				pStack.flip(pStack.size() - firstMove);
				pStack.flipSequences(firstMove);
				recordMessage("No." + pStack.getFlipCount() + " " + pStack);
				System.out.println("Stack after first flip: " + pStack);
				
				int secondMove = pStack.size();
				System.out.println("Stack before second flip: " + pStack);
				pStack.flip(pStack.size() - secondMove);
				pStack.flipSequences(secondMove);
				recordMessage("No." + pStack.getFlipCount() + " " + pStack);
				System.out.println("Stack after second flip: " + pStack);
				
				int thirdMove = match.getLength();
				System.out.println("Stack before third flip: " + pStack);
				pStack.flip(pStack.size() - thirdMove);
				pStack.flipSequences(thirdMove);
				pStack.mergeSequences(seq, match);
				recordMessage("No." + pStack.getFlipCount() + " " + pStack);
				System.out.println("Stack after third flip: " + pStack);
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		SequenceBasedFlipper flipper = new SequenceBasedFlipper();
		SequencedPancakeStack pStack = new SequencedPancakeStack(Arrays.asList(0, 2, 4, 1, 3));
		
//		SequencedPancakeStack pStack = new SequencedPancakeStack(Arrays.asList(0, 1, 2, 4, 3));
//		pStack.createSequences();
//		flipper.makeNextFlip(pStack);
		flipper.flipUntilCorrectlyStacked(pStack);
	}
}

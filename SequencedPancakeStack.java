package pancakeflipperrevisited;

import java.util.ArrayList;
import java.util.List;

import pancakeflipperrevisited.PancakeSequence.DeltaSize;

public class SequencedPancakeStack extends PancakeStack{

	private List<PancakeSequence> seqs = new ArrayList<PancakeSequence>();
	
	public SequencedPancakeStack(){
		super();
	}
	
	public SequencedPancakeStack(List<Integer> initialStack){
		super(initialStack);
		createSequences();
	}
	
	private void createSequences(){

		int lastSize = -1; 
		DeltaSize dSize = DeltaSize.NA;
		PancakeSequence currentSequence = null;
		for(int i = 0; i < size(); i++){
			int size = get(i);

			if(size == lastSize + 1){
				dSize = DeltaSize.INCREASING;
			}else if(size == lastSize - 1){
				dSize = DeltaSize.DECREASING;
			}else{
				dSize = DeltaSize.NA;
			}
//			System.out.println("last: " + lastSize + ", size: " + size + ", dSize: " + dSize);
			
			if(currentSequence != null && dSize != DeltaSize.NA){
				currentSequence.setTopSize(size);
			}
			
			if(currentSequence == null && i > 0){
				currentSequence = new PancakeSequence(i);
				currentSequence.setDeltaSize(dSize);
				currentSequence.setBottomSize(lastSize);
				if(dSize != DeltaSize.NA){
					currentSequence.setTopSize(size);
				}
				else{
					currentSequence.setTopSize(lastSize);
				}
				currentSequence.setPosition(i - 1);
				seqs.add(currentSequence);
				System.out.println("Add seq." + currentSequence.getBottomSize() + " " + currentSequence.getTopSize());
			}
			
			if(dSize == DeltaSize.NA){
				if(i == size() -1){
					currentSequence = new PancakeSequence(i);
					currentSequence.setDeltaSize(dSize);
					currentSequence.setBottomSize(size);
					currentSequence.setTopSize(size);
					currentSequence.setPosition(i);
					seqs.add(currentSequence);
					System.out.println("Add seq");
				}
				currentSequence = null; // System.out.println("Nulling");
			}

			lastSize = size;
		}

//		System.out.println("Seqs: " + seqs);
	}
	
	public PancakeSequence getSequenceWithBottomSizeDeltaOne(int x){
		
		for(PancakeSequence seq: seqs){
			if(Math.abs(seq.getBottomSize() - x) == 1){
				return seq;
			}
		}
		return null;
	}

	public PancakeSequence getSequenceWithTopSizeDeltaOne(int x){
		
		for(PancakeSequence seq: seqs){
			if(Math.abs(seq.getTopSize() - x) == 1){
				return seq;
			}
		}
		return null;
	}

	public void mergeSequences(PancakeSequence one, PancakeSequence other){
		
		PancakeSequence lower = one; 
		PancakeSequence upper = other; 
		
		if(one.getPosition() > other.getPosition()){
			lower = other; 
			upper = one;
		}
		int position = lower.getPosition();
		PancakeSequence newSeq = new PancakeSequence(position);
		newSeq.setBottomSize(lower.getBottomSize());
		newSeq.setTopSize(upper.getTopSize());
		newSeq.setDeltaSize(lower.getDeltaSize());
		
		System.out.println("Merged: " + newSeq);
		seqs.remove(one);
		seqs.remove(other);
		seqs.add(newSeq);
	}

	public void flipSequences(int flipPosition){
		
		// TODO Should (??) re-order when flipped (or remove and re-insert in reverse order)
		System.out.println("Flip Pos: " + flipPosition);
		for(PancakeSequence seq: seqs){
			if(seq.getPosition() + seq.getLength() <= flipPosition){
				System.out.println("Need to flip: " + seq + " (length: " + seq.getLength() + ") topPos: " + 
					(seq.getPosition() + seq.getLength()));
//				System.out.println("Before Flip: " + seq);
				seq.flip(flipPosition);
				System.out.println("After Flip: " + seq);
			}
		}
	}
	
	@Override
	public String toString() {
		
		// TODO Should (??) print out in order of position, or better re-order when flipped
		StringBuffer buffer = new StringBuffer();
		for(PancakeSequence seq: seqs){
			buffer.append(FlipperUtils.toStringSequence(seq, this) + " ");
		}
		
		buffer.append(stack);
		return buffer.toString();
	}

	public int getSequenceCount() {
		return seqs.size();
	}

	// TODO Encap
	public List<PancakeSequence> getSequences() {
		return seqs;
	}

	public PancakeSequence getTopSequence() {
		for(PancakeSequence seq: seqs){
			if(seq.getPosition() == 0){
				return seq;
			}
		}
		return null;
	}

}

package pancakeflipperrevisited;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pancakeflipperrevisited.PancakeSequence.DeltaSize;

public class SequencedPancakeStack extends PancakeStack{

	protected List<PancakeSequence> seqs = new ArrayList<PancakeSequence>();
	
	public SequencedPancakeStack(){
		super();
	}
	
	public SequencedPancakeStack(List<Integer> initialStack){
		super(initialStack);
		createSequences();
	}
	
	public SequencedPancakeStack(Integer... initialStack){
		super(Arrays.asList(initialStack));
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
			}
			
			if(dSize == DeltaSize.NA){
				if(i == size() -1){
					currentSequence = new PancakeSequence(i);
					currentSequence.setDeltaSize(dSize);
					currentSequence.setBottomSize(size);
					currentSequence.setTopSize(size);
					currentSequence.setPosition(i);
					seqs.add(currentSequence);
				}
				currentSequence = null; // System.out.println("Nulling");
			}

			lastSize = size;
		}
	}
	
	public PancakeSequence getSequenceWithTopToBottomSizeDeltaOne(PancakeSequence seq1){
		
		int x = seq1.getTopSize();
		for(PancakeSequence seq: seqs){
			if(Math.abs(seq.getBottomSize() - x) == 1 && seq1.getPosition() < seq.getPosition() && seq != seq1){
				return seq;
			}
		}
		return null;
	}
	
	public PancakeSequence getSequenceWithTopToTopSizeDeltaOne(PancakeSequence seq1){
		
		int x = seq1.getTopSize();
		for(PancakeSequence seq: seqs){
			if(Math.abs(seq.getTopSize() - x) == 1 && seq1.getPosition() < seq.getPosition() && seq != seq1){
				return seq;
			}
		}
		return null;
	}
	
	public PancakeSequence getSequenceWithBottomToTopSizeDeltaOne(PancakeSequence seq1){
		
		int x = seq1.getBottomSize();
		for(PancakeSequence seq: seqs){
			if(Math.abs(seq.getTopSize() - x) == 1 && seq1.getPosition() < seq.getPosition() && seq != seq1){
				return seq;
			}
		}
		return null;
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
		
		seqs.remove(one);
		seqs.remove(other);
		seqs.add(newSeq);
	}

	public void flipSequences(int flipPosition){
		
		// TODO Should (??) re-order when flipped (or remove and re-insert in reverse order)
		for(PancakeSequence seq: seqs){
			if(seq.getPosition() + seq.getLength() <= flipPosition){
				seq.flip(flipPosition);
			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((seqs == null) ? 0 : seqs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SequencedPancakeStack other = (SequencedPancakeStack) obj;
		if (seqs == null) {
			if (other.seqs != null)
				return false;
		} else if (!seqs.equals(other.seqs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		// TODO Should (??) print out in order of position, or better re-order when flipped
		StringBuffer buffer = new StringBuffer();
		for(PancakeSequence seq: seqs){
			buffer.append(FlipperUtils.toStringSequence(seq, this));// + seq.getDeltaSize() + " "); // + " " + seq.getPosition());
		}
		
		buffer.append(stack);
		return buffer.toString();
	}

	public int getSequenceCount() {
		return seqs.size();
	}

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

package pancakeflipperrevisited;

public class PancakeSequence {

	private int position = -2;
	private int bottomSize = -2;
	private int topSize = -2;

	public enum DeltaSize{INCREASING, DECREASING, NA};
	private DeltaSize delta = DeltaSize.NA;

	public PancakeSequence(int position){
		this.position = position;
	}

	public PancakeSequence(int position, int bottomSize, int topSize, DeltaSize deltaSize){
		this.position = position;
		this.bottomSize = bottomSize;
		this.topSize = topSize;
		this.delta = deltaSize;
	}

	public int getLength(){
		return Math.abs(bottomSize - topSize) + 1;
	}

	public void flip(int flipPosition){

		if(delta == DeltaSize.INCREASING){
			delta = DeltaSize.DECREASING;
		}
		else if(delta == DeltaSize.DECREASING){
			delta = DeltaSize.INCREASING;
		}

		int tmp = topSize;
		setTopSize(getBottomSize());
		setBottomSize(tmp);

		setPosition(flipPosition - (getPosition() + getLength()));
	}

	public int getPosition() {
		return position;
	}

	public int getBottomSize() {
		return bottomSize;
	}

	public int getTopSize() {
		return topSize;
	}

	public DeltaSize getDeltaSize(){
		return delta;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setBottomSize(int bottomSize) {
		this.bottomSize = bottomSize;
	}

	public void setTopSize(int topSize) {
		this.topSize = topSize;
	}

	public void setDeltaSize(DeltaSize deltaSize) {
		this.delta = deltaSize;
	}

	@Override
	public String toString() {
		
		if(bottomSize > topSize) {
			delta = DeltaSize.DECREASING;
		} else if(bottomSize < topSize) {
			delta = DeltaSize.INCREASING;
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("(");
		
		if(delta == DeltaSize.DECREASING) {
			for(int i = bottomSize; i >= topSize; i--){
				buffer.append(i).append(" ");
			}
			return buffer.toString().trim() + ")";
		}
		
		if(delta == DeltaSize.INCREASING) {
			for(int i = bottomSize; i <= topSize; i++){
				buffer.append(i).append(" ");
			}
			return buffer.toString().trim() + ")";
		}
		
		return "PancakeSequence [position=" + position + ", bottomSize="
				+ bottomSize + ", topSize=" + topSize + ", deltaSize="
				+ delta + "]";
	}

}

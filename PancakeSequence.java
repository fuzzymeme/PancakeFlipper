package pancakeflipperrevisited;

public class PancakeSequence {

	private int position = -2;
	private int bottomSize = -2;
	private int topSize = -2;

	enum DeltaSize{INCREASING, DECREASING, NA};
	private DeltaSize delta = DeltaSize.NA;

	public PancakeSequence(int position){
		this.position = position;
	}
	
	public PancakeSequence(int position, int bottomSize, int topSize){
		this(position, bottomSize, topSize, DeltaSize.NA);
		
		if(bottomSize < topSize) {
			delta = DeltaSize.INCREASING;
		} else if(bottomSize > topSize){
			delta = DeltaSize.DECREASING;
		}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bottomSize;
		result = prime * result + ((delta == null) ? 0 : delta.hashCode());
		result = prime * result + position;
		result = prime * result + topSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PancakeSequence other = (PancakeSequence) obj;
		if (bottomSize != other.bottomSize)
			return false;
		if (delta != other.delta)
			return false;
		if (position != other.position)
			return false;
		if (topSize != other.topSize)
			return false;
		return true;
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
		
		if(getLength() == 1) {
			return ("(" + getTopSize() + ")");
		}
		
		return "PancakeSequence [position=" + position + ", bottomSize="
				+ bottomSize + ", topSize=" + topSize + ", deltaSize="
				+ delta + "]";
	}

}

package pancakeflipperrevisited.utils;

public class Pair<T> {

	private T one;
	private T other;

	public Pair(T one, T other) {
		this.one = one;
		this.other = other;
	}
	
	public T getOne(){
		return one;
	}
	
	public T getOther(){
		return other;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((one == null) ? 0 : one.hashCode());
		result = prime * result + ((other == null) ? 0 : other.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<T> other = (Pair<T>) obj;
		if (one == null) {
			if (other.one != null)
				return false;
		} else if (!one.equals(other.one))
			return false;
		if (this.other == null) {
			if (other.other != null)
				return false;
		} else if (!this.other.equals(other.other))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pair [one:" + one + ", other:" + other + "]";
	}

}

package labCompas.logic;

public class Side {
	private double minDegree;
	private double maxDegree;
	public Side() {
		super();
	}
	public Side(double minDegree, double maxDegree) {
		super();
		this.minDegree = minDegree;
		this.maxDegree = maxDegree;
	}
	public double getMinDegree() {
		return minDegree;
	}
	public void setMinDegree(double minDegree) {
		this.minDegree = minDegree;
	}
	public double getMaxDegree() {
		return maxDegree;
	}
	public void setMaxDegree(double maxDegree) {
		this.maxDegree = maxDegree;
	}
}

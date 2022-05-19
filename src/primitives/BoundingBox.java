package primitives;

public class BoundingBox {
	private double minX;
	private double minY;
	private double minZ;
	private double maxX;
	private double maxY;
	private double maxZ;
	
	public BoundingBox(double minX,double minZ,double minY,double maxX,double maxY,double maxZ) {
		this.minX =  minX;
		this.minY =  minY;
		this.minZ =  minZ;
		this.minX =  maxX;
		this.minY=  maxY;
		this.minZ =  maxZ;
	
	}
}

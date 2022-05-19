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
	
	public boolean intersecte(Ray ray) {
       Point head = ray.getP0();
       double dirfra_x =  1.0f / ray.getDir().getX();
       double dirfra_y =  1.0f / ray.getDir().getY();
       double dirfra_z =  1.0f / ray.getDir().getZ();
       double t1 = (minX - head.getX()) * dirfra_x;
       double t2 = (maxX - head.getX()) * dirfra_x;
       double t3 = (minY - head.getY()) * dirfra_y;
       double t4 = (maxY - head.getY()) * dirfra_y;
       double t5 = (minZ - head.getZ()) * dirfra_z;
       double t6 = (maxZ - head.getZ()) * dirfra_z;


       double tmin = Math.max(Math.max(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
       double tmax = Math.min(Math.min(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));

       // if tmax < 0, ray (line) is intersecting AABB, but the whole AABB is behind us
       if (tmax < 0)
       {
           return false;
       }
       // if tmin > tmax, ray doesn't intersect AABB
       if (tmin > tmax)
       {
           return false;
       }
       return true;
       
	}
}

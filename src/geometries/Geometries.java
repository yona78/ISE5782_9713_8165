package geometries;

import primitives.BoundingBox;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.*;

/**
 * This class will use as a composite of other geometries objects.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Geometries extends Geometry {

	private List<Geometry> geometries = new LinkedList<>();

	/**
	 * Constructor to initialize Geometries empty list of geometries
	 * 
	 */
	public Geometries() {
	}

	/**
	 * Constructor to initialize Geometries with few geometries
	 * 
	 * @param geometries few geometries to add to the list of the object
	 */
	public Geometries(Geometry... geometries) {
		add(geometries);
	}

	/**
	 * Add to the list of the object more geometries
	 * 
	 * @param geometriesAdd - the objects to add to the list
	 */
	public void add(Geometry... geometriesAdd) {
		geometries.addAll(List.of(geometriesAdd));
	}
	
	public List<Geometry> getGeometrie() {
		return this.geometries;
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		List<GeoPoint> lst1 = null;
		for (Geometry geometry : geometries) {
			List<GeoPoint> tmp = geometry.findGeoIntersections(ray, maxDistance);
			if (tmp != null) {
				if (lst1 == null)
					lst1 = new LinkedList<>();
				lst1.addAll(tmp);
			}
		}
		return lst1;
	}

	@Override
	public Vector getNormal(Point point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void calculateBX() {
		BoundingBox boundingBox = geometries.get(0).getBoundingBox();
        for (int i =0; i<geometries.size();i++ ) {
            boundingBox = boundingBox.union(geometries.get(i).getBoundingBox());
        }
    }
	
	 public Geometries buildHierarchy(int sizeOfNode) {
	    	if(geometries.size()<= sizeOfNode ) {
	    		return new Geometries(geometries.toArray(new Geometry[0]));
	    	}
	    	Geometries leftNode = new Geometries();
	        Geometries rightNode = new Geometries();
	        Point calAxis = bx.getMaxPoint().subtract(bx.getMinPoint());
	        Vector axis;
	        double x = calAxis.getX();
	        double y = calAxis.getY();
	        double z = calAxis.getZ();
	        if(x> y && x>z) {
	        	axis = new Vector(1,0,0);
	        }
	        else if(y> x && y>z) {
	        	axis = new Vector(0,1,0);
	        }
	        else {
	        	axis = new Vector(0,0,1);
	        }
	        Point center = bx.getCenterPoint();
	        for (Geometry geo : geometries) {
	        	geo.calculateBX();
	            double differenceOnAxis = 0;
	            Point geoBxCenter = geo.getBoundingBox().getCenterPoint();
	            if (!geoBxCenter.equals(center)) {
	                differenceOnAxis = geoBxCenter.subtract(center).dotProduct(axis);
	            }
	            if (differenceOnAxis < 0) {
	                leftNode.add(geo);
	            }
	            else {
	                rightNode.add(geo);
	            }
	        }

	        // recursive calls
	        leftNode = leftNode.buildHierarchy(sizeOfNode);
	        rightNode = rightNode.buildHierarchy(sizeOfNode);
	        return new Geometries(leftNode, rightNode);
	 }

	public void add(Intersectable intersectableAdd, Geometry... geometriesAdd) {
		geometries.add((Geometry) List.of(intersectableAdd));
		geometries.addAll(List.of(geometriesAdd));
		
	}
	        


}


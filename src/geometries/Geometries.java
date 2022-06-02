package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

/**
 * This class will use as a composite of other geometries objects.
 * 
 * @author Hillel Kroitoro, Yona Orunov
 */

public class Geometries extends Intersectable {

	private List<Intersectable> geometries = new LinkedList<>();

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
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * Add to the list of the object more geometries
	 * 
	 * @param geometriesAdd - the objects to add to the list
	 */
	public void add(Intersectable... geometriesAdd) {
		geometries.addAll(List.of(geometriesAdd));
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		List<GeoPoint> lst1 = null;
		for (Intersectable geometry : geometries) {
			List<GeoPoint> tmp = geometry.findGeoIntersections(ray, maxDistance);
			if (tmp != null) {
				if (lst1 == null)
					lst1 = new LinkedList<>();
				lst1.addAll(tmp);
			}
		}
		return lst1;
	}
	

    public void createBoundingBox() {
        for (Intersectable geo : geometries) {
            geo.createBoundingBox();
        }
        boundingBox = geometries.get(0).boundingBox;
        for (Intersectable geo : geometries.subList(1, geometries.size())) {
            boundingBox = boundingBox.union(geo.boundingBox);
        }
    }
    
    /**
    * Re-arranges the geometries in a tree way that would be performant for bvh rendering.
    *
    * @param maxGeometriesInNode the maximum amount of geometries to be in each node of the new tree.
    * @return the new arranged geometry.
    */
   public Geometries buliedTree(int maxGeometriesInNode) {
       if (maxGeometriesInNode < 3) {
           throw new IllegalArgumentException("ERROR: there must be at least two geometries in each node!");
       }

       // if amount of geometries is enough, this is a leaf node
       if (geometries.size() <= maxGeometriesInNode || boundingBox == null) {
           return new Geometries(geometries.toArray(new Geometry[0]));
       }
       // else... split
       Geometries left = new Geometries();
       Geometries right = new Geometries();

       // find longest side of bb to divide by
       Vector help = boundingBox.getMaxPoint().subtract(boundingBox.getMinPoint());
       Point dimensions = new Point(help.getX(),help.getY(),help.getZ());
       Vector dir;
       if (dimensions.getX() > dimensions.getY() && dimensions.getX() > dimensions.getZ()) {
           dir = new Vector(1, 0, 0);
       }
       else if (dimensions.getY() > dimensions.getX() && dimensions.getY() > dimensions.getZ()) {
           dir = new Vector(0, 1, 0);
       }
       else {
           dir = new Vector(0, 0, 1);
       }

       // center point of bb
       Point center = boundingBox.getMiddlePoint();

       // sort the geometries into the two new tree-nodes
       for (Intersectable geo : geometries) {
           // the differences between the bbs' locations on the longest axis.
           double differenceOnAxis = 0;
           Point geoBbCenter = geo.boundingBox.getMiddlePoint();
           if (!geoBbCenter.equals(center)) {
               differenceOnAxis = geoBbCenter.subtract(center).dotProduct(dir);
           }
           if (differenceOnAxis < 0) {
               left.add(geo);
           }
           else {
               right.add(geo);
           }
       }

       // recursive calls
       left = left.buliedTree(maxGeometriesInNode);
       right = right.buliedTree(maxGeometriesInNode);
       return new Geometries(left, right);
   }
   
   public List<Intersectable> getGeometries() {
	   return geometries;
   }

}


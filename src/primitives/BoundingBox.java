package primitives;

public class BoundingBox {
	
	private double minX, minY, minZ;


    /**
     * The maximum values of the box on the axis
     */
    private double maxX, maxY, maxZ;
    
    public BoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;

        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }
    

    /**
     * @return The value of minimum X.
     */
    public double getMinX() {
        return minX;
    }

    /**
     * @return The value of minimum Y.
     */
    public double getMinY() {
        return minY;
    }

    /**
     * @return The value of minimum Z.
     */
    public double getMinZ() {
        return minZ;
    }

    /**
     * @return The value of maximum X.
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * @return The value of maximum Y.
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * @return The value of maximum Z.
     */
    public double getMaxZ() {
        return maxZ;
    }
    
    public Point getMaxPoint() {
    	return new Point(maxX,maxY,maxZ);
    }
    
    public Point getMinPoint() {
    	return new Point(minX,minY,minZ);
    }
    
    public Point getMiddlePoint() {
    	   double x = 0, y = 0, z = 0;
           if (maxX != Double.POSITIVE_INFINITY)
           {
               x = Util.alignZero((maxX + minX) / 2);
           }
           if (maxY != Double.POSITIVE_INFINITY)
           {
               y = Util.alignZero((maxY +minY) / 2);
           }
           if (maxZ != Double.POSITIVE_INFINITY)
           {
               z = Util.alignZero((maxZ + minZ) / 2);
           }
           return new Point(x, y, z);
    }
    
    /**
     * @param ray         the ray to test.
     * @param maxDistance the maximum distance to test for
     * @return whether the ray intersects the bounding box or not, within the given max distance.
     */
    private boolean isIntersectedByRay(Ray ray, double maxDistance) {
    	Vector dir = ray.getDir();
        Point dirHead = new Point(dir.getX(),dir.getY(),dir.getZ());
        Point origin = ray.getP0();
        double minT = 0, maxT = maxDistance; // t has to be bigger than zero and smaller or equal to the max distance allowed

        // find T range so point intersects on X
        if (dirHead.getX() == 0)
        {
            if (minX <= origin.getX() || origin.getX() >= maxX)
            { // point never intersect
                return false;
            }
        }
        else
        {
            double val1 = Util.alignZero((minX - origin.getX()) / dirHead.getX());
            double val2 = Util.alignZero((maxX - origin.getX()) / dirHead.getX());

            if (dirHead.getX() < 0)
            {
                minT = Math.max(minT, val2);
                maxT = Math.min(maxT, val1);
            }
            else
            {
                minT = Math.max(minT, val1);
                maxT = Math.min(maxT, val2);
            }

            if (maxT < minT || maxT == 0)
            { //if max is smaller then min, or max = 0, then T doesn't exist. no intersection
                return false;
            }
        }
        // find T range so point intersects on Y
        if (dirHead.getY() == 0)
        {
            if (minY <= origin.getY() || origin.getY() >= maxY)
            { // point never intersect
                return false;
            }
        }
        else
        {
            double val1 = Util.alignZero((minY - origin.getY()) / dirHead.getY());
            double val2 = Util.alignZero((maxY - origin.getY()) / dirHead.getY());

            if (dirHead.getY() < 0)
            {
                minT = Math.max(minT, val2);
                maxT = Math.min(maxT, val1);
            }
            else
            {
                minT = Math.max(minT, val1);
                maxT = Math.min(maxT, val2);
            }

            if (maxT < minT || maxT == 0)
            { //if max is smaller then min, or max = 0, then T doesn't exist. no intersection
                return false;
            }
        }
        // find T range so point intersects on Z
        if (dirHead.getZ() == 0)
        {
            // return true if point intersects on z, false if not
            return minZ > origin.getZ() && origin.getZ() < maxZ;
        }
        else
        {
            double val1 = Util.alignZero((minZ - origin.getZ()) / dirHead.getZ());
            double val2 = Util.alignZero((maxZ - origin.getZ()) / dirHead.getZ());

            if (dirHead.getZ() < 0)
            {
                minT = Math.max(minT, val2);
                maxT = Math.min(maxT, val1);
            }
            else
            {
                minT = Math.max(minT, val1);
                maxT = Math.min(maxT, val2);
            }
            //T exists only if max is bigger than or equal to min, and max isn't 0!
            return maxT >= minT && maxT != 0;
        }
    }

    /**
     * @param ray the ray to test.
     * @return whether the ray intersects the bounding box or not.
     */
    public boolean isIntersectedByRay(Ray ray) {
        return isIntersectedByRay(ray, Double.POSITIVE_INFINITY);
    }
    
    /**
     * unites two bounding boxes into one big box
     * @param other the other bounding box
     * @return bounding box with two bounding boxes inside him
     */
    public BoundingBox union(BoundingBox other) {
        return new BoundingBox(Math.min(minX, other.minX),
        		Math.min(minY, other.minY),Math.min(minZ, other.minZ),
        		Math.max(maxX, other.maxX), Math.max(maxY, other.maxY),
        		Math.max(maxZ, other.maxZ));
    }
}
package ass2.spec;

import java.util.ArrayList;
import java.util.List;

/**
 * COMMENT: Comment Road 
 *
 * @author malcolmr
 */
public class Road {

    private List<Double> myPoints;
    private double myWidth;
    
    /** 
     * Create a new road starting at the specified point
     */
    public Road(double width, double x0, double y0) {
        myWidth = width;
        myPoints = new ArrayList<Double>();
        myPoints.add(x0);
        myPoints.add(y0);
    }

    /**
     * Create a new road with the specified spine 
     *
     * @param width
     * @param spine
     */
    public Road(double width, double[] spine) {
        myWidth = width;
        myPoints = new ArrayList<Double>();
        for (int i = 0; i < spine.length; i++) {
            myPoints.add(spine[i]);
        }
    }

    /**
     * The width of the road.
     * 
     * @return
     */
    public double width() {
        return myWidth;
    }

    /**
     * Add a new segment of road, beginning at the last point added and ending at (x3, y3).
     * (x1, y1) and (x2, y2) are interpolated as bezier control points.
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     */
    public void addSegment(double x1, double y1, double x2, double y2, double x3, double y3) {
        myPoints.add(x1);
        myPoints.add(y1);
        myPoints.add(x2);
        myPoints.add(y2);
        myPoints.add(x3);
        myPoints.add(y3);        
    }
    
    /**
     * Get the number of segments in the curve
     * 
     * @return
     */
    public int size() {
        return myPoints.size() / 6;
    }

    /**
     * Get the specified control point.
     * 
     * @param i
     * @return
     */
    public double[] controlPoint(int i) {
        double[] p = new double[2];
        p[0] = myPoints.get(i*2);
        p[1] = myPoints.get(i*2+1);
        return p;
    }
    
    /**
     * Get a point on the spine. The parameter t may vary from 0 to size().
     * Points on the kth segment take have parameters in the range (k, k+1).
     * 
     * @param t
     * @return
     */
    public double[] point(double t) {
        int i = (int)Math.floor(t);
        t = t - i;
        
        i *= 6;
        
        double x0 = myPoints.get(i++);
        double y0 = myPoints.get(i++);
        double x1 = myPoints.get(i++);
        double y1 = myPoints.get(i++);
        double x2 = myPoints.get(i++);
        double y2 = myPoints.get(i++);
        double x3 = myPoints.get(i++);
        double y3 = myPoints.get(i++);
        
        double[] p = new double[2];

        p[0] = b(0, t) * x0 + b(1, t) * x1 + b(2, t) * x2 + b(3, t) * x3;
        p[1] = b(0, t) * y0 + b(1, t) * y1 + b(2, t) * y2 + b(3, t) * y3;        
        
        return p;
    }
    
    /**
     * Calculate the Bezier coefficients
     * 
     * @param i
     * @param t
     * @return
     */
    private double b(int i, double t) {
        
        switch(i) {
        
        case 0:
            return (1-t) * (1-t) * (1-t);

        case 1:
            return 3 * (1-t) * (1-t) * t;
            
        case 2:
            return 3 * (1-t) * t * t;

        case 3:
            return t * t * t;
        }
        
        // this should never happen
        throw new IllegalArgumentException("" + i);
    }
    
//    private void drawRoad() {
//    	// Enable vertex arrays
//    	glEnableClientState(GL_VERTEX_ARRAY);
//    	
//    	// Create array of vertices, consisting of 3 points:
//    	vertex *vertices = new vertex[3];
//
//    	// Assign some values to all 3 points
//    	vertices[0].x = 10.0f;
//    	vertices[0].y = 5.0f;
//    	vertices[0].z = 7.0f;
//
//    	// Vertex 2
//    	vertices[1].x = -10.0f;
//    	vertices[1].y = 3.0f;
//    	vertices[1].z = 1.0f;
//
//    	// Vertex 3
//    	vertices[2].x = 5.0f;
//    	vertices[2].y = -5.0f;
//    	vertices[2].z = 2.0f;
//    	
//    	glVertexPointer( 3, // number of coordinates per vertex (x,y,z)
//    		    GL_FLOAT,       // they are floats
//    		    sizeof(vertex), // stride
//    		    vertices);      // the array pointer
//    	
    	
    	
    
    
    
    
 // Render primitives from array-based data
//    int num_indices = 3;
//    glDrawArrays(GL_TRIANGLES, 0, num_indices);// Render primitives from array-based data
//    int num_indices = 3;
//    glDrawArrays(GL_TRIANGLES, 0, num_indices);
//    
//    glPushMatrix();
//
//    // Bind VBO for drawing array data
//    glBindBufferARB(GL_ARRAY_BUFFER_ARB, m_vboId);
//
//    // Activate array-based data
//    glEnableClientState(GL_VERTEX_ARRAY);
//
//    // Stride of 3, floats
//    glVertexPointer(3, GL_FLOAT, 0, 0);
//
//    // Draw triangles
//    glDrawArrays(GL_TRIANGLES, 0, 18);
//
//    // Switch off vertex array data
//    glDisableClientState(GL_VERTEX_ARRAY);
//
//    // Bind with 0 to switch back to default pointer operation
//    glBindBufferARB(GL_ARRAY_BUFFER_ARB, NO_VBO_ID);
//
//    glPopMatrix();
//    
//    
//    
//
//
}

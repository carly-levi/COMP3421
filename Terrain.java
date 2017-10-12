package ass2.spec;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;



/**
 * COMMENT: Comment HeightMap 
 *
 * @author malcolmr
 */
public class Terrain {

    private Dimension mySize;
    public double[][] myAltitude;
    private List<Tree> myTrees;
    private List<Road> myRoads;
    private float[] mySunlight;

    /**
     * Create a new terrain
     *
     * @param width The number of vertices in the x-direction
     * @param depth The number of vertices in the z-direction
     */
    public Terrain(int width, int depth) {
        mySize = new Dimension(width, depth);
        myAltitude = new double[width][depth];
        myTrees = new ArrayList<Tree>();
        myRoads = new ArrayList<Road>();
        mySunlight = new float[3];
    }
    
    public Terrain(Dimension size) {
        this(size.width, size.height);
    }

    public Dimension size() {
        return mySize;
    }

    public List<Tree> trees() {
        return myTrees;
    }

    public List<Road> roads() {
        return myRoads;
    }

    public float[] getSunlight() {
        return mySunlight;
    }

    /**
     * Set the sunlight direction. 
     * 
     * Note: the sun should be treated as a directional light, without a position
     * 
     * @param dx
     * @param dy
     * @param dz
     */
    public void setSunlightDir(float dx, float dy, float dz) {
        mySunlight[0] = dx;
        mySunlight[1] = dy;
        mySunlight[2] = dz;        
    }
    
    /**
     * Resize the terrain, copying any old altitudes. 
     * 
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        mySize = new Dimension(width, height);
        double[][] oldAlt = myAltitude;
        myAltitude = new double[width][height];
        
        for (int i = 0; i < width && i < oldAlt.length; i++) {
            for (int j = 0; j < height && j < oldAlt[i].length; j++) {
                myAltitude[i][j] = oldAlt[i][j];
            }
        }
    }

    /**
     * Get the altitude at a grid point
     * 
     * @param x
     * @param z
     * @return
     */
    public double getGridAltitude(int x, int z) {
        return myAltitude[x][z];
    }

    /**
     * Set the altitude at a grid point
     * 
     * @param x
     * @param z
     * @return
     */
    public void setGridAltitude(int x, int z, double h) {
        myAltitude[x][z] = h;
    }
    
    public double interpolateX(double x, double z){
    	double R1 = getGridAltitude((int)x-1, (int)z);
    	double R2 = getGridAltitude((int)x+1, (int)z);
    	double func1 = ((x-(x-1))/((x+1)-(x-1))*R1);
    	double func2 = (((x+1) - x)/((x+1) - (x-1)))*R2;
    	double altitude = func1+func2;
		return altitude;
    }
    
    public double interpolateY(double x, double z){
    	double R1 = getGridAltitude((int)x, (int)z-1);
    	double R2 = getGridAltitude((int)x, (int)z+1);
    	double func1 = ((z-(z-1))/((z+1)-(z-1))*R1);
    	double func2 = (((z+1) - z)/((z+1) - (z-1)))*R2;
    	double altitude = func1+func2;
    	return altitude;
    }

    /**
     * Get the altitude at an arbitrary point. 
     * Non-integer points should be interpolated from neighbouring grid points
     * 
     * TO BE COMPLETED
     * 
     * @param x
     * @param z
     * @return
     */
    public double altitude(double x, double z) {
        double altitude = 0;
        if(x != (int)x || z != (int)z){
        	altitude = interpolateX(x,z);       
        }
        else{
        	altitude = getGridAltitude((int)x, (int)z);
        }
        
        return altitude;
    }

    /**
     * Add a tree at the specified (x,z) point. 
     * The tree's y coordinate is calculated from the altitude of the terrain at that point.
     * 
     * @param x
     * @param z
     */
    public void addTree(double x, double z) {
        double y = altitude(x, z);
        Tree tree = new Tree(x, y, z);
        myTrees.add(tree);
    }


    /**
     * Add a road. 
     * 
     * @param x
     * @param z
     */
    public void addRoad(double width, double[] spine) {
        Road road = new Road(width, spine);
        myRoads.add(road);        
    }
    
	public static void Draw(GL2 gl, Terrain myTerrain){
		//GL2 gl = gl.getGL().getGL2();
		int size = myTerrain.myAltitude[0].length;
		double data[][] = new double[size][size]; /* array of data heights */
    	
		gl.glBegin(GL2.GL_TRIANGLES);

    	for(int i=0;i<size;i++){
    		for(int j=0;j<size;j++){
    			data[i][j]= myTerrain.myAltitude[i][j];}}
    	
        for(int i=0; i<size; i++){
        	for(int j=0; j<size;j++){
        	gl.glVertex4d((float)i/size, data[i][j], (float)j/size, 1.0);
            gl.glVertex4d((float)i/size, data[i][j], (float)(j+1)/size, 1.0);
            gl.glVertex4d((float)(i+1)/size, data[i][j], (float)(j+1)/size, 1.0); 
            gl.glVertex4d((float)(i+1)/size, data[i][j], (float)(j+1)/size, 1.0); 
            gl.glVertex4d((float)(i+1)/size, data[i][j], (float)j/size, 1.0); 
            gl.glVertex4d((float)i/size, data[i][j], (float)j/size, 1.0); 
        	}
        }
        gl.glEnd();
	}
}

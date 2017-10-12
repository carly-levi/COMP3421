package ass2.spec;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;

import javax.swing.JFrame;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;



/**
 * COMMENT: Comment Game 
 *
 * @author malcolmr
 */
public class Game extends JFrame implements GLEventListener, KeyListener{

    private Terrain myTerrain;

    public Game(Terrain terrain) {
    	super("Assignment 2");
        myTerrain = terrain;
   
    }
    
    /** 
     * Run the game.
     *
     */
    public void run() {
    	  GLProfile glp = GLProfile.getDefault();
          GLCapabilities caps = new GLCapabilities(glp);
          GLJPanel panel = new GLJPanel();
          panel.addGLEventListener(this);
 
          // Add an animator to call 'display' at 60fps        
          FPSAnimator animator = new FPSAnimator(60);
          animator.add(panel);
          animator.start();

          getContentPane().add(panel);
          setSize(800, 600);        
          setVisible(true);
          setDefaultCloseOperation(EXIT_ON_CLOSE);        
    }
    
    /**
     * Load a level file and display it.
     * 
     * @param args - The first argument is a level file in JSON format
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Terrain terrain = LevelIO.load(new File(args[0]));
        Game game = new Game(terrain);
        game.run();
    }


	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    	gl.glColor4d(1,0,0,1);
    	
		/*camera*/
		//glu.gluLookAt(eyeX, eyeY, eyeZ,centerX, centerY, centerZ, upX, upY, upZ)
		
		Terrain.Draw(gl, myTerrain);
	

		
		/*trees*/
	
		
		
		/*set materials for each object*/
		
	}
    
	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
    	gl.glEnable(GL2.GL_DEPTH_TEST); 
		
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); 
        double aspect;
        double top, bottom, left, right;
        
        if (width > height) {
            aspect = (1.0 * width) / height;
            top = 1.0;
            bottom = -1.0;
            left = -aspect;
            right = aspect;            
        }
        else {
            aspect = (1.0 * height) / width;
            top = aspect;
            bottom = -aspect;
            left = -1;
            right = 1;                        
        }        
        GLU myGLU = new GLU();
        double near = 0;
		double far = 10;
		// coordinate system (left, right, bottom, top)
        //myGLU.gluPerspective(180, aspect, near, far);               
    }
		
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
								  
		 case KeyEvent.VK_DOWN:
		       
			  //move backward
			// gl.glTranslated();
			  break;
	     case KeyEvent.VK_UP:
		     
			  //move forward
	    	 //gl.glTranslated();
			  break;				  			  
		case KeyEvent.VK_RIGHT:
			       
				  //turn right rotate right
			//gl.glRotated();
				  break;
		 case KeyEvent.VK_LEFT:
			     
		    	//turn left rotate left
			 //gl.glRotated();
				  break;	
		
		 default:
			 break;
		 }		
	}
	
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(com.jogamp.newt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(com.jogamp.newt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    

}

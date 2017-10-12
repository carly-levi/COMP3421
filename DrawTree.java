package ass2.spec;

public class DrawTree implements GLEventListener{
	public void cylinder(GL2 gl, GLU glu) {
    	glu.gluCylinder(quadric, 2, 1, 3, 32, 32);
    	gl.glRotated(-90, 0, 0, 1);	
	}
	public void sphere(GL2 gl, GLU glu){
		glu.gluSphere(quadric, 2, 32, 32);		
	}
	
	public void DrawTree(GL2 gl, GLU glu){
		//trunk
		gl.glPushMatrix();
			glu.gluCylinder(gl, glu);
		gl.glPopMatrix();
		
		//leaves
		gl.glPushMatrix();
			gl.glTranslated(1, 0, 1, 0);
			gl.gluSphere(gl, glu);
		gl.glPopMatrix();
	}}
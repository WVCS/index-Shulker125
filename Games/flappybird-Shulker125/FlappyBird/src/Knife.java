import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform; 
import java.net.URL;
import java.util.Random;
//access key: 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
public class Knife{
	//add location attributes
	public static int x, vx; //position of the knife and velocity
	private Image img;
	private AffineTransform tx;
	Random rnd = new Random (300);
	public int y = rnd.nextInt((300 - 160)+1)+160;
	Music musicDeath = new Music("dead.wav", false);
	public Knife(int x, int vx) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		img = getImage("/imgs/knife.png"); //load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	

	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	} 
	
	public void paint(Graphics g, int vx) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		x -= vx;
		//call update to update the actual picture location
		update();
		g2.drawImage(img, tx, null);
		if (x < -800) {
			x = 400;
			y = rnd.nextInt((300 - 160)+1)+160;
		}

	}
	/* update the picture variable location */
	private void update() {
		tx.setToTranslation(x, y);
		// set scale
	}
	public static int knifeX() {
		return x;
	}
	public int knifeY() {
		return y;
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Knife.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

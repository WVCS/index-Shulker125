import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform; 
import java.net.URL;
//access key: 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
public  class Bird{
	//add location attributes
	public static int x, y; //position of the bird
	private Image img; 	
	private int vy = 0; //velocity in the y
	private int ay = 0; // acceleration is down (increasing y)
	private AffineTransform tx;
	public boolean run = true;
	public static boolean firstRun = true;
	public Bird(int x, int y) {
		this.x = x; 
		this.y = y;
		img = getImage("/imgs/amogus.png"); //load the image for Tree

		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}  
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	} 
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actual picture location
		update();
		g2.drawImage(img, tx, null);
		

	}
	public void flap() {
		img = getImage("/imgs/amogusjet.png");
		vy -= 20;
	}
	public void change() {
		img = getImage("/imgs/amogus.png");
	}
	
	/* update the picture variable location */
	private void update() {
		y += vy;
		vy += ay;
		tx.setToTranslation(x, y);
		tx.scale(.1	, .1);
		if (vy > 25) {
			vy = 25;
		}
		else if (vy < -25) {
			vy = -25;
		}
		if (y <= 10) {
			y = 10;
			ay = 5;
		}
		else if (y >= 450) {
			y = 450;
		}
		if (y > 15 && !firstRun) {
			ay = 2;
		}
		if (firstRun) {
			ay = 0;
			vy = 0;
		}
		// set scale
		tx.scale(40, 40);
		
	}
	public static int birdX() {
		return x;
	}
	public static int birdY() {
		return y;
	}
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.5, .5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Bird.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

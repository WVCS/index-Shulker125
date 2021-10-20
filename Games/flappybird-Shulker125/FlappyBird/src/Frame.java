import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.Timer;
//access key: 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
public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	public Image img = Background.getImage("/imgs/back.gif"); //setting main background
	public AffineTransform tx; //setting up scale system
	Music musicSpawn = new Music("flap.wav", false); //sound when clicking
	Music death = new Music("dead.wav", false); //death sound when hitting the knife
	Music musicDeath = new Music("dead1.wav", false); //death sound when hitting the ground
	Music clear = new Music("amogus.wav", false);
	Bird bird = new Bird(20, 225); //bird object
	public int vx = 5; //knife velocity
	public int score = 0;
	Knife knife = new Knife(400, vx);
	Knife2 knife2 = new Knife2(800, vx);
	Knife3 knife3 = new Knife3(1200, vx);
	Color newcolor = new Color(255, 255, 255);
	//knife object 
	public static int gameTrue = 0; //game counter for death
	public static boolean run = false, knifeRun = false; //run status
	private double scale = 0.33; //background image scaling
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(newcolor);
		Background b = new Background(0, 0, img, scale);
		if (bird.birdX()+60 >= knife.knifeX() && bird.birdX()+60 <= knife.knifeX()+80 && bird.birdY()+80 >= knife.knifeY()) {
			if (run) {
				death.play();
				run = false;
				knifeRun = true;
			}
		}
		if (bird.birdX()+60 >= knife2.knifeX() && bird.birdX()+60 <= knife2.knifeX()+80 && bird.birdY()+80 <= knife2.knifeY()+440) {
			if (run) {
				death.play();
				run = false;
				knifeRun = true;
			}
		}
		if (bird.birdX()+60 >= knife3.knifeX() && bird.birdX()+60 <= knife3.knifeX()+80 && bird.birdY()+80 >= knife3.knifeY()) {
			if (run) {
				death.play();
				run = false;
				knifeRun = true;
			}
		}
		if (bird.birdX() +50 >= knife.knifeX()+60 && bird.birdX() +50 <= knife.knifeX()+61) {
			score++;
			clear.play();
		}
		if (bird.birdX() +50 >= knife2.knifeX()+60 && bird.birdX() +50 <= knife2.knifeX()+61) {
			score++;
			clear.play();
		}
		if (bird.birdX() +50 >= knife3.knifeX()+60 && bird.birdX() +50 <= knife3.knifeX()+61) {
			score++;
			clear.play();
		}
		if (bird.birdY() > 449 && !knifeRun) {
			while (gameTrue < 1) {
				musicDeath.play();
				run = false;
				gameTrue++;
			}
		}
		if (run) {
			vx = 5;
			scale = 0.33;
			img = Background.getImage("/imgs/back.gif");
		}
		
		
		//ask the objects to paint themselves
		
		b.paint(g);
		knife.paint(g, vx);
		knife2.paint(g, vx);
		knife3.paint(g, vx);
		bird.paint(g);
		if (Bird.firstRun) {
			score = 0;
			gameTrue = 0;
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("Press Space to Start", 73, 100);
		}
		if (!Bird.firstRun) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString(score + "", 175, 100);
		}
		if (!run) {
			vx = 0;
			scale = 1;
			img = Background.getImage("/imgs/backstill.png");
			if (!Bird.firstRun) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
				g.drawString("Left Click to Respawn", 65, 150);
			}
		}
	}
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	public Frame() {
		JFrame f = new JFrame("Flappy Bird");
		f.setSize(new Dimension(400, 600));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (!run && arg0.getButton() == 1) {
			Bird.firstRun = true;
			Bird.x = 20;
			Bird.y = 225;
			Knife.x = 400;
			Knife2.x = 800;
			Knife3.x = 1200;
			knifeRun = false;
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 32 && run) {
			bird.flap();
			musicSpawn.play();
		}
		if (arg0.getKeyCode() == 32 && Bird.firstRun) {
			run = true;
			Bird.firstRun = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 32) {
			bird.change();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

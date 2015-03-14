import java.applet.Applet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Random;

public class StartingPoint extends Applet implements Runnable, KeyListener,
		MouseMotionListener, MouseListener {

	
	private static final long serialVersionUID = 1L;
	int test = 0;
	Ball b;
	Ball b2;
	private Image i;
	private Graphics gang;
	Platform p[] = new Platform[7];
	Item ito[] = new Item[3];
	private int score;
	double cityX = 0;
	double cityDx = .3;
	int levelcheck = 0;
	boolean mousein = false;
	boolean gameOver = false;
	FireItems fr = new FireItems();
	ReadFile rf = new ReadFile();

	URL url;
	Image city;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void init() {
		setSize(800, 600);
		setBackground(Color.DARK_GRAY);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		try {
			url = getDocumentBase();
		} catch (Exception e) {

		}

		city = getImage(url, "Images/dog.png");
		Pictures P = new Pictures(this);

	}

	@Override
	public void start() {
		Pictures.back.loop();
		score = 0;
		b = new Ball();
		for (int i = 0; i < p.length; i++) {
			Random r = new Random();

			p[i] = new Platform(i * 120, 300);
		}

		for (int i = 0; i < ito.length; i++) {
			Random r = new Random();
			switch (r.nextInt(5)) {
			case 0:
				ito[i] = new GravUp(getWidth() + 2000 * i);
				break;
			case 1:
				ito[i] = new GravDown(getWidth() + 2000 * i);
				break;
			case 2:
				ito[i] = new AgilUp(getWidth() + 2000 * i);
				break;
			case 3:
				ito[i] = new AgilDown(getWidth() + 2000 * i);
				break;
			case 4:
				ito[i] = new Score(getWidth() + 2000 * i, this);
			}

		}
		Thread t1 = new Thread(this);
		t1.start();
	}

	@Override
	public void run() {
		while (true) {
			
			for (int i = 0; i < p.length; i++) {
				int testx = p[i].getX();
				if (testx < 0 - p[i].getWidth()) {
					Random r = new Random();
					int fakei = i;
					if (i == 0) {
						fakei = p.length;
					}
					p[i].setX(p[fakei - 1].getX() + p[i].getWidth()
							+ Pictures.level * 10 + r.nextInt(25));
				}
			}
			gameOver = b.getGameOver();

			if (levelcheck > 1000) {
				Pictures.level++;
				levelcheck = 0;
			}
			levelcheck++;
			if (cityX > getWidth() * -1) {
				cityX -= cityDx;
			} else {
				cityX = 0;
			}
			if (gameOver != true) {
				score++;
			}

			Random r = new Random();
			for (int i = 0; i < ito.length; i++) {
				if (ito[i].isCreateNew()) {
					ito[i] = null;
					switch (r.nextInt(5)) {
					case 0:
						ito[i] = new GravUp(getWidth() + 10 * r.nextInt(500));
						break;
					case 1:
						ito[i] = new GravDown(getWidth() + 10 * r.nextInt(500));
						break;
					case 2:
						ito[i] = new AgilUp(getWidth() + 10 * r.nextInt(500));
						break;
					case 3:
						ito[i] = new AgilDown(getWidth() + 10 * r.nextInt(500));
						break;
					case 4:
						ito[i] = new Score(getWidth() + 10 * r.nextInt(500),
								this);
					}
					ito[i].setCreateNew(false);
				}
			}
			b.update(this);

			for (int i = 0; i < p.length; i++) {
				p[i].update(this, b);
			}
			for (int i = 0; i < ito.length; i++) {
				ito[i].update(this, b);
			}

			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			gang = i.getGraphics();
		}

		gang.setColor(getBackground());
		gang.fillRect(0, 0, this.getSize().width, this.getSize().width);

		gang.setColor(getForeground());
		paint(gang);

		g.drawImage(i, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(15, 77, 147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(city, (int) cityX, 0, this);
		g.drawImage(city, (int) cityX + getWidth(), 0, this);
		b.paint(g);
		for (int i = 0; i < p.length; i++) {
			p[i].paint(g);
		}

		for (int i = 0; i < ito.length; i++) {
			ito[i].paint(g);
		}

		String s = "Score " + Integer.toString(score);
		Font f = new Font("Serif", Font.BOLD, 32);
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawString(s, getWidth() - 150 + 2, 50 + 2);
		g.setColor(new Color(198, 226, 255));
		g.drawString(s, getWidth() - 150, 50);
		String sr = "Level " + Pictures.level;
		g.setFont(f);
		g.setColor(Color.RED);
		if(gameOver!=true){
		g.drawString(sr, getWidth() - 150, 50 + 100);}

		if (gameOver == true) {
			g.drawString("Level - Game Over!", 500, 50 + 100);
			g.setColor(Color.WHITE);
			g.drawString("GameOver", 300, 300);
			// mouse check g.drawRect(280, 320, 200, 40);

			if (mousein == true) {
				g.setColor(Color.RED);
				g.drawString("PLAY AGAIN", 280, 340);

			} else {
				g.setColor(Color.WHITE);
				g.drawString("PLAY AGAIN", 280, 340);
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (gameOver == true) {

			if (e.getX() > 280 && e.getX() < 480) {
				if (e.getY() > 320 && e.getY() < 360) {
					mousein = true;
				}
			}

			if (e.getX() < 280 || e.getX() > 480) {
				mousein = false;

			}

			if (e.getY() < 320 || e.getY() > 360) {
				mousein = false;

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (mousein == true) {
			// New Game
			b = null;
			b = new Ball();
			score = 0;
			Pictures.level = 1;
			levelcheck = 0;
			for (int i = 0; i < p.length; i++) {
				Random r = new Random();

				p[i] = new Platform(i * 120, 300);
			}

			for (int i = 0; i < ito.length; i++) {
				Random r = new Random();
				switch (r.nextInt(5)) {
				case 0:
					ito[i] = new GravUp(getWidth() + 2000 * i);
					break;
				case 1:
					ito[i] = new GravDown(getWidth() + 2000 * i);
					break;
				case 2:
					ito[i] = new AgilUp(getWidth() + 2000 * i);
					break;
				case 3:
					ito[i] = new AgilDown(getWidth() + 2000 * i);
					break;
				case 4:
					ito[i] = new Score(getWidth() + 2000 * i, this);
				}

			}
			mousein = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

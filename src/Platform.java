import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Platform {
	int dx;
	int x, y, width, height;
	Image Plat;
	URL url;
	float frame = 0;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Platform() {
		// TODO Auto-generated constructor stub\
		dx = -1;
		x = 450;
		y = 300;
		width = 120;
		height = 40;
		dx = -10;
	}

	public Platform(int x, int y) {
		this.x = x;
		this.y = y;
		width = 120;
		height = 40;
		dx = -1;
		Plat = Pictures.platform;
		
	}

	public void update(StartingPoint sp, Ball b) {
		int tester = (int)(frame + .1);
		if(tester < 3){
			frame+=.1;
		} else {
			frame = 0;
		}
		x += -(Pictures.level);
		
		check(b);
		if (x < 0 - width) {
			Random r = new Random();
			y = sp.getHeight() - 40 - r.nextInt(400);
			Random r1 = new Random();
			// x = sp.getWidth() + r1.nextInt(300);
		}
	}

	private void check(Ball b) {
		int ballx = b.getX();
		int bally = b.getY();
		int radius = b.getRadius();
		if (bally + radius > y && bally + radius < y + height) {
			if (ballx + radius > x && ballx + radius < x + width + 25) {
				Double newDy = b.getGameDy();
				b.setY(y - radius);
				b.setDy(newDy);
				Pictures.Bounce.play();
			}
		}
	}

	public void paint(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
		//g.drawImage(Plat, x, y, Pictures.sp);
		g.drawImage(Plat, x, y, x + width, y + height, 0, 40*(int)frame, 120, 40*(int)frame + 40, Pictures.sp);

	}
}

import java.awt.Color;
import java.awt.Graphics;

public class GravUp extends Item {

	public GravUp(int x) {
		super(x);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		super.paint(g);
	}
	
	@Override
	public void perform(Ball b) {
		// TODO Auto-generated method stub
		b.setGravity(b.getGravity() + 3);
	}
	
	
	
	

}

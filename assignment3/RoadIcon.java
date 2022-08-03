//a1694827 zhenghui wang


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;



public class RoadIcon extends JComponent  implements RoadListener {

	private String roadName;
	private int length;
	private Place from;
	private Place to;
	private Color original;
	/*************** connection *****************/
	private MapPanel panel;
	private Road road;
	private int SizeX,SizeY;
	public RoadIcon(Road road,MapPanel panel) {
		this.road = road;
		from = road.firstPlace();
		to = road.secondPlace();
		roadName = road.roadName();
		length = road.length();
		original = Color.BLACK;
		SizeX = (from.getX()>to.getX())? from.getX():to.getX();
		SizeY = (from.getY()>to.getY())? from.getY():to.getY();
		setSize(new Dimension(SizeX+150,SizeY+150));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int fromPosX = from.getX();
		int fromPosY = from.getY();
		int toPosX = to.getX();
		int toPosY = to.getY();
		SizeX = (from.getX()>to.getX())? from.getX():to.getX();
		SizeY = (from.getY()>to.getY())? from.getY():to.getY();
		setSize(new Dimension(SizeX+150,SizeY+150));
	    g2d.setColor(original);
	    g2d.drawLine(fromPosX,fromPosY,toPosX,toPosY);
	    g2d.drawString(roadName+" "+length, (fromPosX+toPosX)/2, (fromPosY+toPosY)/2);
	}

	public void roadChanged() {
		
	}
	
}

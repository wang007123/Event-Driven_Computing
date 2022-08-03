//a1694827 zhenghui wang

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;



public class PlaceIcon extends JComponent  implements PlaceListener {
	private Color original;
	private int positionX;
	private int positionY;
	private int size;
	private Rectangle bound;
	/************ initial parameter***************************/
	private int clicked;
	/*************** connection *****************/
	private MapPanel panel;
	private Place place;

	public PlaceIcon(Place place,MapPanel panel) {
		//System.out.println("X: "+posX+",Y: "+posY);
		//initial parameter
		positionX = place.getX();
		positionY = place.getY();
		clicked = 0;
		size = 10;
		//establish connection
		this.place = place;
		this.panel = panel;
		this.setLocation(positionX, positionY);
		bound = new Rectangle(positionX, positionY, size, size);
		this.setBounds(bound);
		this.setSize(size+30,size+30);
		addMouseEvent();
	}
	public Rectangle getBounds() {
		return bound;
	}
	public void refreshData() {
		checkColor();
		positionX = place.getX();
		positionY = place.getY();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		refreshData();
	    g2d.setColor((clicked == 0) ? original : Color.GREEN);
	    //if(clicked ==1 ) Sy
	    g2d.fillRect(positionX, positionY, size, size); 
	    g2d.drawString(place.getName(), positionX, positionY+size+12);
	}
	
	public void resetClicked() {
		this.clicked = 0;
	}
	public void pressClicked() {
		this.clicked = 1;
	}

	
	public void checkColor() {
		if(place.isEndPlace()) {
			original = Color.RED;
		}else if(place.isStartPlace()) {
			original = Color.ORANGE;
		}else {
			original = Color.BLUE;
		}
	}
	
	private void addMouseEvent() {
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	super.mousePressed(e);
		    }
		    public void mouseEnetered(MouseEvent mEvt) {
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	super.mouseExited(e);
		    }
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	super.mouseClicked(e);
		    	if(panel.state ==1) {
		    
		    		panel.fromPlace = place;
		    		panel.state = 2;
		    	}else if(panel.state ==2) {
		    		panel.state = 0;
		    		panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		    		panel.toPlace = place;
		    		panel.add(panel.fromPlace, panel.toPlace, panel.newRoadName, panel.length);
		    	}
		    	int tmp = (clicked == 0) ? 1 : 0;
		    	panel.resetSelections();
		    	if(tmp == 1) panel.selected = place;
		    	else panel.selected = null;
		    	clicked = tmp;
//		        System.out.println("mouseClicked");
		        repaint();
		    }
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	super.mouseReleased(e);
//		        System.out.println("mouseReleased");
		    }
		});
		this.addMouseMotionListener(new MouseAdapter() {
		    @Override
		    public void mouseDragged(MouseEvent e) {
		    	super.mouseDragged(e);
		    	positionX += e.getX();
		    	positionY += e.getY();
		    	place.moveBy(e.getX(), e.getY());
		    	setLocation(positionX, positionY);
		    	bound = new Rectangle(positionX, positionY, size, size);
				setBounds(bound);
//		    	System.out.println("Mouse Dragged: "+e.getX() + " / " + e.getY());
		    	panel.repaint();
		    }

		    @Override
		    public void mouseMoved(MouseEvent e) {
		    	
		    }
		});
	}

	public void placeChanged() {
		
	}
	

}

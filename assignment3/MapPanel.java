//a1694827 zhenghui wang

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.JPanel;

public class MapPanel extends JPanel implements MapListener{
	public Map map;
	private Set<Place> places;
	private Set<Road> roads;
	private Hashtable<String, PlaceIcon> listeners;
	private Hashtable<String, RoadIcon> roadListeners;
	private Place start;
	private Place end;
	public Place selected;
	public int SizeX;
	public int SizeY;
	private boolean drawR;
	private Rectangle rect;
	private int drawX,drawY,draw1,draw2;
	public int state =0;
	private int mouseX,mouseY;
	public Place fromPlace;
	public Place toPlace;
	public int length;
	public String newRoadName;
	private MapEditor edit;
//	private PlaceIcon tmp1 = new PlaceIcon(50,50);
//	private PlaceIcon tmp2 = new PlaceIcon(100,50);
	public MapPanel(Map m, MapEditor edit) {
		this.edit =edit;
		listeners = new Hashtable<String, PlaceIcon>();
		roadListeners = new Hashtable<String, RoadIcon>();
		map = m;
		drawR =false;
		fromPlace=null;
		toPlace =null;
		length =0;
		newRoadName ="";
		places = new HashSet<Place>();
		places.addAll(map.getPlaces());
		roads = new HashSet<Road>();
		roads.addAll(map.getRoads());
		SizeX = 500;
		SizeY = 500;
		//this.setSize(SizeX,SizeY);
		addMouseEvent();
		addMouseMontionEvent();
	}

	private void addMouseEvent() {
		this.addMouseListener(new MouseAdapter(){
			@Override
		    public void mouseClicked(MouseEvent e) {
				resetSelections();
				//start.getBounds();
			}
			@Override
		    public void mouseReleased(MouseEvent e) {
				if(drawR == true) {
					drawR = false;
					resetSelections();
					Enumeration e1 = listeners.keys(); 
					while (e1.hasMoreElements()) { 
						Object tmpSave = e1.nextElement();
						Rectangle tmp = listeners.get(tmpSave).getBounds();
					   if(rect.intersects(tmp)) {
//						   System.out.println(tmp);
//						   System.out.println(rect);
						   listeners.get(tmpSave).pressClicked();
					   }
					} 
					repaint();
				}

			}
		});
	}
	private void addMouseMontionEvent() {
		this.addMouseMotionListener(new MouseAdapter() {
		    @Override
		    public void mouseDragged(MouseEvent e) {
		    	super.mouseDragged(e);
		    	if(drawR == false) {
		    		drawX=e.getX();
		    		drawY=e.getY();
		    		drawR = true;
		    	}
		    	draw1=e.getX();
		    	draw2=e.getY();
		    	repaint();
		    }

		    @Override
		    public void mouseMoved(MouseEvent e) {
		    	mouseX = e.getX();
		    	mouseY = e.getY();
		    	repaint();
		    }
		});
	}
    public void add(Place a,Place b,String name, int length) {
    	edit.createNewRoad(a,b,name,length);
    }
	public void resetNewRoad() {
		fromPlace=null;
		toPlace =null;
		length =0;
		newRoadName ="";
	}
	public void resetSelections() {
		Enumeration e1 = listeners.keys(); 
		while (e1.hasMoreElements()) { 
		   listeners.get(e1.nextElement()).resetClicked();
		} 
		selected = null;
		repaint();
	}
	public void changeMap(Map m) {
//		System.out.println("map changed");
		map = m;
		placesChanged();
	}
	//initial size of this JPanel
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(SizeX, SizeY);
    }
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //map.getTripDistance();
        //System.out.println("i'm here "+places.size());
        for(Place i: places) {
        	for (String key: listeners.keySet()) {
        	    //System.out.println("key : " + key);
        	    listeners.get(key).paintComponent(g);
        	}
        }
        if(state == 2 ) {
        	g.drawLine(fromPlace.getX(), fromPlace.getY(),mouseX , mouseY);
        }
        if(drawR == true) {
        	int x = (drawX<draw1)?drawX:draw1;
        	int y = (drawY<draw2)?drawY:draw2;
        	if(x <0) x=0;
        	if(y<0) y =0;
        	int lengthX = (drawX<draw1)?draw1-x:drawX-x;
        	int lengthY = (drawY<draw2)?draw2-y:drawY-y;
        	g.setColor(Color.RED);
        	rect = new Rectangle(x, y, lengthX, lengthY);
//        	System.out.println(x+":"+y+":"+lengthX+":"+lengthY);
        	g.drawRect(x, y, lengthX, lengthY);
        }
    }
    public void placesChanged() {
		Set<Place> tmpPlaces = new HashSet<Place>();
		tmpPlaces.addAll(map.getPlaces());
	   	tmpPlaces.removeAll(places);
	   	places.clear();
	   	places.addAll(map.getPlaces());
	   	 for(Place i: tmpPlaces) {
	   		 i.deleteListener(null);
	   		 PlaceIcon newlistener = new PlaceIcon(i,this);
	   		 i.addListener(newlistener);
	   		 if(listeners.get(i.getName())!= null) {
	   			 remove(listeners.get(i.getName()));
	   		 }
	   		 add(newlistener);
	   		 listeners.put(i.getName(), newlistener);
	   	 }
	   repaint();
    }

    //Called whenever the number of roads in the map has changed
    public void roadsChanged() {
		Set<Road> tmpRoads = new HashSet<Road>(); ;
		tmpRoads.addAll(map.getRoads());
		tmpRoads.removeAll(roads);
		roads.clear();
		roads.addAll(map.getRoads());
	   	 for(Road i: tmpRoads) {
	   		 i.deleteListener(null);
	   		 RoadIcon newlistener = new RoadIcon(i,this);
	   		 i.addListener(newlistener);	
	   		 if(roadListeners.get(i.roadName())!= null) {
//	   			System.out.println("road Changed2233");
	   			 remove(roadListeners.get(i.roadName()));
	   		 }
	   		 add(newlistener);
	   		 roadListeners.put(i.roadName(), newlistener);
	   	 }
	    repaint();
    }

    //Called whenever something about the map has changed
    //(other than places and roads)
    public void otherChanged() {
    	start = map.getStartPlace();
    	end = map.getEndPlace();
    	repaint();
//    	System.out.println("other Changed");
    }

}

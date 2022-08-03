

package draw;
/**
*
* @author Kevin & Irene
*/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Map_2D extends JFrame {
	/* Initialize the components for the GUI
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JButton left;
	private static JButton right;
	private static JButton up;
	private static JButton down;
	private static JButton stop;
	private static JButton keyboardMode;
	private static JButton pilotMode1;
	private static JButton pilotMode2;
	private static JButton pilotMode3;
	private static JPanel paintPanel;
	private static JPanel buttonPanel;
	private static GridBagConstraints c;
	
	private static JButton botton1;
	public static drawMap drawFuntcion;
	//public Computer computer;
	
	//real size of A0 is about 1200*850mm, 
	//the size of the map will be 60*42 blocks, the size of each block
	// is 20*20mm
	private static int[][] map = new int[60][42];
	
	/**
	*
	* Construct
	*/
	
	public Map_2D() {
		setTitle("Manual Control Mode version 0.2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		paintPanel = new JPanel();
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,3));
		add(paintPanel);
		add(buttonPanel);
//		setLayout(new GridBagLayout());
//		c = new GridBagConstraints();
		//setSize(400,500);
        init();
        buttonLeft();
        buttonRight();
        buttonUp();
        buttonDown();
        buttonStop();
        pilotMode1();
        pilotMode2();
        pilotMode3();
        keyboard();
        keyboardLeft();
        keyboardRight();
        keyboardUp();
        keyboardDown();
        keyboardStop();
        setVisible(true);
        setLayout(null);  
        pack();
	}
	// create the GUI and call the draw map 
	public static void main(String[] args) {
		new Map_2D();
		for(int i =0;i<40;i++) {
			map[1][i] =1;
			map[i][1] =1;
		}

	}
	/**
	*
	* initialize
	*/
	private void init() {
		drawFuntcion = new drawMap();
	    buttonPanel.add(drawFuntcion);
	}
	private void buttonLeft() {
		left = new JButton("TURN   LEFT\n");
		buttonPanel.add(left);
		
		left.addMouseListener(new MouseAdapter() {
			 
			@Override
			public void mouseReleased(MouseEvent e) {
				//computer.leftRelease();
			}
 
			@Override
			public void mousePressed(MouseEvent e) {
				//computer.leftPress();
			}
		});
		buttonPanel.add(left);
	}
	private void buttonRight() {
		right = new JButton("TURN  RIGHT\n");
		//Add mouse control
		right.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mouseReleased(MouseEvent e) {
				//rightRelease();
			}
 
 
 
			@Override
			public void mousePressed(MouseEvent e) {
				//rightPress();
			}
 
 
		});
		buttonPanel.add(right);
	}
	private void buttonUp() {
		//Initialize button
		up = new JButton("UP");
		
		
		//Add mouse control
		up.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mouseReleased(MouseEvent e) {
				//upRelease();
			}
 
 
 
			@Override
			public void mousePressed(MouseEvent e) {
				//upPress();
			}
 
 
		});
		buttonPanel.add(up);
		
	}
	private void buttonDown() {
		down = new JButton("DOWN");
		
		buttonPanel.add(down);
		//Add mouse control
		down.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mouseReleased(MouseEvent e) {
				//downRelease();
			}
 
 
 
			@Override
			public void mousePressed(MouseEvent e) {
				//downPress();
			}
 
 
		});
	}
	private void buttonStop() {
		//Initialize button
		stop = new JButton("STOP AND EXIT\n");
		
		buttonPanel.add(stop);
		//Add mouse control
		stop.addMouseListener(new MouseAdapter() {
 
			@Override
			public void mousePressed(MouseEvent e) {
				//sendCommand("STOP");
				
			}
			
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
				
			}
		});
	}
	/*Pilot control 1
	 * Add a button on the GUI
	 * By clicking the button,
	 * the robot would follow
	 * the binding command
	 * Currently, it would let
	 * robot turn left for 90
	 * degrees
	 * */
	private  void pilotMode1() {
		pilotMode1 = new JButton("Pilot Mode 1\n");
		
		buttonPanel.add(pilotMode1);
		
		//Add mouse control
		pilotMode1.addMouseListener(new MouseAdapter() {
		 
			@Override
			public void mousePressed(MouseEvent e) {
						
			}
					
			public void mouseReleased(MouseEvent e) {
				pilotMode1();
						
			}
		});
	}
	/*Pilot control 2
	 * Add a button on the GUI
	 * By clicking the button,
	 * the robot would follow
	 * the binding command.
	 * Currently, it would let
	 * robot turn right for 90
	 * degrees
	 * */
	private  void pilotMode2() {
		pilotMode2 = new JButton("Pilot Mode 2\n");
		
		buttonPanel.add(pilotMode2);
		
		//Add mouse control
		pilotMode2.addMouseListener(new MouseAdapter() {
				 
			@Override
			public void mousePressed(MouseEvent e) {
								
			}
							
			public void mouseReleased(MouseEvent e) {
				pilotMode2();
								
			}
		});	
	}
	/*PilotMode3
	 * Go straight line while detecting the color
	 * Stop when the detected color is not white
	 * */
	private  void pilotMode3() {
		pilotMode3 = new JButton("Pilot Mode 3\n");
		
		buttonPanel.add(pilotMode3);
		
		//Add mouse control
		pilotMode3.addMouseListener(new MouseAdapter() {
				 
			@Override
			public void mousePressed(MouseEvent e) {
								
			}
							
			public void mouseReleased(MouseEvent e) {
				pilotMode3();
								
			}
		});	
	}
	
	private void keyboard() {
		//Keyboard binding
		keyboardMode = new JButton("SWITCH MODE\n");
		buttonPanel.add(keyboardMode);
	}
	
	private void keyboardLeft() {
		//Add keyboard control - Bind A to Turn left
		Action APressedAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//leftPress();
				
				
			}
		};
		
		Action AReleasedAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//leftRelease();
				
				
			}
		};
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("A"), "APressed");
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("released A"), "AReleased");
		keyboardMode.getActionMap().put("APressed", APressedAction);
		keyboardMode.getActionMap().put("AReleased", AReleasedAction);
	}
	private void keyboardRight() {
		//Add keyboard control - Bind D to Turn right
		Action DPressedAction = new AbstractAction() {

			/**
			 * 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//rightPress();
						
						
			}
		};
				
		Action DReleasedAction = new AbstractAction() {

			/**
			 * 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//rightRelease();	
			
			}
		};
				
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("D"), "DPressed");
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("released D"), "DReleased");
		keyboardMode.getActionMap().put("DPressed", DPressedAction);
		keyboardMode.getActionMap().put("DReleased", DReleasedAction);
	}
	private void keyboardUp() {
		//Add keyboard control - Bind W to Go straight
		Action WPressedAction = new AbstractAction() {

			/**
			 * 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//upPress();
						
						
			}
		};
				
		Action WReleasedAction = new AbstractAction() {

			/**
			 * 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//upRelease();
						
						
			}
		};
				
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("W"), "WPressed");
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("released W"), "WReleased");
		keyboardMode.getActionMap().put("WPressed", WPressedAction);
		keyboardMode.getActionMap().put("WReleased", WReleasedAction);
	}
	private void keyboardDown() {
		//Add keyboard control
		Action SPressedAction = new AbstractAction() {

			/**
			 * 
			*/
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				//downPress();

			}
		};
				
		Action SReleasedAction = new AbstractAction() {

			/**
			 * 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//downRelease();
						
			}
		};
				
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("S"), "SPressed");
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("released S"), "SReleased");
		keyboardMode.getActionMap().put("SPressed", SPressedAction);
		keyboardMode.getActionMap().put("SReleased", SReleasedAction);
	}
	private void keyboardStop() {
		//Add keyboard control - Bind ESC to Stop
		Action ESCPressedAction = new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				//sendCommand("STOP");
				//System.exit(0);
			}
		};
		
		keyboardMode.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "ESCPressed");
		keyboardMode.getActionMap().put("ESCPressed", ESCPressedAction);
				
	}

  
  /**
	*
	* Draw the map
	*/
	private class drawMap extends JComponent {
      
	      /**
		 * 
		 */
		private static final long serialVersionUID = 7499099534045841685L;
	
	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(500 , 500 );
	    }
	    
		public void paint(Graphics g) {
	    	super.paintComponent(g);
	          
	    	Graphics2D g2d = (Graphics2D) g;
	    	g2d.setPaint(Color.gray);
	 	    
	    	for(int i =0;i<map.length;i++) {
	    		 
	    		for(int j =0;j<map[0].length;j++) {
	    			 
	    				if(map[i][j] == 1) {
	    						g2d.setPaint(Color.black);
	    				 
	    				}else if(map[i][j] == 2)  {
	    				 g2d.setPaint(Color.red);
	    				 
	    				}else {
	    						g2d.setPaint(Color.white);
	    				}
	    			 
	    				g2d.fill(new Rectangle2D.Double(i*5+20, j*5+20, 5, 5));
	    		}
	    	 
	    	}
		}
	
	    public void updataMap(int xPos, int yPos, int definedObject) {
	        map[xPos][yPos]= definedObject;
	        repaint();
	    }
	}
}




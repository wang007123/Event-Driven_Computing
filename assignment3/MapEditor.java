//a1694827 zhenghui wang

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.print.DocFlavor.STRING;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MapEditor extends javax.swing.JFrame {
    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem appendButton;
    private javax.swing.JMenuItem deleteButton;
    private javax.swing.JMenuItem exitButton;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem newPlaceButton;
    private javax.swing.JMenuItem newRoadButton;
    private javax.swing.JMenuItem openButton;
    private javax.swing.JMenuItem saveButton;
    private javax.swing.JMenuItem setEndButton;
    private javax.swing.JMenuItem setStartButton;
    private javax.swing.JMenuItem unsetEndButton;
    private javax.swing.JMenuItem unsetStartButton;
    // End of variables declaration     
    private MapPanel jPanel1;
	private FileReader readFile;
	private FileWriter writeFile;
	private Map map = new MapImpl();

    public MapEditor() {
    	initComponents();
        
    }

                      
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        openButton = new javax.swing.JMenuItem();
        appendButton = new javax.swing.JMenuItem();
        saveButton = new javax.swing.JMenuItem();
        exitButton = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        newPlaceButton = new javax.swing.JMenuItem();
        newRoadButton = new javax.swing.JMenuItem();
        setStartButton = new javax.swing.JMenuItem();
        unsetStartButton = new javax.swing.JMenuItem();
        setEndButton = new javax.swing.JMenuItem();
        unsetEndButton = new javax.swing.JMenuItem();
        deleteButton = new javax.swing.JMenuItem();
        jPanel1 = new MapPanel(map,this);
        map.addListener(jPanel1);
        jPanel1.changeMap(map);
        jPanel1.setLayout(null);
        add(jPanel1);
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu3.setText("File");

        openButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openButton.setText("Open...");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });
        jMenu3.add(openButton);

        appendButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        appendButton.setText("Append....");
        appendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appendButtonActionPerformed(evt);
            }
        });
        jMenu3.add(appendButton);

        saveButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveButton.setText("Save as...");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jMenu3.add(saveButton);

        exitButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitButton.setText("Quit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jMenu3.add(exitButton);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Edit");

        newPlaceButton.setText("New Place");
        newPlaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPlaceButtonActionPerformed(evt);
            }
        });
        jMenu4.add(newPlaceButton);

        newRoadButton.setText("New Road");
        newRoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRoadButtonActionPerformed(evt);
            }
        });
        jMenu4.add(newRoadButton);

        setStartButton.setText("Set Start");
        setStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setStartButtonActionPerformed(evt);
            }
        });
        jMenu4.add(setStartButton);

        unsetStartButton.setText("Unset Start");
        unsetStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unsetStartButtonActionPerformed(evt);
            }
        });
        jMenu4.add(unsetStartButton);

        setEndButton.setText("Set End");
        setEndButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEndButtonActionPerformed(evt);
            }
        });
        jMenu4.add(setEndButton);

        unsetEndButton.setText("Unset End");
        unsetEndButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unsetEndButtonActionPerformed(evt);
            }
        });
        jMenu4.add(unsetEndButton);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jMenu4.add(deleteButton);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);
//        setLayout(null);

//
        pack();
    }                               

    private void newPlaceButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
//         System.out.println("new place Button clicked");
         String name = JOptionPane.showInputDialog(this, "What's the name of the new Place");
         try{
        	 map.newPlace(name,(jPanel1.SizeX/2)-5,(jPanel1.SizeY/2)-5);
        	 jPanel1.repaint();
         }catch(Exception e) {
        	 ErrorWindow(e.toString());
         }
    }                                              

    private void newRoadButtonActionPerformed(java.awt.event.ActionEvent evt) {  
    	jPanel1.resetNewRoad();
//         System.out.println("new road Button clicked");
         String name = JOptionPane.showInputDialog(this, "What's the name of the new road?");
         String tmp = JOptionPane.showInputDialog(this, "What's the length of the new road?");
         int length;
         try{
        	 length = Integer.parseInt(tmp);
         }catch(Exception e) {
        	 ErrorWindow("Length is illegal,try again");
        	 return;
         }
         jPanel1.newRoadName=name;
         jPanel1.length = length;
         jPanel1.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
         jPanel1.state = 1;
         
    }                                             
    public void createNewRoad(Place a,Place b,String name, int length) {
    	try {
    		map.newRoad(a, b, name, length);
    	}catch(Exception e) {
    		ErrorWindow(e.toString());
    	}
    }
    private void setStartButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
//         System.out.println("set start Button clicked");
         if(jPanel1.selected == null) {
        	 ErrorWindow(":Please Select a Place to set Start!");
         }else {
        	 jPanel1.map.setStartPlace(jPanel1.selected);
         }
    }                                              

    private void unsetStartButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
//        System.out.println("unset start Button clicked");
        jPanel1.map.setStartPlace(null);
    }                                                

    private void setEndButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
//         System.out.println("set end Button clicked");
         if(jPanel1.selected == null) {
        	 ErrorWindow(":Please Select a Place to set End!");
         }else {
        	 jPanel1.map.setEndPlace(jPanel1.selected);
         }
    }                                            

    private void unsetEndButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
//         System.out.println("unset end Button clicked");
         jPanel1.map.setEndPlace(null);
    }                                              

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
//         System.out.println("delete Button clicked");
    }                                            

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
//         System.out.println("open Button clicked");
         Map newMap = new MapImpl();
         MapPanel newPanel = new MapPanel(newMap,this);
         newMap.addListener(newPanel);
         newPanel.changeMap(newMap);
         JFileChooser fileChooser = new JFileChooser();
         File workingDirectory = new File(System.getProperty("user.dir"));
         fileChooser.setCurrentDirectory(workingDirectory);
         int returnValue = fileChooser.showOpenDialog(null);
         if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
	          File selectedFile = fileChooser.getSelectedFile();
	          readFile = new FileReader(selectedFile);
//	          System.out.println("opening file:"+selectedFile.getName());
	          new MapReaderWriter().read(readFile, newMap);
	          newPanel.setLayout(null);
	          add(jPanel1);
	          
	          map = newMap;
	          newPanel.changeMap(map);
	          remove(jPanel1);
	          add(newPanel);
	          jPanel1 = newPanel;
	          revalidate();
//	          System.out.println("opening map successful");
	        } catch (Exception ex) {
	        	ErrorWindow(ex.toString());
	        }
        }
    }                                          

    private void appendButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
//         System.out.println("append Button clicked");
         JFileChooser fileChooser = new JFileChooser();
         File workingDirectory = new File(System.getProperty("user.dir"));
         fileChooser.setCurrentDirectory(workingDirectory);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
		      File selectedFile = fileChooser.getSelectedFile();
		      readFile = new FileReader(selectedFile);
//		      System.out.println("appending file:"+selectedFile.getName());
		      new MapReaderWriter().read(readFile, map);
//		      System.out.println("appending map successful");
            } catch (Exception e) {
            	ErrorWindow(e.toString());
            }
        }
    }                                            

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
//         System.out.println("save Button clicked");
         JFileChooser fileChooser = new JFileChooser();
         //chooser.setCurrentDirectory(new File("/home/me/Documents"));
         File workingDirectory = new File(System.getProperty("user.dir"));
         fileChooser.setCurrentDirectory(workingDirectory);
         int retrival = fileChooser.showSaveDialog(null);
         if (retrival == JFileChooser.APPROVE_OPTION) {
             try {
            	 File selectedFile = fileChooser.getSelectedFile();
                 writeFile = new FileWriter(selectedFile);
//                 System.out.println("save  file:"+selectedFile.getName());
                 new MapReaderWriter().write(writeFile, map);
//                 System.out.println("save  map successful");
             } catch (Exception ex) {
            	 ErrorWindow(ex.toString());
             }
         }
    }                                          
 
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
         System.exit(0);
    }                                          
    
    public void ErrorWindow(String e) {
    	jPanel1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    	String tmp = e;
    	String split[]=tmp.split(":",2);
    	if(split.length>1) {
    		 JOptionPane.showMessageDialog(jPanel1,split[1]);
    	}else {
    		JOptionPane.showMessageDialog(jPanel1,tmp);
    	}
    }

    public static void main(String args[]) {
        new MapEditor().setVisible(true);
    }
   
}



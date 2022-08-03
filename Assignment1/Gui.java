package src;

import java.net.*;
import java.io.*;

public class action{
	public Gui gui= new Gui();
	public Computer computer = new Computer();

    public void connect(Gui gui, Computer computer){
        this.gui = gui;
        this.computer = computer;
    }

    public static void main(String[] args) throws IOException {
		computer = new Computer();
		init();
		gui.connect(computer);
		messenger msg = new messenger(socket);
		t = new Thread(msg);
		t.start();
		//map.connect(gui);
		while (true) {
			// collects data sent from robot
			/*String msg_in = recieveMessage();
			Data object = getData(msg_in);
			object = determineObject(object);
			// sends data to gui
			int x = object.x;
			int y = object.y;
			//String obj = object.object;
			updateMap(x, y, object.id);*/

			// gui renders handles foreground functionality
			// gui.render();
		}
	}
}
 

public class Computer {
	// variables and components
	// reference to the GUI
	// map object (probably a 2d array)
	// reference to gui class object.
	// socket connection to EV3 brick for sending and receiving information
	//private static Map_2D map;
	private GUI gui;
	private static Computer computer;
	private static BufferedWriter bufwriter;
	private static BufferedReader bufreader;
	private static Socket socket;
	private static Thread t;
	
	
	private class messenger implements Runnable {
		private Socket socket;
		public messenger(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String line = "Aye";
			try {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while (true) {
					line = br.readLine();
					if(line != null) {
						System.out.println(line);
						String[] splited = line.split(" ");
						int xPosition = Integer.parseInt(splited[0]);
						int yPosition = Integer.parseInt(splited[1]);
						int colorID = Integer.parseInt(splited[2]);
						gui.map[xPosition][yPosition] = 1;
						
					}
					
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 		System.out.println("Error with message receiving!");
		}
		
	}
	public void init() {
		this.gui = new GUI();
		this.gui.map[59][41] =1;
		this.gui.map[59][0] =1;
		this.gui.map[0][41] =1;
		this.gui.map[0][0] =1;
		connect("10.0.1.1", 6655);
	}
	
	// Attempt to create a socket to connect to the robot or fail with error
	public static void connect(String serverName, int port) {
		try {
			socket = new Socket(serverName, port);
			bufwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bufreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Attempt to send a string command to the robot via socket or fail with error
	public static void sendMessage(String command) {
		try {
			bufwriter.write(command+"\n");
			bufwriter.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Receives message from the socket and returns first value as a string in the buffer
	public String recieveMessage() {
		try {
			String result = new String();
			result = bufreader.readLine();
			Data object = getData(result);
			object = determineObject(object);

			// sends data to gui
			int x = object.x;
			int y = object.y;
			//String obj = object.object;
			this.updateMap(x, y, object.id);
			return result;
		} catch (IOException e1) {
			e1.printStackTrace();
			return "";
		}
	}
 
	// Sense output to populate map with data (2D array)
	public void updateMap(int x, int y, int value) {
		this.gui.map[x][y] = value;
	}

	public static class Data {
		int x, y;
		int id;
		String colour;
		String object;
		Data(){
			
		}
		Data(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}

	public static Data determineObject(Data obj) {
		if 		(obj.id == 6) obj.colour = "WHITE";
		else if (obj.id == 2) obj.colour = "BLUE";
		else if (obj.id == 1) obj.colour = "GREEN";
		else if (obj.id == 7) obj.colour = "BLACK";
		else obj.colour = "INVALID";
		obj.object = obj.colour;
		return obj;
	}

	public static Data getData(String msg_in) {
		String[] tokens = msg_in.split(" ");
		Data object = new Data();
		object.x = 5;
		object.y = 5;
		//object.x = Integer.parseInt(tokens[0]);
		//object.y = Integer.parseInt(tokens[1]);
		object.id = Integer.parseInt(tokens[2]);
		
		return object;
	}
  
	public void downPress() {
		sendMessage("DOWN-PRESS");
	}

	public void downRelease() {
		sendMessage("DOWN-RELEASE");
	}

	public void leftPress() {
		sendMessage("LEFT-PRESS");
	}

	public void upRelease() {
		sendMessage("UP-RELEASE");
	}

	public void leftRelease() {
		sendMessage("LEFT-RELEASE");
	}

	public void rightRelease() {
		sendMessage("RIGHT-RELEASE");
	}

	public void upPress() {
		sendMessage("UP-PRESS");
	}

	public void rightPress() {
		sendMessage("RIGHT-PRESS");
	}

	public void pilotMode1() {
		sendMessage("PILOT-MODE-1");
	}

	public void pilotMode2() {
		sendMessage("PILOT-MODE-2");
	}

	public void pilotMode3() {
		sendMessage("PILOT-MODE-3");
	}
	
	public void stop() {
		
		sendMessage("STOP");
		t.interrupt();
	}

}
//a1694827 zhenghui wang
import java.io.Reader;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class MapReaderWriter implements MapIo 
{
	public MapReaderWriter(){

	}
    //This class handles reading and writing map representations as 
    //described in the practical specification

    //Read the description of a map from the 
    //Reader r, and transfers it to Map, m.
    public void read (Reader r, Map m) 
      throws IOException, MapFormatException{
      	BufferedReader reader = null;
      	try{
      		reader = new BufferedReader(r);
      		String line;
      		while((line = reader.readLine()) != null){
      			String[] splited = line.split("\\s+");
      			if("place".equals(splited[0])){
      				m.newPlace(splited[1],Integer.parseInt(splited[2])
      					,Integer.parseInt(splited[3]));
      			}else if("road".equals(splited[0])){
      				Place firstPlace = m.findPlace(splited[1]);
					Place secondPlace = m.findPlace(splited[4]);
					if (firstPlace == null || secondPlace == null) {
						throw new CustomException("Road record place is not in list");
					}
      				m.newRoad(firstPlace, secondPlace, splited[2], Integer.parseInt(splited[3]));
      			}else if("start".equals(splited[0])){
      				Place start = m.findPlace(splited[1]);
      				if(start == null) {
      					throw new CustomException("start place p is not in the map.");
      				}
      				m.setStartPlace(start);
      			}else if("end".equals(splited[0])){   
      				Place end = m.findPlace(splited[1]); 
      				if(end == null) {
      					throw new CustomException("end place p is not in the map.");
      				}
      				m.setEndPlace(end);
      			}
     //  			}else {
					// throw new MapFormatException(1,"Invalid map format");
     //  			}
      		}
      	} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();  
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}  	
  	}
    
    
    //Write a representation of the Map, m, to the Writer w.
    public void write(Writer w, Map m)
      throws IOException{
      	BufferedWriter writer = null;
      	try{
      	    writer = new BufferedWriter(w);
      	    for (Place i: m.getPlaces() ) {
      	    	writer.write("place " + i.getName() 
      	    		+ " " + i.getX() + " " + i.getY() + "\n");
      	    }
      	    writer.write("\n");
      	    for (Road j: m.getRoads() ) {
      	    	writer.write("road " + j.firstPlace().getName() + " "
      	    		+ j.roadName() + " " + j.length() + " "
      	    		+ j.secondPlace().getName() + "\n" );
      	    }
      	    if(m.getStartPlace()!= null){
      	    	writer.write("start "+ m.getStartPlace().getName()+"\n");
      	    }
      	    if(m.getEndPlace()!= null){
      	    	writer.write("end "+ m.getEndPlace().getName()+"\n");
      	    }
      	} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();  
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}  	
      }
}

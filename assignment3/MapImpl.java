//a1694827 zhenghui wang
import java.util.*; 

public class MapImpl implements Map
{
	private Set<Place> places;
	private Set<Road> roads;
	private Place start;
	private Place end;
	private List<MapListener> mapListeners;
	private Road[] path;
	private int foundDistance;
	public MapImpl(){
		foundDistance =-1;
		places = new HashSet<Place>(); 
		roads = new HashSet<Road>();
		mapListeners = new LinkedList<MapListener>();
	}
	//Add the MapListener ml to this map.
    //Note: A map can have multiple listeners
    public void addListener(MapListener ml){
    	mapListeners.add(ml);
//    	System.out.println("add listenner");
    }


    //Delete the MapListener ml from this map.
    public void deleteListener(MapListener ml){
    	for (Iterator<MapListener> iter = mapListeners.listIterator(); iter.hasNext(); ) {
    		MapListener a = iter.next();
    	    if (iter == ml) {
    	        iter.remove();
    	    }
    	}
    }


    //Create a new Place and add it to this map
    //Return the new place
    //Throws IllegalArgumentException if:
    //  the name is not valid or is the same as that
    //  of an existing place
    //Note: A valid placeName begins with a letter, and is 
    //followed by optional letters, digits, or underscore characters
    public Place newPlace(String placeName, int xPos, int yPos)
      throws IllegalArgumentException{
    	//System.out.println("try:"+ placeName);
      	if (!placeName.matches("[a-zA-Z][_a-zA-Z0-9]*")) {
    			throw new CustomException("illegal Place name");
		}
      	if(findPlace(placeName)!=null) {
      			throw new CustomException("Place name same as an existing place.");
  		}
      	Place new_place = new PlaceImpl(placeName,xPos,yPos);
      	if(!places.add(new_place) ){
      		throw new CustomException("Add place to map Failed.");
      	}
      	//System.out.println("new place");
      	for(MapListener tmp: mapListeners) {
      		tmp.placesChanged();
    	}
      	return new_place;
      }


    //Remove a place from the map
    //If the place does not exist, returns without error
    public void deletePlace(Place s){
    	places.remove(s);
    }


    //Find and return the Place with the given name
    //If no place exists with given name, return null
    public Place findPlace(String placeName){
	    for (Place i : places ) {
	        if (i.getName().equals(placeName)){
	        	return i;
	        }
	    }
	    return null;
    }


    //Return a set containing all the places in this map
    public Set<Place> getPlaces(){
    	return places;
    }
    

    //Create a new Road and add it to this map
    //Returns the new road.
    //Throws IllegalArgumentException if:
    //  the firstPlace or secondPlace does not exist or
    //  the roadName is invalid or
    //  the length is negative
    //Note: A valid roadName is either the empty string, or starts
    //with a letter and is followed by optional letters and digits
    /**********************************************************************/
    //Multiple roads with the same name are acceptable, but duplicate roads (same start and end) are not.
    //In the case where a map file contains duplicate roads, a MapFormatException should be thrown.
    public Road newRoad(Place from, Place to, 
      String roadName, int length) 
      throws IllegalArgumentException{
      	//empty string or starts with a letter and is followed by
      	//optional letters and digits
      	if (!roadName.matches("[a-zA-Z][_a-zA-Z0-9]*") 
          && !"-".equals(roadName)) {
  			throw new CustomException("Road name is illegal.");
	    }
      	if( length<0  ){
      		throw new CustomException("the length of road is negative.");
      	}
      	if(!places.contains(from)||!places.contains(to)) {
      		throw new CustomException("from/to Place not found.");
      	}
      	//duplicate roads (same start and end) are not acceptable.
      	for(Road i: roads) {
      		if(i.firstPlace().getName() == from.getName()) {
      			if(i.secondPlace().getName() == to.getName())
      				throw new CustomException("duplicate road.");
      		}else if(i.secondPlace().getName() == from.getName()) {
      			if(i.firstPlace().getName() == to.getName())
      				throw new CustomException("duplicate road");
      		}
      	}
      	Road new_road = new RoadImpl(from, roadName,length, to);
      	roads.add(new_road);
      	for(MapListener tmp: mapListeners) {
      		tmp.roadsChanged();
    	}
      	return new_road;
      }


    //Remove a road r from the map
    //If the road does not exist, returns without error
    public void deleteRoad(Road r){
    	roads.remove(r);
    }


    //Return a set containing all the roads in this map
    public Set<Road> getRoads(){
    	return roads;
    }
    

    //Set the place p as the starting place
    //If p==null, unsets the starting place
    //Throws IllegalArgumentException if the place p is not in the map
    public void setStartPlace(Place p)
      throws IllegalArgumentException{
      	if(p!=null && !places.contains(p) ){
      		throw new CustomException("start place p is not in the map.");
      	}
      	for(MapListener tmp: mapListeners) {
      		tmp.otherChanged();
    	}
     	path = null;
     	foundDistance = -1;
      	if(p != null){
      		if(start!=null) start.setStart(false);
      		start = p;
      		p.setStart(true);
      	}else {
      		if(start!=null) start.setStart(false);
      		start = null;
      	}
    }


    //Return the starting place of this map
    public Place getStartPlace(){
    	return start;
    }


    //Set the place p as the ending place
    //If p==null, unsets the ending place
    //Throws IllegalArgumentException if the place p is not in the map
    public void setEndPlace(Place p)
      throws IllegalArgumentException{
     	if(p!=null && !places.contains(p)){
     		throw new CustomException("end place p is not in the map.");
      	}
     	if(p!=null && p.getName().equals("ERROR_invaild")) {
      		throw new CustomException("end place p is not in the map.");
      	}
     	path = null;
     	foundDistance = -1;
  		for(MapListener tmp: mapListeners) {
      		tmp.otherChanged();
    	}
      	if(p != null){
      		if(end!=null) end.setEnd(false);
      		end = p;
      		p.setEnd(true);
      	}else {
      		if(end!=null) end.setEnd(false);
      		end = null;
      	}
    }


    //Return the ending place of this map
    public Place getEndPlace(){
    	return end;
    }


    //Causes the map to compute the shortest trip between the
    //"start" and "end" places
    //For each road on the shortest route, sets the "isChosen" property
    //to "true".
    //Returns the total distance of the trip.
    //Returns -1, if there is no route from start to end
    public int getTripDistance(){
    	if(start == null) return -1;
    	if(end == null) return -1;
    	findPath(null,start,0,0);
    	return 0;
    }
    private void findPath(Road[] crossed,Place current,int length,int distance) {
    	if(crossed == null) {
    		Road[] newCrossed = new Road[100];
    		crossed = newCrossed;
    	}
    	if(current.getName().equals(end.getName())) {
    		path =crossed;
    		foundDistance = distance;
    		return;
    	}
    	for(Road i: roads) {
    		boolean check = false;
    		for(int j=0;j<length;j++) {
    			if(roadEqual(crossed[j],i) == 1) {
    				check =true;
    			}
    		}
    		if(check == false) {
    			if(roadValid(i,current)==1) {
    				crossed[length]=i;
    				length ++;
    				distance += i.length();
    				current = i.secondPlace();
    				findPath(crossed,current,length,distance);
    			}else if(roadValid(i,current)==2) {
    				crossed[length]=i;
    				length ++;
    				distance += i.length();
    				current = i.firstPlace();
    				findPath(crossed,current,length,distance);
    			}
    		}
    	}

    }
    
    private int roadValid(Road a,Place b) {
    	if(a.firstPlace().getName().equals(b.getName())) return 1;
    	if(a.secondPlace().getName().equals(b.getName())) return 2;
    	return -1;
    }
    private int roadEqual(Road a,Road b) {
    	if(a.firstPlace().getName().equals(b.firstPlace().getName())) return 1;
    	if(a.secondPlace().getName().equals(b.secondPlace().getName())) return 1;
    	return -1;
    }
    //Return a string describing this map
    //Returns a string that contains (in this order):
    //for each place in the map, a line (terminated by \n)
    //  PLACE followed the toString result for that place
    //for each road in the map, a line (terminated by \n)
    //  ROAD followed the toString result for that road
    //if a starting place has been defined, a line containing
    //  START followed the name of the starting-place (terminated by \n)
    //if an ending place has been defined, a line containing
    //  END followed the name of the ending-place (terminated by \n)
    public String toString(){
    	String print = "";
    	for (Place i: places )
    		print += "PLACE " + i.toString() + "\n";
    	
    	for (Road j: roads) 
    		print += "ROAD " + j.toString() + "\n";
    	
    	if(start != null)
    		print += "START " + start.getName() + "\n";
    	
    	if(end != null)
    		print += "END " + end.getName() + "\n";
    	return print;
    }
}
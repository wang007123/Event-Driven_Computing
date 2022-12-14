import java.util.*;

public class PlaceImpl implements Place
{
    private int X;
    private int Y;
    private String name;
    private Set<Road> roads;
    public PlaceImpl(String Placename, int xPos , int yPos){
        name = Placename;
        X = xPos;
        Y = yPos;
        roads = new HashSet<Road>();
    }
    //Add the PlaceListener pl to this place. 
    //Note: A place can have multiple listeners
    public void addListener(PlaceListener pl){

    }


    //Delete the PlaceListener pl from this place.
    public void deleteListener(PlaceListener pl){

    }


    //Return a set containing all roads that reach this place
    public Set<Road> toRoads(){
        return roads;
    }


    //Return the road from this place to dest, if it exists
    //Returns null, if it does not
    public Road roadTo(Place dest){
        return null;
    }
    

    //Move the position of this place 
    //by (dx,dy) from its current position
    public void moveBy(int dx, int dy){
        X = X+dx;
        Y = Y+dy;
    }
    

    //Return the name of this place 
    public String getName(){
        return name;
    }
    

    //Return the X position of this place
    public int getX(){
        return X;
    }
    

    //Return the Y position of this place
    public int getY(){
        return Y;
    }


    //Return true if this place is the starting place for a trip
    public boolean isStartPlace(){
        return false;
    }


    //Return true if this place is the ending place for a trip
    public boolean isEndPlace(){
        return false;
    }


    //Return a string containing information about this place 
    //in the form (without the quotes, of course!) :
    //"placeName(xPos,yPos)"  
    public String toString(){
        String print = "";
        print = name + "(" + Integer.toString(X)
        + "," + Integer.toString(Y) + ")";
        return print;
    }
}

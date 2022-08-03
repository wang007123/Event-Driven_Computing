//a1694827 zhenghui wang
import java.util.*;

public class RoadImpl implements Road
{
    private Place from;
    private Place to;
    private String name;
    private int length;
    private RoadListener rl;
    public RoadImpl(Place firstPlace, String roadName, int length, Place secondPlace){
        String first = firstPlace.getName();
        String second = secondPlace.getName();
        if( first.compareTo(second)<0 ){
            from = firstPlace;
            to = secondPlace;
        }else{
            from = secondPlace; 
            to = firstPlace;
        }
        name = roadName;
        this.length = length;
    }
    //Add the RoadListener rl to this place.
    //Note: A road can have multiple listeners
    public void addListener(RoadListener rl){
    	this.rl = rl;
    }


    //Delete the RoadListener rl from this place.
    public void deleteListener(RoadListener rl){
    	this.rl = null;
    }


    //Return the first place of this road
    //Note: The first place of a road is the place whose name
    //comes EARLIER in the alphabet.
    public Place firstPlace(){
        return from;
    }
    

    //Return the second place of this road
    //Note: The second place of a road is the place whose name
    //comes LATER in the alphabet.
    public Place secondPlace(){
        return to;
    }
    

    //Return true if this road is chosen as part of the current trip
    public boolean isChosen(){
        return true;
    }


    //Return the name of this road
    public String roadName(){
        return name;
    }
    

    //Return the length of this road
    public int length(){
        return length;
    }

    
    //Return a string containing information about this road 
    //in the form (without quotes, of course!):
    //"firstPlace(roadName:length)secondPlace"
    public String toString(){
        String print = "";
        print = from.getName() + "(" + name + ":"
            + Integer.toString(length) + ")" + to.getName();
        return print;
    }
}

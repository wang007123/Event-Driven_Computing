import java.text.DecimalFormat;
import java.util.Timer;
import java.text.SimpleDateFormat;
public class EnhancedReactionController implements Controller{
    public Gui gui;
    public Random rng;
    private static boolean coins;
    private static boolean press;
    private static boolean display;
    private static int state;
    private static int game;
    private static int realtime;
    private static int randomTime;
    private static int counterTime;
    private static int displayTime;
    private static int sum;
    private static String infor;
    private static DecimalFormat df2 = new DecimalFormat("0.00");
    private static long millis;
    public EnhancedReactionController(){

    }
    public void setState(){
        switch(state){
            case 0:
                this.init();
                gui.setDisplay("Insert Coin");
                break;
            case 1:
                this.init();
                coins =true;
                state =1;
                gui.setDisplay("Press Go!");
                break;
            case 2:
                realtime = 0;
                press = true;
                displayTime = 300;
                gui.setDisplay("Wait...");
                break;
            case 3:
                realtime = 0;
                gui.setDisplay(df2.format((double)realtime/100));
                break;
            case 4:
                realtime = 0;
                gui.setDisplay(infor);
                break;
            case 5:
                realtime = 0;
                displayTime = 500;
                gui.setDisplay(infor);
            default:
                break;
        }
    }
    //Connect controller to gui
    //(This method will be called before ANY other methods)
    public void connect(Gui gui, Random rng){
        this.gui = gui;
        this.rng = rng;
    }

    //Called to initialise the controller
    public void init(){
        sum = 0;
        coins = false;
        game =0;
        press = false;
        state = 0;
        realtime = 0;
        randomTime = rng.getRandom(100,250);
        counterTime = 200;
        displayTime = 300;
        display = false;
        infor = "";
        gui.setDisplay("Insert coin");
    }

    //Called whenever a coin is inserted into the machine
    public void coinInserted(){
        if(coins == false){
            state =1;
            this.setState();
        }
    }

    //Called whenever the go/stop button is pressed
    public void goStopPressed(){
        switch(state){
            case 0:
                break;
            case 1:
                state = 2;
                this.setState();
                break;
            case 2:
                state =0;
                this.setState();
                break;
            case 3:
                sum += realtime;
                infor = df2.format((double)realtime/100);
                display = true;
                state =4;
                this.setState();
                break;
            case 4:
                state = 0;
                this.setState();
                break;
    }}

    //Called to deliver a TICK to the controller
    public void tick(){
        String tmp;
        realtime++;
        switch(state){
            case 2:
                if(realtime >= randomTime){
                    state =3;
                    realtime = 0;
                    this.setState();
                }
                break;
            case 3:
                if(realtime < counterTime){
                    infor = df2.format((double)realtime/100);
                    gui.setDisplay(infor);
                    System.out.println(infor);
                }else{
                    infor = df2.format((double)200/100);;
                    state =4;
                    realtime = 0;
                    this.setState();
                }
                break;
            case 4:
                if(realtime >= displayTime){
                    game++;
                    if(game == 3){
                        state =5;
                        infor = "Average= "+df2.format((double)sum/300);
                        System.out.println("sum is: "+sum+". time is "+ realtime+", inf = "+infor);
                        this.setState();
                    }else{
                        
                        randomTime = rng.getRandom(100,250);
                        state = 2;
                        this.setState();
                    }
                }
                
                break;
            case 5:
                if(realtime >= displayTime){
                    state =0;
                    this.setState();
                }
                break;
            default:
                // 
        }

    }
}
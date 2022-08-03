interface Controller
{
    //Connect controller to gui
    //(This method will be called before ANY other methods)
    public void connect(Gui gui, Random rng);

    //Called to initialise the controller
    public void init();

    //Called whenever a coin is inserted into the machine
    public void coinInserted();

    //Called whenever the go/stop button is pressed
    public void goStopPressed();

    //Called to deliver a TICK to the controller
    public void tick();
}

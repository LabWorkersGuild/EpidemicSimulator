import javax.swing.*;

public class Engine {

    private Board myBoard;
    private Frame windowFrame;
    private Timer clock;
    private Statistics stats;
    private Dimensions boardDimens;
    private Dimensions tallyDimens;
    private int buffer = 10;  //Buffer between boardPanel and tallyPanel

    public Engine(int numPeople)
    {
        boardDimens = new Dimensions();
        tallyDimens = new Dimensions(boardDimens.xOrigin + boardDimens.xLen + buffer, boardDimens.yOrigin + buffer, boardDimens.xLen/2, boardDimens.yLen);

        myBoard = new Board(boardDimens, numPeople);
        windowFrame = new Frame(myBoard, tallyDimens);

        stats = new Statistics(myBoard, numPeople);

        clock = new Timer(10, windowFrame.getBoardPanel());
        clock.addActionListener(stats);
        //clock.addActionListener(windowFrame.getTallyPanel()); TODO Add this back in
    }

    public static void main(String[] args)
    {
        Engine gameEngine = new Engine(1000);
        gameEngine.clock.start();
    }
}

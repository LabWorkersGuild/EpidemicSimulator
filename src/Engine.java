import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Engine implements ActionListener {

    private Timer clock;
    private GUI gui;
    private Board simBoard;

    public Engine(GUI gui, Disease disease, int numPeople, int baseXLen, int baseYLen)
    {
        this.gui = gui;

        Dimensions boardDimens = new Dimensions(0, 0, baseXLen/2, (baseYLen+50)/2);

        simBoard = new Board(disease, boardDimens, numPeople);

        gui.getBoardPanel().setBoard(simBoard);

        Statistics stats = new Statistics(simBoard, gui, numPeople);

        clock = new Timer(10, gui.getBoardPanel());
        clock.addActionListener(stats);
        clock.addActionListener(this);
    }

   public Timer getClock()
   {
       return clock;
   }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0; i < simBoard.getPList().size(); i++)
        {
            simBoard.getPList().get(i).updateDimens(gui.getBoardRec());
        }
    }
}

package backend.simboard;

import backend.disease.Disease;
import lib.CustomColor;

import java.awt.*;

public class SimBoardQuarOcto extends SimBoardOcto implements Quarantinable {

    private Rectangle quarantine;
    private int timeUntilIsolate;
    private int width, height, x2Origin, y2Origin, x3Origin, x4Origin, quarXOrigin, quarWidth;
    private double quarantineChance;

    /** Creates a SimBoardQuarOcto (8 areas, quarantine board) to simulate the actions of people that are displayed on SimBoardPanel
     *
     * @param disease The disease object used for a simulation
     * @param dimens The rectangle that represents the area a person can move in
     * @param numPeople The total number of people in the simulation
     * @param asymptomaticChance The percent of people who are asymptomatic
     * @param socialDistanceValue The number of pixels each person tries to stay apart by when social distancing is enabled
     * @param socialDistanceChance The percent of people who participate in social distancing
     * @param minAge The minimum age of a person in the simulation
     * @param maxAge The maximum age of a person in the simulation
     * @param minPreExistingConditions The minimum pre-existing conditions of a person in the simulation
     * @param maxPreExistingConditions The maximum pre-existing conditions of a person in the simulation
     * @param travelersPer The percent of people who travel without bound in a quad or octo board
     */
    public SimBoardQuarOcto(Disease disease, Rectangle dimens, int numPeople, double asymptomaticChance, int socialDistanceValue, double socialDistanceChance,
                            int minAge, int maxAge, int minPreExistingConditions, int maxPreExistingConditions, double travelersPer, int timeUntilIsolate, double quarantineChance)
    {
        super(disease, dimens, numPeople, asymptomaticChance, socialDistanceValue, socialDistanceChance, minAge, maxAge, minPreExistingConditions, maxPreExistingConditions, travelersPer);
        this.timeUntilIsolate = timeUntilIsolate;
        this.quarantineChance = quarantineChance;
    }

    /** Creates an ArrayList of rectangles that represent dimensions of different parts of the sim board (including the quarantine zone)
     *
     * @param updatedRect The current rectangle for the bounds of the SimBoardPanel
     */
    @Override
    public void updateDimensList(Rectangle updatedRect)
    {
        int xShift = 60, yShift = 30;

        setDimens(updatedRect);

        width = getDimensList().get(0).width / 4 - xShift;
        height = getDimensList().get(0).height / 2 - yShift;

        x2Origin = getDimensList().get(0).x + width + xShift / 4;
        y2Origin = height + 2*yShift;

        x3Origin = getDimensList().get(0).x + 2* width + 2* xShift / 4;

        x4Origin = getDimensList().get(0).x + 3* width + 3* xShift / 4;

        quarXOrigin = x4Origin + width + xShift / 2;
        quarWidth = getDimensList().get(0).width - quarXOrigin;

        quarantine = new Rectangle(quarXOrigin, getDimens().y, quarWidth, getDimens().height);

        getDimensList().get(1).setRect(getDimens().x, getDimens().y, width, height);
        getDimensList().get(2).setRect(x2Origin, getDimens().y, width, height);
        getDimensList().get(3).setRect(x3Origin, getDimens().y, width, height);
        getDimensList().get(4).setRect(x4Origin, getDimens().y, width, height);
        getDimensList().get(5).setRect(getDimens().x, y2Origin, width, height);
        getDimensList().get(6).setRect(x2Origin, y2Origin, width, height);
        getDimensList().get(7).setRect(x3Origin, y2Origin, width, height);
        getDimensList().get(8).setRect(x4Origin, y2Origin, width, height);
        getDimensList().get(9).setRect(getDimens().x, getDimens().y, getDimens().x + x4Origin + width, getDimens().height);
    }

    /**
     * Checks if a sick person needs to go into quarantine if they participate. Then checks if they recover and returns them back to their section of the board
     */
    public void quarantineCheck()
    {
        for(int i = 0; i < getPList().size(); i++)
        {
            if(getPList().get(i).getHasDisease() && !getPList().get(i).isAsymptomatic() && !getPList().get(i).getIsHealthy() && getPList().get(i).getTimeSinceSick() > timeUntilIsolate && !getPList().get(i).isIsoSick())
            {
                if(Math.random() < quarantineChance)
                {
                    getPList().get(i).resetDimens(quarantine);
                    getPList().get(i).setIsoSick(true);
                }
            }
            if(getPList().get(i).getHasDisease() && getPList().get(i).getIsHealthy() && !getPList().get(i).isIsoRecovered())
            {
                getPList().get(i).resetDimens(getDimensList().get(getPList().get(i).getQuadLocation()));
                getPList().get(i).setIsoRecovered(true);
                getPList().get(i).setIsoSick(false);
            }
        }
    }

    /**
     * Draws a line to mark of the quarantine zone
     * @param g2D graphics object used to draw the line
     */
    @Override
    public void drawQuarLine(Graphics2D g2D) {

        int segmentWidth = 4;
        int xBuffer = 12;
        int segmentLen = getDimens().height/15;

        g2D.setColor(CustomColor.EERIE_BLACK);

        for(int i = 0; i < 17; i++)
        {
            if(i%2 == 0)
                g2D.fillRect(quarantine.x - xBuffer, quarantine.y + i*segmentLen, segmentWidth, segmentLen );
        }
    }
}

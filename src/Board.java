import java.util.ArrayList;

public class Board {

   BoardDimensions boardDimensions;
   private ArrayList<Person> pList;


    /** Primary Board constructor. A Board filled with clones of the modelPerson will be created.
     *   Person objects are given random positions inside the board grid.
     * @param personModel
     * @param numPeople
     */

    public Board(BoardDimensions boardDimensions, Person personModel, int numPeople)
    {
        this.boardDimensions = boardDimensions;

        pList = new ArrayList<>();
        for(int i = 0; i < numPeople; i++)
        {
            int xPos = boardDimensions.xOrigin + (int)(boardDimensions.xLen*Math.random());
            int yPos = boardDimensions.yOrigin + (int)(boardDimensions.yLen*Math.random());

            pList.add(new Person(personModel, xPos, yPos));
        }

    }

    /** Secondary Board constructor. A Board filled with an already created ArrayList of Persons will  will be created.
     * @param pList
     *
     */
    public Board(BoardDimensions boardDimensions, ArrayList<Person> pList)
    {
        this.boardDimensions = boardDimensions;
        this.pList = pList;
    }

    /**Default board with hardcoded parameters and default BoardDimensions constructed object */
    public Board()
    {
      this(new BoardDimensions(), new Person(20, false, 1, 1), 100);
    }



}

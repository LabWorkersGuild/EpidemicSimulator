public class Person {

    private boolean hasDisease, isHealthy;
    private int age, personalSpreadRate, xPos, yPos, direction;
    private double changeOfDirectionChange;

    public Person(int age, boolean hasDisease, int xPos, int yPos)
    {
        this.age = age;
        this.xPos = xPos;
        this.yPos = yPos;
        // All people start healthy but with a change of having the disease based on this parameter
        this.hasDisease = hasDisease;

        // If this person is starting with the disease, then they also start as sick
        if(hasDisease)
            isHealthy = false;
        personalSpreadRate = 1;
    }

    public void updatePersonalSpreadRate()
    {
        personalSpreadRate *= 0.1*age;
    }

    public Person(Person testSubject)
    {
        this.age = testSubject.age;
        this.xPos = testSubject.xPos;
        this.yPos = testSubject.yPos;
        this.hasDisease = testSubject.hasDisease;
        this.direction = testSubject.direction;
        this.isHealthy = testSubject.isHealthy;
        this.changeOfDirectionChange = testSubject.changeOfDirectionChange;
        this.personalSpreadRate = testSubject.personalSpreadRate;
    }

    public void movePerson()
    {

    }
}

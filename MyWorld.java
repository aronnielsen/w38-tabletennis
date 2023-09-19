import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int level = 1;
    private Level levelCounter;
    private Ball ballRef;
    private Score scoreCounter;
    
    private int playerScore = 0;
    private int computerScore = 0;
    
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
        
        levelCounter.updateText("Game Level: " + Integer.toString(level));
    }
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 600, 1, false); 
        prepare();
    }
    
    public Ball getBallRef() {
        return ballRef;
    }
    
    public void giveComputerPoint() {
        computerScore++;
        scoreCounter.updateScore(playerScore, computerScore, getWidth());
    }
    
    public void givePlayerPoint() {
        playerScore++;
        scoreCounter.updateScore(playerScore, computerScore, getWidth());
    }
    
    public void prepare() {
        ballRef = new Ball();
        addObject(ballRef, getWidth()/2, getHeight()/2);
        addObject(new Paddle(5, true), getWidth()/2, getHeight() - 50);
        
        addObject(new Paddle(2, false), getWidth()/2, 50);
        
        levelCounter = new Level("Game Level: 1");
        addObject(levelCounter, 62, 35);
        
        scoreCounter = new Score(0, 0, getWidth());
        addObject(scoreCounter, getWidth()/2, getHeight()/2);
        
        setPaintOrder(Ball.class);
    }
}

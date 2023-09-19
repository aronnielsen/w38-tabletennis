import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Paddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paddle extends Actor
{
    private int width = 100;
    private int height = 25;
    
    private int moveSpeed = 5;
    
    private boolean playerPaddle = false;

    private Ball ballRef = null;
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (playerPaddle) {
            playerInput();
            playerCollision();
        } else {
            simpleComputerInput();
            //advancedComputerInput();
        }
    }
    
    public void advancedComputerInput() {
        if (ballRef == null) {
            ballRef = getWorldOfType(MyWorld.class).getBallRef();
        } else {
            if (ballRef.wasPlayerPaddleLastHit()) {
                if (getX() < ballRef.getX()) {
                    setLocation(getX() + moveSpeed, getY());
                } else if (getX() > ballRef.getX()) {
                    setLocation(getX() - moveSpeed, getY());
                }
            } else {
                if (getX() < getWorld().getWidth()/2) {
                    setLocation(getX() + moveSpeed, getY());
                } else if (getX() > getWorld().getWidth()/2) {
                    setLocation(getX() - moveSpeed, getY());
                }
            }
        }
    }
    
    public void simpleComputerInput() {
        setLocation(getX() + moveSpeed, getY());
        
        if (isAtEdge() && getX() > getWorld().getWidth() + width/2) {
            Random random = new Random();
            setLocation(-width/2, random.nextInt(100) + 50);
        }
    }
    
    public boolean getPlayer() {
        return playerPaddle;
    }
    
    public Paddle(int moveSpeed, boolean playerPaddle) {
        this.moveSpeed = moveSpeed;
        this.playerPaddle = playerPaddle;
        
        Random random = new Random();
        
        GreenfootImage newImage = new GreenfootImage(random.nextInt(100)+50, random.nextInt(10)+15);
        newImage.setColor(new Color(0, 0, 0));
        newImage.fill();
        
        setImage(newImage);
    }
    
    private int abs(int value) {
        return (value < 0) ? value * -1 : value;
    }
    
    private void playerInput() {
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            setLocation(getX() - moveSpeed, getY());
        } else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            setLocation(getX() + moveSpeed, getY());
        }
    }
    
    private void playerCollision() {
        if (getX() + width/2 > getWorld().getWidth()) {
            setLocation(getWorld().getWidth() - width/2, getY());
        } else if (getX() - width/2 < 0) {
            setLocation(width/2, getY());
        }
    }
}

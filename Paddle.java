import greenfoot.*;

public class Paddle extends Actor
{
    private int width = 100;
    private int height = 25;
    private int moveSpeed = 5;
    
    private boolean playerPaddle = false;
    private Ball ballRef = null;
    
    public Paddle(int moveSpeed, boolean playerPaddle) {
        this.moveSpeed = moveSpeed;
        this.playerPaddle = playerPaddle;
                
        GreenfootImage newImage = new GreenfootImage(Greenfoot.getRandomNumber(100)+50, Greenfoot.getRandomNumber(10)+15);
        newImage.setColor(new Color(0, 0, 0));
        newImage.fill();
        
        setImage(newImage);
    }
    
    public boolean getPlayer() {
        return playerPaddle;
    }
    
    public void act()
    {
        if (playerPaddle) {
            playerInput();
            playerCollision();
        } else {
            //simpleComputerInput();
            advancedComputerInput();
        }
    }
    
    private void simpleComputerInput() {
        setLocation(getX() + moveSpeed, getY());
        
        if (isAtEdge() && getX() > getWorld().getWidth() + width/2) {
            setLocation(-width/2, Greenfoot.getRandomNumber(100) + 50);
        }
    }
    
    private void advancedComputerInput() {
        if (ballRef == null) {
            ballRef = getWorldOfType(MyWorld.class).getBallRef();
        } else if (ballRef.wasPlayerPaddleLastHit()) {
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
    
    private int abs(int value) {
        return (value < 0) ? value * -1 : value;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{    
    private final boolean SIMPLE_MODE = false;
    private boolean reset = true;
    
    private int moveSpeed = 3;
    private int hitCount = 0;
    private int level = 1;
    private int size = 25;
    
    private Paddle lastHitPaddle = null;
    private boolean playerPaddleLastHit = false;
    
    public Ball() {
        GreenfootImage newImage = new GreenfootImage(25, 25);
        
        newImage.setColor(new Color(0, 0, 0));
        newImage.fillOval(0,0,25,25);
        
        setImage(newImage);
    }
    
    private void 
    
    public void act()
    {
        if (reset) {
            if (Greenfoot.isKeyDown("space")) {
                restartBall();
            }
        } else {
            move(moveSpeed + level);
        
            if (isAtEdge()) {
                if (SIMPLE_MODE) {
                    if (getY() > getWorld().getHeight() - size) {
                        reflectVertical();
                    } else if (getY() < size) {
                        reflectVertical();
                    }
                } else {
                    if (getY() > getWorld().getHeight() - size) {
                        scoredPoint(false);
                    } else if (getY() < size) {
                        scoredPoint(true);
                    }
                }
            }
            
            if (isAtEdge() && (getX() > getWorld().getWidth() - 25 || getX() < 25)) {
                reflectHorizontal();
            }
            
            checkPaddleCollision();
        }
    }
    
    public boolean wasPlayerPaddleLastHit() {
        return playerPaddleLastHit;
    }
    
    private void scoredPoint(boolean playerScored) {
        if (playerScored) {
            getWorldOfType(MyWorld.class).givePlayerPoint();
        } else {
            getWorldOfType(MyWorld.class).giveComputerPoint();
        }
        
        setLocation(getWorld().getWidth()/2 - size/2, getWorld().getHeight()/2);
        reset = true;
    }
    
    public void restartBall() {
        reset = false;
        Random random = new Random();
        setRotation(random.nextInt(90) + 45);
    }
        
    private void checkPaddleCollision() {
        Paddle paddle = (Paddle)getOneIntersectingObject(Paddle.class);
        
        if (paddle != null && (lastHitPaddle == null || !lastHitPaddle.equals(paddle))) {
            hitCount++;
            
            if (hitCount == 10) {
                hitCount = 0;
                level++;
                MyWorld world = getWorldOfType(MyWorld.class);
                
                world.setLevel(world.getLevel() + 1);
                level = world.getLevel();
            }
            
            if (paddle.getPlayer() && getY() < paddle.getY()) {
                reflectVertical(paddle.getX() - getX());
            } else if (!paddle.getPlayer() && getY() > paddle.getY()) {
                reflectVertical(paddle.getX() - getX());
            }
            
            lastHitPaddle = paddle;
            playerPaddleLastHit = paddle.getPlayer();
        } else if (paddle == null) {
            lastHitPaddle = null;
        }
    }
    
    public void reflectVertical() {
        setRotation(getRotation() * -1);
    }
    
    public void reflectVertical(int offset) {
        setRotation((getRotation() + offset) * -1);
    }
    
    public void reflectHorizontal() {
        setRotation((getRotation() + 180) * -1);
    }
}

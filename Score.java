import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    private int playerScore = 0;
    private int computerScore = 0;
    
    public Score(int playerScore, int computerScore, int width) {
        this.playerScore = playerScore;
        this.computerScore = computerScore;
        
        GreenfootImage newImage = new GreenfootImage(width, 100);
        
        newImage.setColor(new Color(255, 255, 255));
        newImage.fillRect(0, 47, width, 6);
        
        newImage.setColor(new Color(0, 0, 0));
        newImage.setFont(new Font(true, false, 24));
        
        newImage.drawString(Integer.toString(computerScore), 10, 35);
        newImage.drawString(Integer.toString(playerScore), 10, 85);
        
        setImage(newImage);
    }
    
    public void updateScore(int playerScore, int computerScore, int width) {
        GreenfootImage newImage = new GreenfootImage(width, 100);
        
        newImage.setColor(new Color(255, 255, 255));
        newImage.fillRect(0, 47, width, 6);
        
        newImage.setColor(new Color(0, 0, 0));
        newImage.setFont(new Font(true, false, 24));
        
        newImage.drawString(Integer.toString(computerScore), 10, 35);
        newImage.drawString(Integer.toString(playerScore), 10, 85);
        
        setImage(newImage);
    }
}

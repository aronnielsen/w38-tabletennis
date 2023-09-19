import greenfoot.*;

public class Level extends Actor
{    
    public Level(String text) {        
        GreenfootImage newImage = new GreenfootImage(125, 50);
        newImage.setColor(new Color(0, 0, 0));
        newImage.setFont(new Font(true, false, 12));
        newImage.drawString(text, 10, 10);
        
        setImage(newImage);
    }
    
    public void updateText(String text) {        
        GreenfootImage newImage = new GreenfootImage(125, 50);
        newImage.setColor(new Color(0, 0, 0));
        newImage.setFont(new Font(true, false, 12));
        newImage.drawString(text, 10, 10);
        
        setImage(newImage);
    }
}

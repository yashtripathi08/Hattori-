package tiles_interactive;

import entity.entity;
import main.gamepanel;

public class InteractiveTile extends entity{
    gamepanel gp;
    public boolean destrutable = false;
    public InteractiveTile(gamepanel gp, int col,int row) {
        super(gp);
        this.gp = gp;
    }
    public boolean isCorrectItem(entity entity){
        boolean isCorrectItem=false;
        return isCorrectItem;
    }
    public void playSE(){

    }
    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile=null;
        return tile;
    }
    public void update(){
        if(invincible==true){
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }
    
}

package object;

import entity.entity;
import main.gamepanel;

public class OBJ_Coin_Bronze  extends entity {
    gamepanel gp;
    public OBJ_Coin_Bronze(gamepanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name = "coin_bronze";
        value = 1;
        down1=setup("/res/objects/coin_bronze",gp.tileSize,gp.tileSize);

        
    } 
    public boolean use (entity entity){
       
        gp.playSE(1);
        gp.ui.addMessage("Coin+"+value);
        gp.player.coin+=value;
        return true;
       }
 
    
}

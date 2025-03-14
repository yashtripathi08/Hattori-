package object;

import entity.entity;
import main.gamepanel;

public class OBJ_Tent extends entity {

    gamepanel gp;
    public OBJ_Tent(gamepanel gp) {
        super(gp);

        this.gp =gp;
        type= type_consumable;
        name="Tent";
        down1 =setup("/res/objects/tent",gp.tileSize,gp.tileSize);
        price =300;
        stackable=true;

    }
    public boolean use(entity entity){

        gp.gameState=gp.sleepState;
        gp.playSE(14);
        gp.player.life=gp.player.maxLife;
        gp.player.mana=gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true;
    }
    
}

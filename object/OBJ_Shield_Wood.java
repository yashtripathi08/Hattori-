package object;

import entity.entity;
import main.gamepanel;

public class OBJ_Shield_Wood  extends entity{

    public OBJ_Shield_Wood(gamepanel gp) {
        super(gp);
        name ="Wood Shield";
        down1 = setup("/res/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue=4;
    }
    
}
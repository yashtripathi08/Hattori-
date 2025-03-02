package object;

import entity.entity;
import main.gamepanel;

public class OBJ_Sword_Normal  extends entity{

    public OBJ_Sword_Normal(gamepanel gp) {
        super(gp);
        name ="Normal Sword";
        down1 = setup("/res/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue=1;
        description ="["+name+ "]\nAn old sword.";
    }
    
}

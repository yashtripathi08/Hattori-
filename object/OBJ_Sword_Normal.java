package object;

import Main.gamepanel;
import entity.entity;

public class OBJ_Sword_Normal  extends entity{

    public OBJ_Sword_Normal(gamepanel gp) {
        super(gp);
        type = type_sword;
        name ="Normal Sword";
        down1 = setup("/res/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue=1;
        attackArea.width = 36;
        attackArea.height = 36;
        description ="["+name+ "]\nAn old sword.";
    }
    
}

package object;

import entity.entity;
import main.gamepanel;

public class OBJ_Lantern  extends entity{

    public OBJ_Lantern(gamepanel gp) {
        super(gp);

        type =type_light;
        name= "Lantern";
        down1=setup("/res/objects/lantern", gp.tileSize, gp.tileSize);
        description="[Lantern]\nIlluminates your \nSurroundings.";
        price =200;
        lightRadius=250;
    }
    
    
}

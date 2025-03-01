package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.entity;
import main.gamepanel;

public class OBJ_Heart  extends entity{
    gamepanel gp;

    public OBJ_Heart(gamepanel gp){
        super(gp);
        name ="Heart";
        image=setup("/res/objects/heart_full",gp.tileSize,gp.tileSize);
        image2=setup("/res/objects/heart_half",gp.tileSize,gp.tileSize);
        image3=setup("/res/objects/heart_blank",gp.tileSize,gp.tileSize);

    
   }

}

package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.entity;
import main.gamepanel;


public class OBJ_Chest extends entity{
 
   public OBJ_Chest(gamepanel gp){
      super(gp);
      name ="chest";
      down1=setup("/res/objects/chest",gp.tileSize,gp.tileSize);

    
    
   }
}

package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.entity;
import main.gamepanel;


public class OBJ_Key extends entity{
 
   public OBJ_Key(gamepanel gp){
      super(gp);
      name ="Key";
      down1=setup("/res/objects/Key",gp.tileSize,gp.tileSize);

    
    
   }
}

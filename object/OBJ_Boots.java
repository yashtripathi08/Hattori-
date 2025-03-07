package object;

import Main.gamepanel;

//import java.io.IOException;

//import javax.imageio.ImageIO;

import entity.entity;


public class OBJ_Boots extends entity{
 
   public OBJ_Boots(gamepanel gp){
      super(gp);
      name ="Boots";
      down1=setup("/res/objects/Boots",gp.tileSize,gp.tileSize);

    
    
   }
}

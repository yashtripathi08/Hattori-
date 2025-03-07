package object;

import Main.gamepanel;

//import java.io.IOException;

//import javax.imageio.ImageIO;

import entity.entity;


public class OBJ_Key extends entity{
 
   public OBJ_Key(gamepanel gp){
      super(gp);
      name ="Key";
      down1=setup("/res/objects/Key",gp.tileSize,gp.tileSize);
      description ="["+name+ "]\nIt opens a door.";

    
    
   }
}

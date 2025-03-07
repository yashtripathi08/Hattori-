package object;

import Main.gamepanel;

//import java.io.IOException;

//mport javax.imageio.ImageIO;

import entity.entity;


public class OBJ_Door extends entity{
 
   public OBJ_Door(gamepanel gp){
      super(gp);
      name ="Door";
      down1=setup("/res/objects/door",gp.tileSize,gp.tileSize);
      collision=true;

    
    
   }
}

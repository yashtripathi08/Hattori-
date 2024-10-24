package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.entity;
import main.gamepanel;


public class OBJ_Door extends entity{
 
   public OBJ_Door(gamepanel gp){
      super(gp);
    name ="Door";
down1=setup("/res/objects/door",gp.tileSize,gp.tileSize);
collision=true;

    
    
   }
}

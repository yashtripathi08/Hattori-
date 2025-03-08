package object;

import entity.entity;
import main.gamepanel;

public class OBJ_shield_blue extends entity{
  
   public OBJ_shield_blue(gamepanel gp){
      super(gp);
      type = type_shield;
      name ="Blue Shield";
      down1=setup("/res/objects/shield_blue",gp.tileSize,gp.tileSize);
      defenseValue=2;
      description ="["+name+ "]\n A shiny blue shield.";
      price =250;

     
     
   }

}

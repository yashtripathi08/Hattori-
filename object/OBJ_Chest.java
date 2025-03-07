package object;


import Main.gamepanel;
import entity.entity;


public class OBJ_Chest extends entity{
 
   public OBJ_Chest(gamepanel gp){
      super(gp);
      name ="chest";
      down1=setup("/res/objects/chest",gp.tileSize,gp.tileSize);

   }
}

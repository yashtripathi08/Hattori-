package object;

import entity.entity;
import main.gamepanel;

public class OBJ_Axe extends entity{
  
   public OBJ_Axe(gamepanel gp){
      super(gp);
      type = type_axe;
      name ="Woodcutter's Axe";
      down1=setup("/res/objects/Axe",gp.tileSize,gp.tileSize);
      attackValue=2;
      attackArea.width = 30;
      attackArea.height = 30;
      description ="["+name+ "]\nIt can cut trees.";
     
     
   }
    
}

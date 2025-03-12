package object;

//import java.io.IOException;

//import javax.imageio.ImageIO;

import entity.entity;
import main.gamepanel;


public class OBJ_Key extends entity{
 
   gamepanel gp;
   public OBJ_Key(gamepanel gp){
      super(gp);
      this.gp =gp;
      type =type_consumable;
      name ="Key";
      down1=setup("/res/objects/Key",gp.tileSize,gp.tileSize);
      description ="["+name+ "]\nIt opens a door.";

      price =100;
    
   }
   public boolean use(entity entity){
      gp.gameState=gp.dialogueState;

      int objIndex =getDetected(entity,gp.obj, "Door");
      if(objIndex!=999){
         gp.ui.currentDialogue ="You used the "+name+"and opened the door.";
         gp.playSE(3);
         gp.obj[gp.currentMap][objIndex] =null;
         return true;

      }
      else{
         gp.ui.currentDialogue ="What are you doing?";
         return false;

      }

   }
}

package object;

//import java.io.IOException;

//mport javax.imageio.ImageIO;

import entity.entity;
import main.gamepanel;


public class OBJ_Door extends entity{
 
   gamepanel gp;

   public OBJ_Door(gamepanel gp){
      super(gp);
      this.gp =gp;

      type =type_obstacle;
      name ="Door";
      down1=setup("/res/objects/door",gp.tileSize,gp.tileSize);
      collision=true;

    
      solidArea.x =0;
      solidArea.y =16;
      solidArea.width =40;
      solidArea.height=32;
      solidAreaDefaultX =solidArea.x;
      solidAreaDefaultY =solidArea.y;
    
   }
   public void interact(){
      gp.gameState =gp.dialogueState;
      gp.ui.currentDialogue ="You need a key to open this.";

   }
}

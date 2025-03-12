package object;


//import javax.swing.text.html.parser.Entity;

import entity.entity;
import main.gamepanel;


public class OBJ_Chest extends entity{
 
   gamepanel gp;
   entity loot;
   boolean opened =false;

   public OBJ_Chest(gamepanel gp,entity loot){
      super(gp);
      this.gp =gp;
      this .loot =loot;
      type =type_obstacle;
      name ="Chest";
      

      image=setup("/res/objects/chest",gp.tileSize,gp.tileSize);
      image2=setup("/res/objects/chest_opened",gp.tileSize,gp.tileSize);

      down1= image;
      collision=true;
      solidArea.x=4;
      solidArea.y=16;
      solidArea.width =40;
      solidArea.height=32;
      solidAreaDefaultX=solidArea.x;
      solidAreaDefaultY=solidArea.y;
   }
   public void interact(){
      gp.gameState=gp.dialogueState;
      if(opened==false){
         gp.playSE(3);

         StringBuilder sb =new StringBuilder();
         sb.append("You open the chest and find a"+loot.name+"!");

         if(gp.player.inventory.size()==gp.player.maxInventorySize){
            sb.append("\n...But you cannot carry any more!");

         }
         else{
            sb.append("\nYu obtained the"+loot.name+"!");
            gp.player.inventory.add(loot);
            down1 =image2;
            opened =true;

         }
         gp.ui.currentDialogue=sb.toString();

      }
      else{
         gp.ui.currentDialogue ="It's empty!";
      }
   }
}

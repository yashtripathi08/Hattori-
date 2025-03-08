package object;

import entity.entity;
import main.gamepanel;

public class OBJ_Potion_Red extends entity{
    gamepanel gp;
   
  
   public OBJ_Potion_Red(gamepanel gp){
      super(gp);
      this.gp=gp;
      type = type_consumable;
      name ="Red Potion";
      value =5;
      down1=setup("/res/objects/potion_red",gp.tileSize,gp.tileSize);
      defenseValue=2;
      description ="["+name+ "]\n Heals your life by"+value+".";
      price =25;
   }
   public void use (entity entity){
    gp.gameState =gp.dialogueState;
    gp.ui.currentDialogue = "You used the "+name+"!\n"+"Your life has been recovered by "+value+".";
    entity.life +=value;
   
    gp.playSE(2);
   }

}

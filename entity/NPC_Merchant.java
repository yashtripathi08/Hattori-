package entity;

//import java.util.Random;

import main.gamepanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_shield_blue;

public class NPC_Merchant  extends entity{

    public NPC_Merchant(gamepanel gp) {
        super(gp);
        type=1;
        direction ="down";
        speed =0;
       
       getImage();
       setDialogue();
       setItems();
    
        
    }
    public void getImage() {
       

        up1 =setup("/res/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        up2 =setup("/res/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        down1 =setup("/res/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        down2=setup("/res/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        left1 =setup("/res/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        left2 =setup("/res/npc/merchant_down_2",gp.tileSize,gp.tileSize);
        right1 =setup("/res/npc/merchant_down_1",gp.tileSize,gp.tileSize);
        right2 =setup("/res/npc/merchant_down_2",gp.tileSize,gp.tileSize);
    }

    public void setDialogue(){
        dialogues[0]="He he, so you found me.\nI have some good stuff\ndo you want to trade?";
      
    }
    public void setItems(){
        inventory .add(new OBJ_Potion_Red(gp));
        inventory .add(new OBJ_Key(gp));
        inventory .add(new OBJ_Sword_Normal(gp));
        inventory .add(new OBJ_Shield_Wood(gp));
        inventory .add(new OBJ_Axe(gp));
        inventory .add(new OBJ_shield_blue(gp));

    }
    public void speak(){
        super .speak();
        gp.gameState=gp.tradeState;
        gp.ui.npc=this;
    }
}
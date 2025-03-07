package object;

import Main.gamepanel;

//import java.io.IOException;

//import javax.imageio.ImageIO;

import entity.entity;

public class OBJ_Heart  extends entity{
    gamepanel gp;

    public OBJ_Heart(gamepanel gp){
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name ="Heart";
        value = 2;
        down1=setup("/res/objects/heart_full",gp.tileSize,gp.tileSize);
        image=setup("/res/objects/heart_full",gp.tileSize,gp.tileSize);
        image2=setup("/res/objects/heart_half",gp.tileSize,gp.tileSize);
        image3=setup("/res/objects/heart_blank",gp.tileSize,gp.tileSize);

    
   }
   public void use(entity entity){
       gp.playSE(1);
       gp.ui.addMessage("Life +"+value);
       entity.life+=value;
      
   }

}

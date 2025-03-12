package object;
import entity.entity;
import main.gamepanel;
public class OBJ_ManaCrystal extends entity{
    gamepanel gp;
    public OBJ_ManaCrystal(gamepanel Gp){
        super (Gp);
        this.gp = Gp;
        type = type_pickupOnly;
        name ="Mana Crystal";
        value=1;
        down1 =setup("/res/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image =setup("/res/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2
         =setup("/res/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
}
public boolean use(entity entity){
    gp.playSE(1);
    gp.ui.addMessage("Mana +"+value);
    entity.mana+=value;
   return true;
}
}
package object;
import entity.entity;
import main.gamepanel;
public class OBJ_ManaCrystal extends entity{
    gamepanel gp;
    public OBJ_ManaCrystal(gamepanel Gp){
        super (Gp);
        this.gp = Gp;
        name ="Mana Crystal";
        image =setup("/res/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2
         =setup("/res/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
}
}
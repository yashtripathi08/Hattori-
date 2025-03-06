package object;

import entity.Projectile;
import entity.entity;
import main.gamepanel;

public class OBJ_Rock extends Projectile {
    gamepanel gp;
    public OBJ_Rock(gamepanel gp) {
        super(gp);
        this.gp = gp;
        name = "Rock";
        speed = 8;
        maxLife=80;
        life=maxLife;
        attack=2;
        useCost=1;
        alive=false;
        getImage();
    } 
    public void getImage() {
        up1 =setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        up2 =setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down1 =setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down2 =setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left1 =setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left2 =setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right1 =setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right2 =setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);

    }
        public boolean haveResource(entity user){
        boolean haveResource =false;;
        if(user.ammo>=useCost){
            haveResource=true;
            
        }
        return haveResource;
            
    } 
    public void subtractResource(entity user){
        user.ammo-=useCost;
    }
}

    


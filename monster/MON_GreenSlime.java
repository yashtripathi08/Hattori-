package monster;

import java.util.Random;

import entity.entity;
import main.gamepanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_GreenSlime extends entity {
    gamepanel gp;

    public MON_GreenSlime(gamepanel gp) {
        super(gp);
        this.gp =gp;
        type = type_monster;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack =2;
        defense =0;
        exp=2;
        projectile =new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {
        up1 = setup("/res/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        actionLookCounter++;
        if (actionLookCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLookCounter = 0;
           
        }
        int i=new Random().nextInt(100)+1;
        if(i<99&&projectile.alive==false&&shotAvailableCounter==30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter=0;
        }
     
    }
    public void damageReaction() {
        actionLookCounter = 0;
        direction = gp.player.direction;
    }
    public void checkDrop(){
        int i=new Random().nextInt(100)+1;

        if(i<=50){
           dropItem(new OBJ_Coin_Bronze(gp));
        }
        else if(i>50&&i<=75){
            dropItem(new OBJ_Heart(gp));
        }
        else if(i>75&&i<=100){
           dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}

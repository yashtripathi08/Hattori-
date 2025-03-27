package monster;

import java.util.Random;

import entity.entity;
import entity.player;
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
        defaultSpeed=1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack =1;
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

        if(onPath==true){


            checkStopChasingOrNot(gp.player, 15, 100);
            

            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            checkShootOrNot(200, 30);
        }
        else{
            checkStartChasingOrNot(gp.player, 5, 100);
            
            getRandomDirection();
        }
        
    }
    public void damageReaction() {
        actionLookCounter = 0;
       // direction = gp.player.direction;
       onPath=true;
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

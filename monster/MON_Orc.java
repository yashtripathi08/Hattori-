package monster;

import java.util.Random;

import entity.entity;
import main.gamepanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_Orc extends entity {
 gamepanel gp;

    public MON_Orc(gamepanel gp) {
        super(gp);
        this.gp =gp;
        type = type_monster;
        name = "Orc";
        defaultSpeed=1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack =8;
        defense =2;
        exp=10;
        //projectile =new OBJ_Rock(gp);

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/res/monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/orc_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/orc_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/orc_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/orc_right_2", gp.tileSize, gp.tileSize);
    }

    public void getAttackImage(){

        attackUp1 = setup("/res/monster/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/res/monster/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/res/monster/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/res/monster/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/res/monster/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/res/monster/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/res/monster/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/res/monster/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void setAction() {

        if(onPath==true){


            checkStopChasingOrNot(gp.player, 15, 100);
            

            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

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

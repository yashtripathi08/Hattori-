package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.UtilityTool;
import main.gamepanel;
import main.keyHandler;

public class player extends entity {

  
    keyHandler keyH;
    public final int screenX;
    public final int screenY;
 

    public player(gamepanel gp, keyHandler keyH) {

        super(gp);
        
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea=new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        attackArea.width=36;
        attackArea.height=36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttckImage();
    }

    public void setDefaultValues() {


        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
         

        maxLife=6;
        life=maxLife;
    
    }

    public void getPlayerImage() {
       

        up1 =setup("/res/boy_up_1",gp.tileSize,gp.tileSize);
        up2 =setup("/res/boy_up_2",gp.tileSize,gp.tileSize);
        down1 =setup("/res/boy_down_1",gp.tileSize,gp.tileSize);
        down2=setup("/res/boy_down_2",gp.tileSize,gp.tileSize);
        left1 =setup("/res/boy_left_1",gp.tileSize,gp.tileSize);
        left2 =setup("/res/boy_left_2",gp.tileSize,gp.tileSize);
        right1 =setup("/res/boy_right_1",gp.tileSize,gp.tileSize);
        right2 =setup("/res/boy_right_2",gp.tileSize,gp.tileSize);
    }

    public void getPlayerAttckImage(){
        attackUp1 =setup("/res/boy_attack_up_1",gp.tileSize,gp.tileSize*2);
        attackUp2 =setup("/res/boy_attack_up_2",gp.tileSize,gp.tileSize*2);
        attackDown1 =setup("/res/boy_attack_down_1",gp.tileSize,gp.tileSize*2);
        attackDown2 =setup("/res/boy_attack_down_2",gp.tileSize,gp.tileSize*2);
        attackLeft1 =setup("/res/boy_attack_left_1",gp.tileSize*2,gp.tileSize);
        attackLeft2 =setup("/res/boy_attack_left_2",gp.tileSize*2,gp.tileSize);
        attackRight1 =setup("/res/boy_attack_right_1",gp.tileSize*2,gp.tileSize);
        attackRight1 =setup("/res/boy_attack_right_2",gp.tileSize*2,gp.tileSize);
    }

    public void update() {
        if(attacking==true){

            attacking();
        }
        else if (keyH.upPressed==true || keyH.downPressed==true || keyH.leftPressed ==true|| keyH.rightPressed==true|| keyH.enterPressed==true) {
            if (keyH.upPressed==true) {
                direction = "up";
           
            }
            if (keyH.downPressed==true) {
                direction = "down";
                
            }
            if (keyH.leftPressed==true) {
                direction = "left";
              
            }
            if (keyH.rightPressed==true) {
                direction = "right";
                
            }




               collisionOn=false;
               gp.cChecker.checkTile(this);


               int objIndex =gp.cChecker.checkObject(this,true);
pickUpObject(objIndex);

int npcIndex=gp.cChecker.checkEntity(this, gp.npc);
interactNPC(npcIndex);
 
int monsterIndex =gp.cChecker.checkEntity(this, gp.monster);
contactMonster(monsterIndex);

gp.eHandler.checkEvent();

if(collisionOn==false&&keyH.enterPressed==false){
    switch (direction) {
        case "up":worldY-=speed; break;
        case "down": worldY+=speed; break;
        case "left":  worldX -= speed;  break;
        case "right":worldX += speed; break;

    }
}
gp.keyH.enterPressed=false;



            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>60){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }
public void attacking(){
    spriteCounter++;

    if(spriteCounter<=5){
        spriteNum=1;

    }
    if(spriteCounter>5&&spriteCounter<=25){
        spriteNum=2;

        int currentWorldX=worldX;
        int currentWorldY=worldY;
        int solidAreaWidth=solidArea.width;
        int solidAreaHeigth=solidArea.height;


        switch(direction){
            case "up": worldY-=attackArea.height;break;
            case "down": worldY+=attackArea.height;break;
            case "left":worldX-=attackArea.width;break;
            case "right": worldX+= attackArea.width;break;
        }

        solidArea.width=attackArea.width;
        solidArea.height=attackArea.height;
        int monsterIndex =gp.cChecker.checkEntity(this, gp.monster);
        damageMonster(monsterIndex);

        worldX=currentWorldX;
        worldY=currentWorldY;
        solidArea.width=solidAreaWidth;
        solidArea.height=solidAreaHeigth;

    }

    if(spriteCounter>25){
        spriteNum=1;
        spriteCounter=0;
        attacking=false;
    }
}
    public void pickUpObject(int i){

     if(i!=999){

         }
    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed==true){
            if(i!=999){
            
                gp.gameState=gp.dialogueState;
                gp.npc[i].speak();
        }
         
            else{
                
                    attacking=true;
                }
            }
        }
    

    public void contactMonster(int i){
        if(i!=999){
           
            if(invincible==false){
                life-=1;
                invincible=true;
            }
        }
    }

    public void damageMonster(int i){
        if(i!=999){
            if(gp.monster[i].invincible==false){
                gp.monster[i].life-=1;
                gp.monster[i].invincible=true;

                if(gp.monster[i].life<=0){
                    gp.monster[i]=null;
                }
            }
        }
    }

    
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX=screenX;
        int tempScreenY=screenY;
        switch (direction) {
            case "up":
            //tempScreenY =screenY-gp.tileSize;
            if(attacking==false){
                image = (spriteNum == 1) ? up1 : up2;
            }
            if(attacking==true){
                tempScreenY=screenY-gp.tileSize;
                image = (spriteNum == 1) ? attackUp1 : attackUp2;
            }  
                break;

            case "down":
            if(attacking==false){
                image = (spriteNum == 1) ? down1 : down2;
            }
            if(attacking==true){
                image = (spriteNum == 1) ? attackDown1 : attackDown2;
            }
                break;

            case "left":
            //tempScreenX=screenX-gp.tileSize;
            if(attacking==false){
                image = (spriteNum == 1) ? left1 : left2;
            }
            if(attacking==true){
                tempScreenX=screenX-gp.tileSize;
                image = (spriteNum == 1) ? attackLeft1 : attackLeft2;
            }
                break;

            case "right":
            if(attacking==false){
                image = (spriteNum == 1) ? right1 : right2;
            }
            if(attacking==true){
                image = (spriteNum == 1) ? attackRight1 : attackRight2;
            }
                break;
        }

        if(invincible=true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }
}

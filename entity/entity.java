package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.UtilityTool;
import main.gamepanel;

public class entity {
    gamepanel gp;

    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2;
    public String direction = "down";
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0;
    boolean hpBarOn = false;
    int hpBarCounter = 0;
    public boolean onPath = false;
    public boolean knockBack =false;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 40, 40);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLookCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int knockBackCounter =0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    public int defaultSpeed;
    public int maxLife;
    public int life;
    public int level;
    public int maxMana;
    public int mana;
    public int ammo;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public entity currentWeapon;
    public entity currentShield;
    public Projectile projectile;

    public ArrayList<entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int price;
    public int knockBackPower =0;

    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle =8;

    public entity(gamepanel gp) {
        this.gp = gp;
    }

    public  int getLeftX(){
        return worldX +solidArea.x;
    }
    public int getRightX(){
        return worldX +solidArea.x+solidArea.width;
    }
    public  int getTopY(){
        return worldY +solidArea.y;
    }
    public int getBottomY(){
        return worldY +solidArea.y+solidArea.height;
    }
    public int getCOl(){
        return (worldX+solidArea.x)/gp.tileSize;
    }
    public int getRow(){
        return (worldY+solidArea.y)/gp.tileSize;
    }
    public void setAction() {
        // Implement action logic here
    }

    public void damageReaction() {
    
    }
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void interact(){

    }
    public boolean use(entity entity) { return false;
    }

    public void checkDrop() {
    }

    public void dropItem(entity droppedItem) {
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor() {
        return null;
    }

    public int getParticleSize() {
        return 0;
    }

    public int getParticleSpeed() {
        return 0;
    }

    public int getParticleMaxLife() {
        return 0;
    }

    public void generateParticle(entity generator, entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        particle p1 = new particle(gp, target, color, size, speed, maxLife, -2, -1);
        particle p2 = new particle(gp, target, color, size, speed, maxLife, +2, -1);
        particle p3 = new particle(gp, target, color, size, speed, maxLife, -2, +1);
        particle p4 = new particle(gp, target, color, size, speed, maxLife, +2, +1);

        gp.particalList.add(p1);
        gp.particalList.add(p2);
        gp.particalList.add(p3);
        gp.particalList.add(p4);
    }

    public void checkCollition() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true) {
            damagePlayer(attack);
        }
    }

    public void update() {

        if(knockBack==true){

            checkCollition();

            if(collisionOn==true){
                knockBackCounter=0;
                knockBack=false;
                speed =defaultSpeed;

            }
            else if (collisionOn ==false){
                switch(gp.player.direction){
                    case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
                }
            }
            knockBackCounter++;
            if(knockBackCounter==10){
                knockBackCounter=0;
                knockBack=false;
                speed =defaultSpeed;
            }
        }
        else{

            setAction();
        checkCollition();

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        }
        

        spriteCounter++;
        if (spriteCounter > 24) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    public void damagePlayer(int attack) {
        if (gp.player.invincible == false) {
            gp.playSE(6);
            int damage = attack - gp.player.defense;
            if (damage <= 0) {
                damage = 0;
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            BufferedImage image = null;
            switch (direction) {
                case "up":
                    image = (spriteNum == 1) ? up1 : up2;
                    break;
                case "down":
                    image = (spriteNum == 1) ? down1 : down2;
                    break;
                case "left":
                    image = (spriteNum == 1) ? left1 : left2;
                    break;
                case "right":
                    image = (spriteNum == 1) ? right1 : right2;
                    break;
            }

            if (type == 2 && hpBarOn == true) {
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            } else if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1f);
        }
    }

    void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;
        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
        if (gp.pFinder.search() == true) {

            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            }
            else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            }
            else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } 
            else if (enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollition();
                if (collisionOn == true) {
                    direction = "left";
                }
            } 
            else if (enTopY > nextY && enLeftX <nextX) {
                direction = "up";
                checkCollition();
                if (collisionOn == true) {
                    direction = "right";
                }
            } 
            else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollition();
                if (collisionOn == true) {
                    direction = "left";
                }
            } 
            else if (enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollition();
                if (collisionOn == true) {
                    direction = "right";
                }
            }

//            int nextCol = gp.pFinder.pathList.get(0).col;
//            int nextRow = gp.pFinder.pathList.get(0).row;
//            if (nextCol == goalCol && nextRow == goalRow) {
//                onPath = false;
//            }
        }
    }
    public int getDetected(entity user, entity target[][], String name){

        int index=999;


        int nextWorldX =user.getLeftX();
        int nextWorldY =user.getTopY();

        switch(user.direction){
            case "up":nextWorldY=user.getTopY()-1;break;
            case "down":nextWorldY=user.getBottomY()+1;break;
            case "left":nextWorldX=user.getLeftX()-1;break;
            case "right":nextWorldY=user.getRightX()+1;break;

        }

        int col =nextWorldX/gp.tileSize;
        int row =nextWorldY/gp.tileSize;

        for(int i=0; i<target[1].length;i++){
            if(target[gp.currentMap][i]!=null){
                if(target[gp.currentMap][i].getCOl()==col&&target[gp.currentMap][i].getRow()==row&& target[gp.currentMap][i].name.equals(name)){
                    

                    index =i;
                    break;
                }
            }
        }
        return index;

    }
}
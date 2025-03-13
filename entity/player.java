package entity;

import java.awt.AlphaComposite;
//import java.awt.Color;
//import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.ArrayList;

import main.gamepanel;
import main.keyHandler;
import object.OBJ_Axe;
//import object.OBJ_Axe;
import object.OBJ_Fireball;
import object.OBJ_Key;
//import object.OBJ_Rock;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class player extends entity {

    keyHandler keyH;
    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
   


    public player(gamepanel gp, keyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

       // attackArea.width = 36;
       // attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttckImage();
        setItems();
    }

    public void setDefaultValues() {
       worldX = gp.tileSize * 23;
       worldY = gp.tileSize * 21;
      // worldX = gp.tileSize * 12;
        //worldY = gp.tileSize * 12;
        
        defaultSpeed =4;
        speed = defaultSpeed;
        direction = "down";

        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana=4;
        mana=maxMana;
        ammo=10;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 500;
        currentWeapon = new OBJ_Sword_Normal(gp);
        //currentWeapon = new OBJ_Axe(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile =new OBJ_Fireball(gp);
        //projectile =new OBJ_Rock(gp);
        attack = getAttck();
        defense = getDefence();
    }
    public void setDefaultPositions(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";

    }
    public void restoreLifeAndMana(){
        life =maxLife;
        mana=maxMana;
        invincible=false;
    }
    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Axe(gp));

    }

    public int getAttck() {
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefence() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        up1 = setup("/res/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttckImage() {
        if(currentWeapon.type==type_sword){
            
        attackUp1 = setup("/res/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/res/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/res/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/res/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/res/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/res/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/res/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/res/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
       
        if(currentWeapon.type==type_axe){
       
            attackUp1 = setup("/res/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/res/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/res/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/res/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/res/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/res/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/res/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/res/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
       
            
        }
    }

    public void update() {
        if (attacking == true) {
            attacking();
        } else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            if (keyH.downPressed == true) {
                direction = "down";
            }
            if (keyH.leftPressed == true) {
                direction = "left";
            }
            if (keyH.rightPressed == true) {
                direction = "right";
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            gp.cChecker.checkEntity(this, gp.iTile);  

            gp.eHandler.checkEvent();

            if (collisionOn == false && keyH.enterPressed == false) {
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
            if (keyH.enterPressed == true && attackCanceled == false) {
                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;
            gp.keyH.enterPressed = false;

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

        if(gp.keyH.shotKeyPressed==true&&projectile.alive==false&& shotAvailableCounter==30&& projectile.haveResource(this)==true){
            projectile. set(worldX, worldY, direction, true, this);  

            projectile.subtractResource(this);

            for(int i=0; i<gp.projectile[1].length;i++){
                if(gp.projectile[gp.currentMap][i] ==null){
                    gp.projectile[gp.currentMap][i] =projectile;
                    break;
                }
            }
            shotAvailableCounter=0;
            gp.playSE(10);
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter<30){
            shotAvailableCounter++;
        }
        if(life>maxLife){
            life=maxLife;
        }
        if(mana>maxMana){
            mana=maxMana;
        }
        if(life<=0){
            gp.gameState=gp.gameOverState;
            gp.ui.commandNum=-1;
            gp.playSE(12);
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            int projectileIndex =gp.cChecker.checkEntity(this, gp.projectile);
            damageProjectile(projectileIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

            if(gp.obj[gp.currentMap][i].type==type_pickupOnly){
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }

            else if(gp.obj[gp.currentMap][i].type ==type_obstacle){
                if(keyH.enterPressed ==true){
                    attackCanceled=true;
                    gp.obj[gp.currentMap][i].interact();
                }
            }
            else{
            String text;

            if(canObtainItem(gp.obj[gp.currentMap][i])==true){
            
                gp.playSE(1);
                text = "Got a " + gp.obj[gp.currentMap][i].name + "!";

            }
            else{
                text = "Inventory is full!";
            }
            gp.ui.addMessage(text);
            gp.obj[gp.currentMap][i] = null;
            }
        }
    }

    public void interactNPC(int i) {
        if (gp.keyH.enterPressed == true) {
            if (i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
                gp.playSE(6);

                int damage =gp.monster[gp.currentMap][i].attack - defense;
                if (damage <= 0) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i,int attack , int knockBackPower) {
        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {
                gp.playSE(5);

                if(knockBackPower>0){
                    knockBack(gp.monster[gp.currentMap][i],knockBackPower);

                }

                int damage =attack - gp.monster[gp.currentMap][i].defense;
                if (damage <= 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the"+ gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + "+ gp.monster[gp.currentMap][i].exp );
                   
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                  
                }
            }
        }
    }
    
    public void knockBack(entity entity, int knockBackPower){

        entity .direction =direction;
        entity.speed +=knockBackPower;
        entity.knockBack=true;

    }
    public void damageInteractiveTile(int i){
        if(i!=999&&gp.iTile[gp.currentMap][i].destrutable==true&& gp.iTile[gp.currentMap][i].isCorrectItem(this)==true&&gp.iTile[gp.currentMap][i].invincible==false){
           
            gp.iTile[gp.currentMap][i].playSE();
                gp.iTile[gp.currentMap][i].life--;
                gp.iTile[gp.currentMap][i].invincible=true;

                generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
                
                if(gp.iTile[gp.currentMap][i].life==0){
                    gp.iTile[gp.currentMap][i]=gp.iTile[gp.currentMap][i].getDestroyedForm();
                }
                
                
            
        }
    }

    public void damageProjectile(int i){
        if(i!=999){
            entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive =false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttck();
            defense = getDefence();
            //contactMonster(actionLookCounter);

            gp.playSE(8);
            gp.gameState=gp.dialogueState;
            gp.ui.currentDialogue = "You are level "+level+" now!\n"+ "Yot feel stronger!";
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
        if(itemIndex<inventory.size()){
         
            entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type==type_sword||selectedItem.type==type_axe){
                currentWeapon = selectedItem;
                attack = getAttck(); 
                getPlayerAttckImage();
            }
            if(selectedItem.type==type_shield){
                currentShield = selectedItem;
                defense = getDefence();
            }
            if(selectedItem.type==type_consumable){

                if(selectedItem.use(this)==true);
               {
                if(selectedItem.amount>1){
                    selectedItem.amount--;
                }
                else{
                    inventory.remove(itemIndex);
                }
                } 
            }
        }
    }

    public int searchItemInInventory(String itemName){

        int itemIndex=999;
        for(int i=0;i<inventory.size();i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex=i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(entity item){


        boolean canObtain =false;

        if(item.stackable==true){
            int index =searchItemInInventory(item.name);
            if(index!=999){
                inventory.get(index).amount++;
                canObtain=true;
            }
            else{
                if(inventory.size()!=maxInventorySize){
                    inventory.add(item);
                    canObtain=true;
                }
            }
        }
        else{
            if(inventory.size()!=maxInventorySize){
                inventory.add(item);
                canObtain=true;
            }
        }
        return canObtain;
    }
            
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    image = (spriteNum == 1) ? up1 : up2;
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    image = (spriteNum == 1) ? attackUp1 : attackUp2;
                }
                break;
            case "down":
                if (attacking == false) {
                    image = (spriteNum == 1) ? down1 : down2;
                }
                if (attacking == true) {
                    image = (spriteNum == 1) ? attackDown1 : attackDown2;
                }
                break;
            case "left":
                if (attacking == false) {
                    image = (spriteNum == 1) ? left1 : left2;
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    image = (spriteNum == 1) ? attackLeft1 : attackLeft2;
                }
                break;
            case "right":
                if (attacking == false) {
                    image = (spriteNum == 1) ? right1 : right2;
                }
                if (attacking == true) {
                    image = (spriteNum == 1) ? attackRight1 : attackRight2;
                }
                break;
        }

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
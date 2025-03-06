package entity;

import main.gamepanel;

public class Projectile extends entity {
    entity user;
    gamepanel gp;

    public Projectile(gamepanel gp) {
        super(gp);
        this.gp = gp;
    }

    public void set (int worldX, int worldY, String direction, boolean alive, entity user) {
        

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;

    }

    public void update() {

        if(user==gp.player){

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex!=999){
            
                gp.player.damageMonster(monsterIndex, attack);
                alive=false;
            }
        }
        if(user!=gp.player){

            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if (gp.player.invincible==false && contactPlayer == true) {
                damagePlayer(attack);
                alive =false;
            }
        }

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

        life--;
        if (life <= 0) {
            alive = false;
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteCounter == 1) {
                spriteCounter = 2;
            } else if (spriteCounter == 2) {
                spriteCounter = 1;
            }
            spriteCounter = 0;
        }
    }
    public boolean haveResource(entity user){
        boolean haveResource =false;;
        
        return haveResource;
            
    } 
    public void subtractResource(entity user){
    }

}

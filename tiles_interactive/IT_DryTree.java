package tiles_interactive;

import java.awt.Color;

import entity.entity;
import main.gamepanel;

public class IT_DryTree extends InteractiveTile{
    gamepanel gp;
    public IT_DryTree(gamepanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = col*gp.tileSize;
        this.worldY = row*gp.tileSize;

        down1 =setup("/res/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destrutable = true; 
        life=3;
    }
    public boolean isCorrectItem(entity entity){
        boolean isCorrectItem=false;
        if(entity.currentWeapon.type==type_axe){
            isCorrectItem=true;
        }
        return isCorrectItem;
    }
    public void playSE(){

        gp.playSE(11);
    }
    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile=new IT_Trunk(gp,worldX/gp.tileSize,worldY/gp.tileSize);
        return tile;
    }
   public Color getParticleColor(){
        Color color=new Color(65,50,30);
        return color;
    }
    public int getParticleSize(){
        int size =6;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    } 
   
}

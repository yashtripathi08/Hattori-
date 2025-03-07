package tiles_interactive;

import main.gamepanel;

public class IT_Trunk extends InteractiveTile
{
     gamepanel gp;
    public IT_Trunk(gamepanel gp, int col,int row) {
        super(gp,col,row);
        this.gp = gp;
        this.worldX = col*gp.tileSize;
        this.worldY = row*gp.tileSize;
        
        down1 =setup("/res/tiles_interactive/trunk", gp.tileSize, gp.tileSize);
      
        solidArea.x=0;
        solidArea.y=0;
        solidArea.width=0;
        solidArea.height=0;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;    
    }
    
}

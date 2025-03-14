package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.entity;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class UI {

    gamepanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank,crystal_full ,crystal_blank,coin;
    public boolean messageOn = false;
   // public String message = "";
   // int messageCounter = 0;
   ArrayList<String> message = new ArrayList<String>();
   ArrayList<Integer> messageCounter = new ArrayList<Integer>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int playerSlotCol =0;
    public int playerSlotRow =0;
    public int npcSlotCol=0;
    public int npcSlotRow=0;

    int subState =0;
    int counter =0;
    public entity npc;

    public UI(gamepanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
        entity bronzeCoin =new OBJ_Coin_Bronze(gp);
        coin = bronzeCoin.down1;
    }

    public void addMessage(String text) {
       // message = text;
        //messageOn = true;
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (gp.gameState == gp.tileState) {
            drawTitleScreen();
           // gp.stopMusic();
        }

        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();
        }

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            drawPlayerLife();
        }

        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
            
        }

        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player,true);
        }

        if(gp.gameState==gp.optionsState){
            drawOptionScreen();
        }
        if(gp.gameState==gp.gameOverState){
            drawGameOverScreen();
        }
        if(gp.gameState==gp.transitionState){
            drawTransition();
        }
        if(gp.gameState==gp.tradeState){
            drawTradeScreen();
        }
        if(gp.gameState==gp.sleepState){
            drawSleepScreen();
        }
    }


    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
        x=(gp.tileSize/2)-5;
        y=(int)(gp.tileSize*1.5);
        i=0;
        while(i<gp.player.maxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x+=35;
        }

        x=(gp.tileSize/2)-5;
        y=(int)(gp.tileSize*1.5);
        i=0;
        while(i<gp.player.mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x+=35;
        }
        
        
    }

    public void drawMessage() {
    

        int messageX = gp.tileSize ;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for( int i=0;i<message.size();i++){
            if(message.get(i)!=null){
                
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);

                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter =messageCounter.get(i)+1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
            
    }
}


    public void drawTitleScreen() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "HATTORI";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawCharacterScreen() {
        
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defence", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight+10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight+15;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;


        int tailX= (frameX + frameWidth) -30;

        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX-gp.tileSize, textY-24,null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX-gp.tileSize, textY-24,null);


    }
    public void drawInventory(entity entity, boolean cursor){
        int frameX=0;
        int frameY= 0;
        int frameWidth=0;
        int frameHeight=0;
        int slotCol;
        int slotRow;
        if(entity==gp.player){

             frameX= gp.tileSize*12;
             frameY= gp.tileSize;
             frameWidth= gp.tileSize*6;
             frameHeight= gp.tileSize*5;
             slotCol=playerSlotCol;
             slotRow=playerSlotRow;
        }
        else{
             frameX= gp.tileSize*2;
             frameY= gp.tileSize;
             frameWidth= gp.tileSize*6;
             frameHeight= gp.tileSize*5;
             slotCol=npcSlotCol;
             slotRow=npcSlotRow;
        }
       
       
       
       
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        for(int i=0;i<entity.inventory.size();i++){

            if(entity.inventory.get(i)==entity.currentWeapon||entity.inventory.get(i)==entity.currentShield|| entity.inventory.get(i)==entity.currentLight){
            
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            
            }
                

            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
            
            if(entity ==gp.player &&entity.inventory.get(i).amount >1){
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s=""+entity.inventory.get(i).amount;
                amountX =getXForAlignToRightText(s, slotX+44);
                amountY=slotY+gp.tileSize;

                g2.setColor((new Color(60,60,60)));
                g2.drawString(s, amountX, amountY);
                g2.setColor(Color.white);
                g2.drawString(s, amountX-3, amountY-3);


            }
            slotX += slotSize;
            if(i==4 || i==9|| i==14){
                slotX = slotXstart;
                slotY +=slotSize;
            }
        }
            

        if(cursor==true){

        int cursorX = slotXstart+(slotSize*slotCol);
        int cursorY = slotYstart+(slotSize*slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        int dframeX= frameX;
        int dframeY= frameY+frameHeight;
        int dframeWidth= frameWidth;
        int dframeHeight= gp.tileSize*3;

        int textX = dframeX + 20;
        int textY = dframeY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot(slotCol,slotRow);

        if(itemIndex<entity.inventory.size()){

            drawSubWindow(dframeX, dframeY, dframeWidth, dframeHeight);


            for(String line:entity.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
        }
        
    }
    public void drawOptionScreen(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int frameX =gp.tileSize*5;
        int frameY=gp.tileSize;
        int frameHeight= gp.tileSize*9;
        int frameWidth=gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState){
            case 0:options_top(frameX, frameY);break;
            case 1: options_fullScreenNotification(frameX, frameY);break;
            case 2:options_control(frameX, frameY);break;
            case 3:options_endGameConfirmation(frameX,frameY);break;
        }

        gp.keyH.enterPressed=false;
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text ="Game Over";
        g2.setColor(Color.black);
        x=getXForCenteredText(text);
        y=gp.tileSize*4;
        g2.drawString(text, x, y);

        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);


        g2.setFont(g2.getFont().deriveFont(50f));
        text="Retry";
        x=getXForCenteredText(text);
        y+=gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum==0){
            g2.drawString(">", x-40, y);
        }

        text="Quit";
        x=getXForCenteredText(text);
        y+=55;
        g2.drawString(text, x, y);
        if(commandNum==1){
            g2.drawString(">", x-40, y);
        }

    }
    public void options_top(int frameX,int frameY){

        int textX;
        int textY;

        String text ="OPtions";
        textX =getXForCenteredText(text);
        textY=frameY+gp.tileSize;
        g2.drawString(text, textX, textY);


        textX=frameX +gp.tileSize;
        textY=frameY +gp.tileSize*2;
        g2.drawString("FUll Screen", textX, textY);
        if(commandNum==0){

            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                if(gp.fullScreenOn==false){
                    gp.fullScreenOn=true;
                }
                else if(gp.fullScreenOn==true){
                    gp.fullScreenOn=false;
                }
                subState=1;
            }
        }

        textY+=gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum==1){

            g2.drawString(">", textX-25, textY);
        }

        textY+=gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum==2){

            g2.drawString(">", textX-25, textY);
        }

        textY+=gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum==3){

            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState=2;
                commandNum=0;
            }
        }

        textY+=gp.tileSize;
        g2.drawString("END Game", textX, textY);
        if(commandNum==4){

            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState=3;
                commandNum=0;
            }
        }

        textY+=gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum==5){

            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                gp.gameState=gp.playState;
                commandNum=0;
            }
        }

        textX=frameX +(int)(gp.tileSize*5);
        textY =frameY+gp.tileSize*2+24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY - gp.tileSize, 24, 24);
        if(gp.fullScreenOn==true){
            g2.fillRect(textX, textY - gp.tileSize, 24, 24);
        }

        textY+=gp.tileSize;
        g2.drawRect(textX, textY-gp.tileSize, 120, 24);
        int volumeWidth =24*gp.music.volumeScale;
        g2.fillRect(textX, textY-gp.tileSize, volumeWidth, 24);

        textY+=gp.tileSize;
        g2.drawRect(textX, textY-gp.tileSize, 120, 24);
         volumeWidth =24*gp.se.volumeScale;
        g2.fillRect(textX, textY-gp.tileSize, volumeWidth, 24);


        gp.config.saveConfig();
    }
    public void options_fullScreenNotification(int frameX,int frameY){

        int textX=frameX +gp.tileSize;
        int textY =frameY +(gp.tileSize*2+24);

        currentDialogue ="     The change will take \n     effect after restarting\n     the game.";

        for(String line:currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY+=40;
        }

        textY=frameY +gp.tileSize*8;
        g2.drawString("Back", textX, textY);
        if(commandNum==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState=0;
                
            }
        }

    }
    public void options_control(int frameX,int frameY){
        int textX;
        int textY;
        String text="Control";
        textX=getXForCenteredText(text);
        textY=frameY +gp.tileSize;
        g2.drawString(text, textX, textY);
        textX=frameX+gp.tileSize;
        textY+=gp.tileSize;
        g2.drawString("Move", textX, textY);textY +=gp.tileSize;
        g2.drawString("COnfirm/Attack", textX, textY);textY +=gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY);textY +=gp.tileSize;
        g2.drawString("Character Screen", textX, textY);textY +=gp.tileSize;
        g2.drawString("Pause", textX, textY);textY +=gp.tileSize;
        g2.drawString("Options", textX, textY);textY +=gp.tileSize;

        textX=frameX+gp.tileSize*6;
        textY=frameY +gp.tileSize*2;
        g2.drawString("WASD", textX, textY);textY +=gp.tileSize;
        g2.drawString("ENTER", textX, textY);textY +=gp.tileSize;
        g2.drawString("F", textX, textY);textY +=gp.tileSize;
        g2.drawString("C/X", textX, textY);textY +=gp.tileSize;
        g2.drawString("P/ENTER", textX, textY);textY +=gp.tileSize;
        g2.drawString("ESC", textX, textY);textY +=gp.tileSize;

        textX=frameX+gp.tileSize;
        textY =frameY+gp.tileSize*8;
        g2.drawString("Back", textX, textY);
        if(commandNum==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState=0;
                commandNum=3;
            }
        }


    }
    public void options_endGameConfirmation(int frameX, int frameY){
        int textX =frameX+gp.tileSize;
        int textY =frameY+gp.tileSize*2;
        currentDialogue="Quit the game and \nreturn to the title screen?";
        for(String line:currentDialogue.split("\n")){
            g2.drawString(line, textX, textY);
            textY+= 40;
        }

        String text="Yes";
        textX=getXForCenteredText(text);
        textY+=gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if(commandNum==0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState=0;
                gp.gameState=gp.tileState;
            }
        }

        text="No";
        textX=getXForCenteredText(text);
        textY+=gp.tileSize;
        g2.drawString(text, textX, textY);
        if(commandNum==1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed==true){
                subState=0;
                commandNum=4;
            }
        }
        
    }

    public void drawTransition(){

        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if(counter==50){
            counter =0;
            gp.gameState=gp.playState;
            gp.currentMap=gp.eHandler.tempMap;
            gp.player.worldX=gp.tileSize*gp.eHandler.tempCol;
            gp.player.worldY=gp.tileSize*gp.eHandler.tempRow;
            gp.eHandler.previousEventX=gp.player.worldX;
            gp.eHandler.previousEventY=gp.player.worldY;
        }
    }
    public void drawTradeScreen(){

        switch(subState){
            case 0:trade_select();break;
            case 1:trade_bye();break;
            case 2:trade_sell();break;

        }
        gp.keyH.enterPressed=false;
    }
    public void trade_select(){

        drawDialogueScreen();

        int x=gp.tileSize*15;
        int y=gp.tileSize*4;
        int width =gp.tileSize*3;
        int heigth =(int)(gp.tileSize*3.5);
        drawSubWindow(x, y, width, heigth);

        x+=gp.tileSize;
        y+=gp.tileSize;
        g2.drawString("Bye", x, y);
        if(commandNum==0){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed==true){
                subState=1;
            }
        }
        y+=gp.tileSize;
        g2.drawString("Sell", x, y);
        if(commandNum==1){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed==true){
                subState=2;
            }
        }
        y+=gp.tileSize;
        g2.drawString("Leave", x, y);
        if(commandNum==2){
            g2.drawString(">", x-24, y);
            if(gp.keyH.enterPressed==true){
                commandNum=0;
                gp.gameState=gp.dialogueState;
                currentDialogue="Come again, hehe!";
            }
        }

    }
    public void trade_bye(){

        drawInventory(gp.player, false);
        drawInventory(npc, true);

        int x=gp.tileSize*2;
        int y=gp.tileSize*9;
        int width =gp.tileSize*6;
        int height=gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+24, y+60);

         x=gp.tileSize*12;
         y=gp.tileSize*9;
         width =gp.tileSize*6;
         height=gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coin : "+gp.player.coin, x+24, y+60);

        int itemIndex =getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex<npc.inventory.size()){
            x=(int)(gp.tileSize*5.5);
            y=(int)(gp.tileSize*5.5);
            width=(int)(gp.tileSize*2.5);
            height=gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32, 32,null);
            int price =npc.inventory.get(itemIndex).price;
            String text=""+price;
            x=getXForAlignToRightText(text, gp.tileSize*8-20);
            g2.drawString(text, x, y+34);

            if(gp.keyH.enterPressed==true){
                if(npc.inventory.get(itemIndex).price>gp.player.coin){
                    subState=0;
                    gp.gameState=gp.dialogueState;
                    currentDialogue="You need more coins to buy that!";
                    drawDialogueScreen();
                }
                else{
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex))==true){
                        gp.player.coin -= npc .inventory .get(itemIndex).price;
                    }
                    else{
                        subState=0;
                    gp.gameState=gp.dialogueState;
                    currentDialogue="You cannot carry any more!";
                    }
                }
                // else if(gp.player.inventory.size()==gp.player.maxInventorySize){

                //     subState=0;
                //     gp.gameState=gp.dialogueState;
                //     currentDialogue="You cannot carry any more!";
                    
                // }
                // else{
                //     gp.player.coin -= npc .inventory .get(itemIndex).price;
                //     gp.player.inventory.add(npc.inventory.get(itemIndex));
                // }
            }
               
        }


    }
    
    
    public void trade_sell(){

        drawInventory(gp.player, true);
        int x;
        int y;
        int width;
        int height;

         x=gp.tileSize*2;
         y=gp.tileSize*9;
         width =gp.tileSize*6;
         height=gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x+24, y+60);

         x=gp.tileSize*12;
         y=gp.tileSize*9;
         width =gp.tileSize*6;
         height=gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coin : "+gp.player.coin, x+24, y+60);

        int itemIndex =getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if(itemIndex<gp.player.inventory.size()){
            x=(int)(gp.tileSize*15.5);
            y=(int)(gp.tileSize*5.5);
            width=(int)(gp.tileSize*2.5);
            height=gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x+10, y+8, 32, 32,null);
            int price =gp.player.inventory.get(itemIndex).price/2;
            String text=""+price;
            x=getXForAlignToRightText(text, gp.tileSize*18-20);
            g2.drawString(text, x, y+34);

            if(gp.keyH.enterPressed==true){
               
                if(gp.player.inventory.get(itemIndex)==gp.player.currentWeapon||gp.player.inventory.get(itemIndex)==gp.player.currentShield){

                    commandNum=0;
                    subState=0;
                    gp.gameState=gp.dialogueState;
                    currentDialogue="You cannot sell equipped item!";

                }
                else {
                    if(gp.player.inventory.get(itemIndex).amount>1){
                        gp.player.inventory.get(itemIndex).amount--;
                    }
                    else{
                        gp.player.inventory.remove(itemIndex);
                    }
                    
                    gp.player.coin+=price;
                }
            }
               
        }
    }

    public void drawSleepScreen(){
        counter++;
     
        if(counter<120){
            gp.eManager.lighting.filterAlpha+=0.01f;
            if( gp.eManager.lighting.filterAlpha>1f ){
                gp.eManager.lighting.filterAlpha=1f;

            }
        }
        if(counter>=120){
            gp.eManager.lighting.filterAlpha-=0.01f;
            if( gp.eManager.lighting.filterAlpha<=0f){
                gp.eManager.lighting.filterAlpha=0f;
                counter =0;
                gp.eManager.lighting.dayState=gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter=0;
                gp.gameState=gp.playState;
                gp.player.getPlayerImage();
            }

        }
    }


    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex =slotCol+(slotRow*5);
        return itemIndex;

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXForAlignToRightText(String text, int tailX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x= tailX - length;
        return x;
    }

}
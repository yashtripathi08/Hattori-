package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.entity;
import entity.player;
import main.tile.tileManager;
import tiles_interactive.InteractiveTile;

public class gamepanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    int fps = 60;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int screenWidth2=screenWidth;
    int screenHeight2=screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn=false;

    tileManager tileM = new tileManager(this);
    public keyHandler keyH = new keyHandler(this);

    Sound music = new Sound();
    Sound se = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    public player player = new player(this, keyH); // Declare the player variable
    public entity obj[] = new entity[20];
    public entity npc[] = new entity[10];
    public entity monster[] = new entity[20];
    public InteractiveTile iTile[] = new InteractiveTile[50];
    public ArrayList<entity> projectileList = new ArrayList<>();
    public ArrayList<entity> particalList = new ArrayList<>();
    ArrayList<entity> entityList = new ArrayList<>();

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int tileState = 0;
    public final int characterState = 4;
    public final int optionsState =5;

    public gamepanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow(); // Request focus for the panel

        player = new player(this, keyH); // Instantiate the player object
    }

    public void setUpGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();

       // playMusic(0);

        gameState = tileState;
        keyH.enterPressed = false;

        tempScreen=new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 =(Graphics2D)tempScreen.getGraphics();
        //setFullScreen();
    }
    public void setFullScreen(){
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd= ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(main.window);

        screenWidth2=main.window.getWidth();
        screenHeight2=main.window.getHeight();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                drawTotempScreen();
                drawToScreen();
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].alive == false) {
                        monster[i].checkDrop();
                        monster[i] = null; // Remove the monster from the game
                    } else {
                        monster[i].update();
                    }
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    } else if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }

            for (int i = 0; i < particalList.size(); i++) {
                if (particalList.get(i) != null) {
                    if (particalList.get(i).alive == true) {
                        particalList.get(i).update();
                    } else if (particalList.get(i).alive == false) {
                        particalList.remove(i);
                    }
                }
            }

            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].update();
                }
            }

            if (gameState == pauseState) {
                // Add pause state logic here
            }
        }
    }

    public void drawTotempScreen(){
        long drawStart = 0;
        if (keyH.showDebugText == true) {
            drawStart = System.nanoTime();
        }

        if (gameState == tileState) {
            ui.draw(g2);
        } else {
            tileM.draw(g2);

            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {
                    iTile[i].draw(g2);
                }
            }

            entityList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }
            for (int i = 0; i < particalList.size(); i++) {
                if (particalList.get(i) != null) {
                    entityList.add(particalList.get(i));
                }
            }

            Collections.sort(entityList, new Comparator<entity>() {
                @Override
                public int compare(entity e1, entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            entityList.clear();

            ui.draw(g2);
        }

        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y);
            y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, x, y);
            y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw Time: " + passed, x, y);
        }
    }
    public void drawToScreen(){
        Graphics g=getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2,screenHeight2,null);
        g.dispose();
    }

   
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
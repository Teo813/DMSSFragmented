package entity;

import com.company.GamePanel;
import com.company.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY= gp.screenHeight/2 - (gp.tileSize/2);
        setDefault();
        getPlayerImage();
        solidArea = new Rectangle(0,16,36,36);
    }
    public void setDefault(){
        worldX=gp.tileSize *23;
        worldY=gp.tileSize*21;
        speed=4;
        direction = "down";
        getPlayerImage();
    }
    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/playerForward1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/playerForward2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/playerBack1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/playerBack2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/playerRight2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/playerLeft2.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if (keyH.upPressed == true) {
                direction = "up";
                worldY -= speed;

            } else if (keyH.downPressed == true) {
                direction = "down";
                worldY += speed;

            } else if (keyH.leftPressed == true) {
                direction = "left";
                worldX -= speed;

            } else if (keyH.rightPressed == true) {
                direction = "right";
                worldX += speed;

            }
            spriteCounter++;
            if (spriteCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        collisonOn = false;
        gp.cChecker.checkTile(this);
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum==1) {
                    image = up1;
                }
                if (spriteNum==2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum==1) {
                    image = down1;
                }
                if (spriteNum==2){
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum==1) {
                    image = right1;
                }
                if (spriteNum==2){
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum==1) {
                    image = left1;
                }
                if (spriteNum==2){
                    image = left2;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}

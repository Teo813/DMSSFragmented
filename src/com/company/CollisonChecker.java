package com.company;

import entity.Entity;

public class CollisonChecker {
    GamePanel gp;
    public CollisonChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
     int entityLeftWorldX = entity.worldX + entity.solidArea.x;
     int entityRightWorldX=entity.worldX + entity.solidArea.x + entity.solidArea.width;
     int entityTopWorldY = entity.worldY+entity.solidArea.y;
     int entityBottomWorldY = entity.worldY+entity.solidArea.y+entity.solidArea.height;
     int entityLeftCol = entityLeftWorldX/gp.tileSize;
     int entityRightCol = entityRightWorldX/gp.tileSize;
     int entityTopRow = entityTopWorldY/gp.tileSize;
     int entityBottomRow = entityBottomWorldY/gp.tileSize;

     int tileNum1,tileNum2;
     switch (entity.direction){
         case "up":
             entityTopRow = (entityTopWorldY-entity.speed)/gp.tileSize;
             tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
             tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
             if (gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison==true){
                entity.collisonOn = true;
             }
         case "down":
             entityBottomRow = (entityBottomWorldY-entity.speed)/gp.tileSize;
         case "left":
             entityLeftCol = (entityLeftWorldX-entity.speed)/gp.tileSize;
         case"right":
             entityRightCol = (entityRightWorldX-entity.speed)/gp.tileSize;
     }
    }
}

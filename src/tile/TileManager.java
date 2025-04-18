package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world1.txt");
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col< gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col ++;
                }
                if (col == gp.maxWorldCol){
                    col = 0;
                    row ++;

                }
            }
            br.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/field.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/flowers.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/pavement.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wheat.png")));

        } catch (IOException e){
            e.printStackTrace();
        }
        // 0 = trava
        // 1 = pole
        // 2 = voda
        // 3 = kytky
        // 4 = chodnik
        // 5 = psenice
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
            worldCol ++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow ++;

            }
        }
    }
}

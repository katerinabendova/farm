package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[30];
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

    /**
     *
     */
    public void getTileImage(){

            setup(0, "grass", false);
            setup(1, "circuit", true);
            setup(2, "water", true);
            setup(3, "grass_flowers", false);
            setup(4, "stream_basic1", true);
            setup(5, "field", false);
            setup(6, "pavement_basic", false);
            setup(7, "pavement_basic2", false);
            setup(8, "pavement_bottom_left", false);
            setup(9, "pavement_bottom_right", false);
            setup(10, "pavement_middle1", false);
            setup(11, "pavement_middle2", false);
            setup(12, "pavement_middle3", false);
            setup(13, "pavement_top_right", false);
            setup(14, "water_bottom", true);
            setup(15, "water_bottom_left", true);
            setup(16, "water_bottom_right", true);
            setup(17, "water_top", true);
            setup(18, "water_top_left", true);
            setup(19, "water_top_right", true);
            setup(20, "water_right", true);
            setup(21, "water_left", true);
            setup(22, "water_middle1", true);
            setup(23, "stream_basic1", true);
            setup(24, "stream_basic2", true);
            setup(25, "stream_bottom_right", true);
            setup(26, "stream_top_left", true);

    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.titleSize, gp.titleSize);
            tile[index].collision = collision;

        } catch (IOException e){
            e.printStackTrace();
        }
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

            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            worldCol ++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow ++;

            }
        }
    }
}

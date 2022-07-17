package program.game.shootingStars;

import program.game.shootingStars.variables.constant.PathConstant;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage spaceBackground;
	public static BufferedImage rocketSprite;
	public static BufferedImage asteroidSprite;
	public static BufferedImage enemySprite;
	public static BufferedImage coinSprite;
	public static BufferedImage bulletSprite;
	
	public static BufferedImage fireAnimationSprites [];
	
	
	public ImageLoader () {
		fireAnimationSprites = new BufferedImage [15];
		loadImages();
	}
	
	
	private void loadImages () {
		try {
			spaceBackground = ImageIO.read(new File (PathConstant.FILE_PATH_BACKGROUND));
			rocketSprite = ImageIO.read(new File (PathConstant.FILE_PATH_ROCKET_SPRITE));
			asteroidSprite = ImageIO.read(new File (PathConstant.FILE_PATH_ASTEROID_SPRITE));
			coinSprite = ImageIO.read(new File (PathConstant.FILE_PATH_COIN_SPRITE));
			enemySprite = ImageIO.read(new File (PathConstant.FILE_PATH_ENEMY_SPRITE));
			bulletSprite = ImageIO.read(new File (PathConstant.FILE_PATH_BULLET_SPRITE));
			
			for (int i = 0; i <  fireAnimationSprites.length; i++) {
				 fireAnimationSprites[i] = ImageIO.read(new File (PathConstant.FILE_PATH_FIRE_SPRITE + "fire" + i + ".png"));
			}
			
		} catch (Exception e) {}
		
	}

}

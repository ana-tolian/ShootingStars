package program.game.shootingStars.io;

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
	public static BufferedImage cursorImage;
	public static BufferedImage explosionImage;
	public static BufferedImage coinCollectedImage;
	public static BufferedImage plusImage;
	public static BufferedImage upgradeIcon;
	public static BufferedImage shopIcon;

	public static BufferedImage [] fireAnimationSprites;
	public static BufferedImage [] moduleLevelSprite;

	
	public ImageLoader () {
		fireAnimationSprites = new BufferedImage [15];
		moduleLevelSprite = new BufferedImage [5];
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
			cursorImage = ImageIO.read(new File (PathConstant.FILE_PATH_CURSOR));
			explosionImage = ImageIO.read(new File (PathConstant.FILE_PATH_EXPLOSION_SPRITE));
			coinCollectedImage = ImageIO.read(new File (PathConstant.FILE_PATH_COIN_COLLECTED_SPRITE));
			plusImage = ImageIO.read(new File (PathConstant.FILE_PATH_PLUS_ICON));
			upgradeIcon = ImageIO.read(new File (PathConstant.FILE_PATH_UPGRADE_ICON));
			shopIcon = ImageIO.read(new File (PathConstant.FILE_PATH_SHOP_ICON));
//			Image curImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/images/cursor.png"));

			for (int i = 0; i < fireAnimationSprites.length; i++) {
				 fireAnimationSprites[i] = ImageIO.read(new File (PathConstant.FILE_PATH_FIRE_SPRITE + "fire" + i + ".png"));
			}

			for (int i = 0; i < moduleLevelSprite.length; i++) {
				moduleLevelSprite[i] = ImageIO.read(new File (PathConstant.FILE_PATH_SCALE_SPRITE + "scale" + (i + 1) + ".png"));
			}
			
		} catch (Exception e) {e.printStackTrace();}
		
	}

	public static BufferedImage getSpriteByPath (String path) {
		BufferedImage br = null;

		try {
			br = ImageIO.read(new File (path));
		} catch (Exception e) {e.printStackTrace();}

		return br;
	}

}

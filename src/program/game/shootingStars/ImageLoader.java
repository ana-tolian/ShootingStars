package program.game.shootingStars;

import program.game.shootingStars.variables.constant.PathConstant;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

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

	public static BufferedImage fireAnimationSprites [];
	public static BufferedImage moduleLevelSprite[];

	public static ImageIcon coinIcon;
	public static ImageIcon upgradeIcon;
	public static ImageIcon shopIcon;
	public static ImageIcon upArrow;
	public static ImageIcon downArrow;
	
	
	public ImageLoader () {
		fireAnimationSprites = new BufferedImage [15];
		moduleLevelSprite = new BufferedImage [5];
		loadIcons();
		loadImages();
	}

	private void loadIcons () {
		coinIcon = new ImageIcon(PathConstant.FILE_PATH_COIN_SPRITE);
		upgradeIcon = new ImageIcon(PathConstant.FILE_PATH_UPGRADE_ICON);
		shopIcon = new ImageIcon(PathConstant.FILE_PATH_SHOP_ICON);
		upArrow = new ImageIcon(PathConstant.FILE_PATH_UP_ARROW);
		downArrow = new ImageIcon(PathConstant.FILE_PATH_DOWN_ARROW);

		coinIcon.getImage().flush();
		upgradeIcon.getImage().flush();
		shopIcon.getImage().flush();
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
//			Image curImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/images/cursor.png"));

			for (int i = 0; i < fireAnimationSprites.length; i++) {
				 fireAnimationSprites[i] = ImageIO.read(new File (PathConstant.FILE_PATH_FIRE_SPRITE + "fire" + i + ".png"));
			}

			for (int i = 0; i < moduleLevelSprite.length; i++) {
				moduleLevelSprite[i] = ImageIO.read(new File (PathConstant.FILE_PATH_SCALE_SPRITE + "scale" + (i + 1) + ".png"));
			}
			
		} catch (Exception e) {}
		
	}

	public static BufferedImage getSpriteByPath (String path) {
		BufferedImage br = null;

		try {
			br = ImageIO.read(new File (path));
		} catch (Exception e) {}

		return br;
	}

	public ImageIcon getCoinIcon () {
		return coinIcon;
	}

	public ImageIcon getUpgradeIcon () {
		return upgradeIcon;
	}

	public ImageIcon getShopIcon () {
		return shopIcon;
	}

	public ImageIcon getUpArrowIcon () {
		return upArrow;
	}

	public ImageIcon getDownArrowIcon () {
		return downArrow;
	}

}

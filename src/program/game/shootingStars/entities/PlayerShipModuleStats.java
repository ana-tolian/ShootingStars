package program.game.shootingStars.entities;

public class PlayerShipModuleStats {

    private String name;
    private String special;
    private int weaponLevel;
    private int hullLevel;


    public PlayerShipModuleStats (String name) {
        this.name = name;
        this.weaponLevel = 1;
        this.hullLevel = 1;
        this.special = "none";
    }

    public PlayerShipModuleStats (String name, int weaponLevel, int hullLevel, String special) {
        this.name = name;
        this.weaponLevel = weaponLevel;
        this.hullLevel = hullLevel;
        this.special = special;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public void setWeaponLevel(int weaponLevel) {
        this.weaponLevel = weaponLevel;
    }

    public int getHullLevel() {
        return hullLevel;
    }

    public void setHullLevel(int hullLevel) {
        this.hullLevel = hullLevel;
    }

    @Override
    public String toString () {
        return name + "#" + weaponLevel + "#" + hullLevel + "#" + special;
    }
}

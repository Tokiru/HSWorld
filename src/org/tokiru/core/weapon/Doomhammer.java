package org.tokiru.core.weapon;

/**
 * Created by tokiru.
 */
public class Doomhammer extends Weapon {
    public Doomhammer() {
        super(null, 2, 8, "Doomhammer");
    }
    @Override
    public int maxNumberOfAttacks() {
        return 2;
    }
}

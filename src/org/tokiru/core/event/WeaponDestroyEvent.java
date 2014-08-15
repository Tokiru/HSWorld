package org.tokiru.core.event;

import org.tokiru.core.weapon.Weapon;

/**
 * Created by tokiru.
 */
public class WeaponDestroyEvent implements Event {
    public Weapon weapon;
    public WeaponDestroyEvent(Weapon weapon) {
        this.weapon = weapon;
    }
    @Override
    public EventType getType() {
        return EventType.WEAPON_DESTROY;
    }
}

package org.tokiru.core.buff;

/**
 * Created by tokiru.
 */
public class AuraAttackBuff extends AuraBuff implements Buff {
    public AuraAttackBuff(int attack) {
        super(new AttackBuff(attack));
    }
}

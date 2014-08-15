package org.tokiru.core.buff;

/**
 * Created by tokiru.
 */
public class OneTurnAttackBuff extends OneTurnBuff implements Buff {
    public int attackBuff;
    public OneTurnAttackBuff(int attackBuff) {
        this.attackBuff = attackBuff;
    }

    @Override
    public Buff getRaw() {
        return new AttackBuff(attackBuff);
    }


}

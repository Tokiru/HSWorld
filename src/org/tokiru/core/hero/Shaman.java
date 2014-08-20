package org.tokiru.core.hero;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.spell.NonTargetSpellCard;
import org.tokiru.core.card.spell.SpellCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.creature.MinionBuilder;
import org.tokiru.core.creature.SkeletalCreature;
import org.tokiru.core.event.Event;

/**
 * Created by tokiru.
 */
public class Shaman implements HeroClass {
    @Override
    public Type getType() {
        return Type.SHAMAN;
    }

    @Override
    public Card getAbilityCard() {
        return new TotemicCallCard();
    }

    @Override
    public String getName() {
        return "Thrall";
    }

    private class TotemicCallCard extends NonTargetSpellCard implements SpellCard {

        public TotemicCallCard() {
            super(2, "Totemic Call");
        }

        @Override
        public void play(Creature target, BoardState boardState, int playerID, int spellDamage) {
            int totemType = (int) (Math.random() * 4);
            Creature totem;
            if (totemType == 0) {
                totem = new MinionBuilder().setHealth(2).setAttack(0).taunt().setName("Stoneclaw totem").compile().getCreature();
            } else if (totemType == 1) {
                totem = new MinionBuilder().setHealth(2).setAttack(0).setSpellDamage(1).
                        setName("Airwrath totem").compile().getCreature();
            } else if (totemType == 2) {
                totem = new HealingTotem();
            } else {
                totem = new MinionBuilder().setHealth(1).setAttack(1).setName("Searing totem").compile().getCreature();
            }

            boardState.addCreature(totem, null, playerID);
        }

        private class HealingTotem extends SkeletalCreature implements Creature {
            public HealingTotem() {
                super(1, 1, "Healing totem");
            }

            @Override
            public void accept(Event event) {
                super.accept(event);
                if (event.getType() == Event.EventType.END_TURN) {
                    for (Creature creature : boardState.getFriendlyMinions(boardState.getPlayerID(this))) {
                        creature.takeHeal(1);
                    }
                }
            }
        }
    }
}

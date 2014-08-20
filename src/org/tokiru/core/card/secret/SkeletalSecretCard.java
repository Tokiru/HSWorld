package org.tokiru.core.card.secret;

import org.tokiru.core.board.BoardState;
import org.tokiru.core.card.Card;
import org.tokiru.core.card.SkeletalCard;
import org.tokiru.core.creature.Creature;
import org.tokiru.core.player.Player;
import org.tokiru.core.secret.Secret;

/**
 * Created by tokiru.
 */
public class SkeletalSecretCard extends SkeletalCard implements Card, SecretCard {

    private final Secret secret;

    public SkeletalSecretCard(Secret secret) {
        this.secret = secret;
    }

    @Override
    public CardType getType() {
        return CardType.SECRET;
    }

    @Override
    public boolean canPlay(Creature target, BoardState boardState) {
        return true;
    }

    @Override
    public void play(Player owner, BoardState boardState) {
        secret.play(owner, boardState);
    }
}

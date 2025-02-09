package stsjorbsmod.cards.wanderer;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stsjorbsmod.JorbsMod;
import stsjorbsmod.actions.CardsToTopOfDeckAction;
import stsjorbsmod.cards.CustomJorbsModCard;
import stsjorbsmod.characters.Wanderer;
import stsjorbsmod.powers.EnergizedCustomPower;

import static stsjorbsmod.JorbsMod.makeCardPath;

public class Channel extends CustomJorbsModCard {
    public static final String ID = JorbsMod.makeID(Channel.class.getSimpleName());
    public static final String IMG = makeCardPath("Block_Commons/channel.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Wanderer.Enums.WANDERER_CARD_COLOR;

    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int ENERGY_NEXT_TURN = 1;
    private static final int UPGRADE_CARDS_FROM_DISCARD_TO_DECK = 1;

    public Channel() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
        magicNumber = baseMagicNumber = ENERGY_NEXT_TURN;
        metaMagicNumber = baseMetaMagicNumber = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new EnergizedCustomPower(p, magicNumber)));
        if (metaMagicNumber > 0) {
            addToBot(new CardsToTopOfDeckAction(p, p.discardPile, this.metaMagicNumber, false));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMetaMagicNumber(UPGRADE_CARDS_FROM_DISCARD_TO_DECK);
            upgradeDescription();
        }
    }
}

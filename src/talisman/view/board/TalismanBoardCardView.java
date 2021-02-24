package talisman.view.board;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import talisman.util.Pair;
import talisman.view.cards.TalismanCardView;
import talisman.view.cards.TalismanCardViewImpl;

/**
 * A panel that wraps a card view for showing on the board
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanBoardCardView extends JPanel {
    private static final long serialVersionUID = 1L;
    private TalismanCardView card;
    private BoardCardListner interactListener;
    private BoardCardListner hiddenListener;

    /**
     * Called for events generated by a TalismanBoardCardView.
     * 
     * @author Alberto Arduini
     *
     */
    public interface BoardCardListner extends EventListener {
        void cardInteracted(TalismanBoardCardView cardPanelView);
    }

    public TalismanBoardCardView(final TalismanCardView card, final boolean canBePickedUp) {
        final TalismanCardViewImpl swingCard = (TalismanCardViewImpl) card;

        final LayoutManager panelManager = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(panelManager);
        this.add(swingCard);
        if (canBePickedUp) {
            final JButton pickupButton = new JButton("Pickup");
            pickupButton.addActionListener((e) -> {
                this.setVisible(false);
                if (this.interactListener != null) {
                    this.interactListener.cardInteracted(this);
                }
            });
            this.add(pickupButton);
        }
        final JButton hideButton = new JButton("Close");
        hideButton.addActionListener((e) -> {
            this.setVisible(false);
            if (this.hiddenListener != null) {
                this.hiddenListener.cardInteracted(this);
            }
        });
        this.add(hideButton);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        this.setVisible(false);
    }

    /**
     * Gets the underlying card view.
     * 
     * @return the card view
     */
    public TalismanCardView getCard() {
        return card;
    }

    /**
     * Sets the listener waiting for a user interaction.
     * 
     * @param listener the listener
     */
    public void setInteractListener(final BoardCardListner listener) {
        this.interactListener = listener;
    }

    /**
     * Sets the listener waiting for when the card gets hidden.
     * 
     * @param listener the listener
     */
    public void setHiddenListener(final BoardCardListner listener) {
        this.hiddenListener = listener;
    }
}
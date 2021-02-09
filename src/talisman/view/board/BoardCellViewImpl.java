package talisman.view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import talisman.util.CellType;
import talisman.view.ImagePanel;

/**
 * An implementation for a swing-based cell view.
 * 
 * * @author Alberto Arduini
 *
 */
// getX and getY do not get overridden since they are already define in JComponent
public final class BoardCellViewImpl extends ImagePanel implements BoardCellView {
    private final JTextArea text;
    private final CellType type;

    /**
     * Creates a new cell.
     * 
     * @param imagePath the path to the background image
     * @param text      the text on the cell
     * @param type      the type of the cell
     */
    public BoardCellViewImpl(final String imagePath, final String text, final CellType type) {
        super(imagePath);
        this.type = type;
        final LayoutManager layout = new BorderLayout();
        this.setLayout(layout);
        this.text = new JTextArea(1, 1);
        this.text.setText(text);
        this.text.setForeground(Color.WHITE);
        this.text.setBackground(Color.BLACK);
        this.text.setLineWrap(true);
        this.text.setEditable(false);
        this.text.setAlignmentX(CENTER_ALIGNMENT);
        this.text.setAlignmentY(CENTER_ALIGNMENT);
        this.text.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.add(this.text, BorderLayout.PAGE_END);
        final Dimension size = new Dimension(225, 125);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellType getCellType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCellX() {
        if (!SwingUtilities.getRoot(this).isVisible()) {
            return 0;
        }
        return this.getLocationOnScreen().x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCellY() {
        if (!SwingUtilities.getRoot(this).isVisible()) {
            return 0;
        }
        return this.getLocationOnScreen().y;
    }
}

package talisman.controller.board;

import talisman.model.board.TalismanBoard;
import talisman.model.board.TalismanBoardCell;
import talisman.model.board.TalismanBoardSection;
import talisman.view.board.PopulatedBoardView;
import talisman.model.board.TalismanBoardPawn;

/**
 * The implementation of a basic MVC controller for a TalismanBoard.
 * 
 * @author Alberto Arduini
 *
 */
public final class TalismanBoardControllerImpl implements TalismanBoardController {
    private final TalismanBoard board;
    private final PopulatedBoardView view;

    /**
     * Creates a new controller.
     * 
     * @param board the board model to control
     * @param view  the board view
     */
    public TalismanBoardControllerImpl(final TalismanBoard board, final PopulatedBoardView view) {
        this.board = board;
        this.view = view;
        this.view.addUpdateListener(() -> {
            this.updatePawnsViewPosition();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCharacterCell(final int player, final int cell) {
        this.getBoard().movePawnTo(player, cell);
        this.updatePawnViewPosition(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCharacterSection(final int player, final int section, final int cell) {
        this.getBoard().changePawnSection(player, section, cell);
        this.updatePawnViewPosition(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardSection getCharacterSection(final int player) {
        final int sectionIndex = this.getBoard().getPawnSectionIndex(player);
        return this.getBoard().getSection(sectionIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardCell getCharacterCell(final int player) {
        final int cellIndex = this.getBoard().getPawnCellIndex(player);
        return this.getCharacterSection(player).getCell(cellIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyCharacterCellActions(final int player) {
        this.getCharacterCell(player).applyActionsTo(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardPawn getCharacterPawn(final int player) {
        return this.getBoard().getPawn(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoard getBoard() {
        return this.board;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PopulatedBoardView getView() {
        return this.view;
    }

    private void updatePawnsViewPosition() {
        for (int i = 0; i < this.getBoard().getPawnCount(); i++) {
            this.updatePawnViewPosition(i);
        }
    }

    private void updatePawnViewPosition(final int index) {
        final TalismanBoardPawn pawn = this.getCharacterPawn(index);
        this.getView().movePawnToCell(index, pawn.getPositionSection(), pawn.getPositionCell());
    }
}

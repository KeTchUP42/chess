package chess.board;

import game.core.area.BaseArea;
import game.core.area.src.Interfaces.IArea;

/**
 * @author Roman
 */
public class BoardArea extends BaseArea implements IArea {
    /**
     * @param areaSize Размер области
     */
    public BoardArea(int areaSize) {
        super(areaSize);
    }

}

package edu.grinnell.csc207.blocks;

/**
 * The ways in which things can be aligned horizontally.
 */
public enum HAlignment {
  /** Align by the left column. */
  LEFT,
  /** Align by the center column. */
  CENTER,
  /** Align by the right column. */
  RIGHT;

  /**
   * Returns the location of a column in the inner block.
   * @param col The column of the outer block.
   * @param innerWidth The width of the block that needs to be aligned.
   * @param outerWidth The width of the block that is aligning.
   * @return The equivalent column of the inner block,
   *         or -1 if the column is not in the inner block.
   */
  public int getLocation(int col, int innerWidth, int outerWidth) {
    int diff = outerWidth - innerWidth;

    if (this == HAlignment.LEFT && col >= innerWidth) {
      return -1;
    } else if (this == HAlignment.LEFT && col < innerWidth) {
      return col;
    } else if (this == HAlignment.RIGHT && col < diff) {
      return -1;
    } else if (this == HAlignment.RIGHT && col >= diff) {
      return col - outerWidth + innerWidth;
    } else {
      // We have HAlignment.CENTER
      int leftSide = diff / 2;
      int rightSide = diff - leftSide;

      if (col >= leftSide && col < (outerWidth - rightSide)) {
        return col - leftSide;
      } else {
        return -1;
      } // if-else
    } // if-else
  } // getLocation(int, int, int)
};

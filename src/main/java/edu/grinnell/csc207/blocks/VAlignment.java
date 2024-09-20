package edu.grinnell.csc207.blocks;

/**
 * Vertical alignments.
 */
public enum VAlignment {
  /** Align to the top. */
  TOP,
  /** Align to the center. */
  CENTER,
  /** Align to the bottom. */
  BOTTOM;

  /**
   * Returns the location of a row in the inner block.
   * @param row The row of the outer block.
   * @param innerHeight The height of the block that needs to be aligned.
   * @param outerHeight The height of the block that is aligning.
   * @return The equivalent row of the inner block, or -1 if the row is not in the inner block.
   */
  public int getLocation(int row, int innerHeight, int outerHeight) {
    int diff = outerHeight - innerHeight;

    if (this == VAlignment.TOP && row >= innerHeight) {
      return -1;
    } else if (this == VAlignment.TOP && row < innerHeight) {
      return row;
    } else if (this == VAlignment.BOTTOM && row < diff) {
      return -1;
    } else if (this == VAlignment.BOTTOM && row >= diff) {
      return row - outerHeight + innerHeight;
    } else {
      // We have VAlignment.CENTER
      int topSide = diff / 2;
      int bottomSide = diff - topSide;

      if (row >= topSide && row < (outerHeight - bottomSide)) {
        return row - topSide;
      } else {
        return -1;
      } // if-else
    } // if-else
  } // getLocation(int, int, int)
} // enum VAlignment


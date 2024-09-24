package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author David William Stroud
 * @author Sarah Deschamps
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("Invalid row " + i);
    } // if
    int index = 0;
    int rowOfCurrentBlock = 0;
    int heightSoFar = 0;
    findRow: for (int j = 0; j < this.blocks.length; j++) {
      heightSoFar += this.blocks[j].height();
      if (heightSoFar > i) {
        index = j;
        int blockHeight = this.blocks[j].height();
        for (int k = blockHeight; k >= 0; k--) {
          if (heightSoFar - blockHeight + k == i) {
            rowOfCurrentBlock = k;
            break findRow;
          } // if
        } // for
      } // if
    } // for
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < this.width(); j++) {
      int place = align.getLocation(j, this.blocks[index].width(), this.width());
      if (place < 0) {
        sb.append(" ");
      } else {
        // add the row all at once for better efficiency
        sb.append(this.blocks[index].row(rowOfCurrentBlock));
        j += this.blocks[index].width() - 1;
      } // if-else
    } // for
    return sb.toString();
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int totalHeight = 0;
    for (int j = 0; j < this.blocks.length; j++) {
      totalHeight += this.blocks[j].height();
    } // for
    return totalHeight;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    if (this.blocks.length == 0) {
      return 0;
    } // if
    int outerWidth = this.blocks[0].width();
    for (int j = 0; j < this.blocks.length; j++) {
      if (outerWidth < this.blocks[j].width()) {
        outerWidth = this.blocks[j].width();
      } // if
    } // for
    return outerWidth;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return other instanceof VComp && this.eqv((VComp) other);
  } // eqv(AsciiBlock)

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(VComp other) {
    if (this.blocks.length != other.blocks.length || this.align != other.align) {
      return false;
    } // if

    for (int j = 0; j < this.blocks.length; j++) {
      if (!this.blocks[j].equals(other.blocks[j])) {
        return false;
      } // if
    } // for
    return true;
  } // eqv(VComp)
} // class VComp

package edu.grinnell.csc207.blocks;

/**
 * An AsciiBlock that transposes (flips the axes of) another AsciiBlock.
 */
public class Transpose implements AsciiBlock {
  AsciiBlock block;

  /**
   * This constructor creates a Transpose block that represents the transposed version of block1.
   * 
   * @param block1 The block to transpose.
   */
  public Transpose(AsciiBlock block1) {
    this.block = block1;
  }

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @pre
   *   0 <= i < this.height()
   *
   * @exception Exception
   *   if the row number is invalid.
   */
  public String row(int i) throws Exception {
    if (i < 0 || i >= this.height()) {
      throw new Exception("No such row: " + i);
    } // if

    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < this.width(); j++) {
      sb.append(this.block.row(j).charAt(i));
    } // for
    return sb.toString();
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.block.width();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.block.height();
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
    return other instanceof Transpose && this.eqv((Transpose) other);
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
  public boolean eqv(Transpose other) {
    return this.block.eqv(other.block);
  } // eqv(Transpose)
}

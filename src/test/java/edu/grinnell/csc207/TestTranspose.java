package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.blocks.*;

/**
 * Tests of the new block.
 */
public class TestTranspose {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * This test checks whether basic lines can be transposed correctly.
   */
  @Test
  public void transposeLine() {
    Line abcd = new Line("abcd");
    Line helloWorld = new Line("helloWorld");
    Line iAmTalking = new Line("I am talking.");

    assertEquals(
      "a\nb\nc\nd\n",
      TestUtils.toString(new Transpose(abcd)),
      "abcd is transposed correctly"
    );

    assertEquals(
      "h\ne\nl\nl\no\nW\no\nr\nl\nd\n",
      TestUtils.toString(new Transpose(helloWorld)),
      "helloWorld is transposed correctly"
    );

    assertEquals(
      "I\n \na\nm\n \nt\na\nl\nk\ni\nn\ng\n.\n",
      TestUtils.toString(new Transpose(iAmTalking)),
      "iAmTalking is transposed correctly"
    );
  } // transposeLine()

  /**
   * This test checks whether Rects can be transposed correctly.
   */
  @Test
  public void transposeRect() throws Exception {
    Rect tallRect = new Rect('x', 2, 3);
    Rect longRect = new Rect('y', 3, 2);
    Rect squareRect = new Rect('z', 2, 2);

    assertEquals(
      "xxx\nxxx\n",
      TestUtils.toString(new Transpose(tallRect)),
      "tallRect is transposed correctly"
    );

    assertEquals(
      "yy\nyy\nyy\n",
      TestUtils.toString(new Transpose(longRect)),
      "longRect is transposed correctly"
    );

    assertEquals(
      "zz\nzz\n",
      TestUtils.toString(new Transpose(squareRect)),
      "squareRect is transposed correctly"
    );
  } // transposeRect()

  /**
   * This test checks whether multi-line heterogenous blocks can be transposed correctly.
   */
  @Test
  public void transposeMultiline() {
    Lines lines = new Lines(new String[] {"Holler", "if a", "need"});

    assertEquals(
      "Hin\nofe\nl e\nlad\ne  \nr  \n",
      TestUtils.toString(new Transpose(lines)),
      "lines is transposed correctly"
    );
  } // transposeMultiline()

  /**
   * This test checks whether the empty block is transposed correctly.
   */
  @Test
  public void transposeEmpty() {
    Empty empty = new Empty();

    assertEquals(
      "",
      TestUtils.toString(new Transpose(empty)),
      "empty is transposed correctly"
    );
  }

  /**
   * This test checks whether a VComp block can be transposed correctly.
   */
  @Test
  public void transposeVcomp() {
    VComp vComp = new VComp(HAlignment.CENTER, new AsciiBlock[] {
      new Line("Hello"),
      new Line("Hello, world!")
    });

    //     Hello    
    // Hello, world!

    assertEquals(
      " H\n e\n l\n l\nHo\ne,\nl \nlw\noo\n r\n l\n d\n !\n",
      TestUtils.toString(new Transpose(vComp)),
      "vComp is transposed correctly"
    );
  } // transposeVcomp
  
  /**
   * This test checks whether squares of various sizes can be transposed correctly.
   */
  @Test
  public void transposeSquares() {
    Lines squareSizeZero = new Lines(new String[] {""});
    Lines squareSizeOne = new Lines(new String[] {"a"});
    Lines squareSizeTwo = new Lines(new String[] {"ab", "cd"});
    Lines squareSizeThree = new Lines(new String[] {"abc", "def", "ghi"});

    assertEquals(
      "",
      TestUtils.toString(new Transpose(squareSizeZero)),
      "squareSizeZero is transposed correctly"
    );

    assertEquals(
      "a\n",
      TestUtils.toString(new Transpose(squareSizeOne)),
      "squareSizeOne is transposed correctly"
    );

    assertEquals(
      "ac\nbd\n",
      TestUtils.toString(new Transpose(squareSizeTwo)),
      "squareSizeTwo is transposed correctly"
    );

    assertEquals(
      "adg\nbeh\ncfi\n",
      TestUtils.toString(new Transpose(squareSizeThree)),
      "squareSizeThree is transposed correctly"
    );
  } // transposeSquares

  /**
   * This test checks whether transposing is its own inverse.
   */
  @Test
  public void transposeInverse() {
    Lines block = new Lines(new String[] {"abcd", "efgh", "ijkl"});

    assertEquals(
      TestUtils.toString(block),
      TestUtils.toString(new Transpose(new Transpose(block))),
      "the transpose of a transpose is the original block"
    );
  } // transposeInverse
} // class TestNewBlock

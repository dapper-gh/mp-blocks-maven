package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Boxed;





import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author David William Stroud
 * @author Sarah Deschamps
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    // AsciiBlock art = new Rect('^', 80, 24);
    AsciiBlock picnicGrid = new Rect('/', 3, 2);
    AsciiBlock emptyPicnicGrid = new Rect(' ', 3, 2);
    AsciiBlock blanketPart = new VComp(HAlignment.CENTER,
                                       new HComp(VAlignment.CENTER, picnicGrid, emptyPicnicGrid),
                                       new HComp(VAlignment.CENTER, emptyPicnicGrid, picnicGrid));
    AsciiBlock blanket = new Grid(blanketPart, 13, 2);
    AsciiBlock[] flowerLines = new AsciiBlock[] {new Line("(\\/\\)"),
                                                 new Line("(*)  \\_/  (*)"),
                                                 new Line("\\   |  /"),
                                                 new Line("\\  | |"),
                                                 new Line("+-------+"),
                                                 new Line("|       |"),
                                                 new Line("{         }"),
                                                 new Line("\\       /"),
                                                 new Line("\\_____/")};
    AsciiBlock flower = new VComp(HAlignment.CENTER, flowerLines);
    AsciiBlock[] cakeLines = new AsciiBlock[] {new Line("*  *  *"),
                                               new Line("|  |  |"),
                                               new Line("-----------"),
                                               new Line("|~~~~~~~~~|"),
                                               new Line("|\\/\\/\\/\\/\\|"),
                                               new Line("|_________|")};
    AsciiBlock cake = new VComp(HAlignment.CENTER, cakeLines);
    AsciiBlock items = new HComp(VAlignment.BOTTOM, new AsciiBlock[]
      {new Rect(' ', 20, 9),
        cake,
      new Rect(' ', 20, 1),
        flower});

    AsciiBlock sun = new VComp(HAlignment.LEFT, new AsciiBlock[]
      {new Line("########* -"),
      new Line("########* /"),
      new Line("#######*_\\"),
      new Line("####* _\\"),
      new Line("/ \\/")});
    AsciiBlock cloud = new VComp(HAlignment.CENTER, new AsciiBlock[]
      {new Line("_    ___"),
      new Line("_( )__(   )_"),
      new Line("_(            o__"),
      new Line("o_________________)")});
    AsciiBlock art = new Boxed(new VComp(HAlignment.LEFT, new AsciiBlock[]
      {new HComp(VAlignment.BOTTOM, new AsciiBlock[]
        {sun, new Rect(' ', 20, 1), cloud}),
        items, blanket}));
    AsciiBlock.print(pen, art);
    pen.close();
  } // main(String[])
} // class Art80x24

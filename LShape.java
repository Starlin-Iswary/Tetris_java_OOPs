
package tetrisblocks;

import tetris.TetrisBlock; //As 'LShape' & 'TetrisBlock' classes are in different packages, we imported 'TetrisBlock' class.

public class LShape extends TetrisBlock
{
    public LShape() //constructor of 'LShape' class
    {
        super( new int[][]{ {1, 0}, //'super()' calls constructor of parent class ('TetrisBlock'). Parameter of 'super()' is shape of block.
                            {1, 0},
                            {1, 1}
        });
    }
}

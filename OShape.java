package tetrisblocks;

import tetris.TetrisBlock;  //As 'OShape' & 'TetrisBlock' classes are in different packages, we imported 'TetrisBlock' class.

public class OShape extends TetrisBlock
{
    public OShape() //constructor of 'OShape' class
    {
        super( new int[][]{ {1, 1},  //'super()' calls constructor of parent class ('TetrisBlock'). Parameter of 'super()' is shape of block.
                            {1, 1}
        });
    }
}

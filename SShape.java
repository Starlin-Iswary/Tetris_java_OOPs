package tetrisblocks;

import tetris.TetrisBlock; //As 'SShape' & 'TetrisBlock' classes are in different packages, we imported 'TetrisBlock' class.

public class SShape extends TetrisBlock
{
    public SShape() //constructor of 'SShape' class
    {
        super(new int[][]{ {0, 1, 1}, //'super()' calls constructor of parent class ('TetrisBlock'). Parameter of 'super()' is shape of block.
                           {1, 1, 0}
        });
    }
}

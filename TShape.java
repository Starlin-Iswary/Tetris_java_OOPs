package tetrisblocks;

import tetris.TetrisBlock; //As 'TShape' & 'TetrisBlock' classes are in different packages, we imported 'TetrisBlock' class.

public class TShape extends TetrisBlock
{
    public TShape() //constructor of 'TShape' class
    {
        super( new int[][]{ {1, 1, 1}, //'super()' calls constructor of parent class ('TetrisBlock'). Parameter of 'super()' is shape of block.
                            {0, 1, 0}
        });
    }
}

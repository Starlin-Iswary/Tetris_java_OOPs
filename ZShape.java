package tetrisblocks;

import tetris.TetrisBlock; //As 'ZShape' & 'TetrisBlock' classes are in different packages, we imported 'TetrisBlock' class.

public class ZShape extends TetrisBlock
{
    public ZShape() //constructor of 'ZShape' class
    {
        super(new int[][]{ {1, 1, 0}, //'super()' calls constructor of parent class ('TetrisBlock'). Parameter of 'super()' is shape of block.
                           {0, 1, 1} 
        });
    }
}

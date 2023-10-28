package tetrisblocks;

import tetris.TetrisBlock; //As 'JShape' & 'TetrisBlock' classes are in different packages, we imported 'TetrisBlock' class.

public class JShape extends TetrisBlock
{
    public JShape() //constructor of 'JShape' class
    {
        super( new int[][] {    {0, 1}, //'super()' calls constructor of parent class ('TetrisBlock'). Parameter of 'super()' is shape of block.
                                {0, 1},
                                {1, 1}
        });
    }
}

package tetrisblocks;

import tetris.TetrisBlock; //As 'IShape' & 'TetrisBlock' classes are in different packages, we imported 'TetrisBlock' class.

public class IShape extends TetrisBlock
{
    public IShape() //constructor of 'IShape' class
    {
        super( new int[][]{ {1, 1, 1, 1} } ); //'super()' calls constructor of parent class ('TetrisBlock'). Parameter of 'super()' is shape of block.
    }
    
    @Override
    public void rotate() //overriding the 'rotate()' method for I-shape
    {
        super.rotate();
        
        if ( this.getWidth() == 1 ) //if the I-shape is in horizontal position
        {
            this.setX( this.getX() + 1 ); //After rotating, block will shift to right by one unit.
            this.setY( this.getY() - 1 ); //After rotating, block will shift upwards by one unit.
        }
        else //if the I-shape is in vertical position
        {
            this.setX( this.getX() - 1 ); //After rotating, block will shift to left by one unit.
            this.setY( this.getY() + 1 ); //After rotating, block will shift downwards by one unit.
        }
    }
}

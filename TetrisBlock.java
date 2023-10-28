package tetris;

import java.awt.Color;
import java.util.Random;

public class TetrisBlock
{
    private int[][] shape; //defines the shape of the tetris blocks (falling shapes)
    private Color color; //defines the color of the tetris blocks (falling shapes)
    private int x, y; //(x,y) is relative position of top left corner of block w.r.t top left corner of screen.
    private int[][][] shapes; //to store all possible rotated positions of a shape
    private int currentRotation; //tells the current rotated position
    
    private Color[] availableColors = {Color.green, Color.red, Color.blue}; //'availableColors' is array of colors of blocks.
    
    public TetrisBlock(int [][] shape) //constructor to initialise 'shape'
    {
        this.shape = shape; //'this' is used to assign the value of method parameter 'shape' to the member variable 'shape'.
                
        initShapes();
    }
    
    private void initShapes() //This method instantiates 'shapes'.
    {
        shapes = new int[4][][]; //'shapes' stores four elements one for each rotation.
        
        for (int i = 0; i < 4; i++) //This loop will execute four times one for each rotation.
        {
            int r = shape[0].length; //No. of rows in new array is no. of columns in original 'shape' array.
            int c = shape.length; //No. of columns in new array is no. of rows in original 'shape' array.
            
            shapes[i] = new int[r][c]; //instantiated a new array for rotation of shape
            
            for(int y = 0; y < r; y++) //traversing newly created array ('shapes').
            {
                for (int x = 0; x < c; x++)
                {
                    shapes[i][y][x] = shape[c - x - 1][y]; //Rows of 'shape' becomes columns of 'shapes' and vice-versa. assigning values to the newly created array ('shapes') using original 'shape' array
                }
            }
            
            shape = shapes[i]; //Now, the current rotated shape becomes original shape (as in next time we shall rotate the shape).
        }
    }
    
    public void spawn(int gridWidth) //confirms that the block starts falling from above (beyond) the screen so that it is not problem when the screen is full
    {
        Random r = new Random(); //'Random' class generates random no.
        
        currentRotation = r.nextInt( shapes.length ); //Every time a new block starts falling, its rotation will be random.
        shape = shapes[currentRotation]; //'shape' gets the shape of respective index ('currentRotation').
        
        y = -getHeight();
        x = r.nextInt(gridWidth - getWidth()); //so that blocks fall from random position of top of screen. 'r.nextInt()' generates a random no. between 0 & width of screen.
        
        color = availableColors[ r.nextInt( availableColors.length ) ]; //Random colors are chosen for blocks from 'availableColors' array.
    }
    
    public int[][] getShape(){ return shape; } //to access the 'shape' variable from outside as 'shape' is private
    
    public Color getColor(){ return color; } //to access the 'color' variable from outside as 'color' is private
    
    public int getHeight(){ return shape.length; } //Returns no. of rows of block. 'shape.length' is no. of rows of block.
    
    public int getWidth(){ return shape[0].length; } //Returns no. of columns of block. 'shape[0].length' is no. of columns of block.
    
    public int getX(){ return x; } //method to access variable 'x' from outside class
    
    public void setX(int newX){ x = newX; } //to set x-position (left edge) of block to a new position ('newX')
    
    public int getY(){ return y; } //method to access variable 'y' from outside class
    
    public void setY(int newY){ y = newY; } //to set y-position (top edge) of block to a new position ('newY')
    
    public void moveDown(){ y++; } //responsible for moving block
    
    public void moveLeft(){ x--; } //responsible for moving block
    
    public void moveRight(){ x++; } //responsible for moving block
    
    public void rotate() //responsible for rotating block
    {
        currentRotation++;
        if(currentRotation > 3) currentRotation = 0; //As 'shapes' contains four elements, after 'currentRotation' reached 3, it should be set back to 0.
        shape = shapes[currentRotation];
    }
    
    public int getBottomEdge(){ return y + getHeight(); } //returns position of bottom edge of block
    
    public int getLeftEdge(){ return x; } //returns position of left edge of block
    
    public int getRightEdge(){ return x + getWidth(); } //returns position of right edge of block which is position of left edge ('x') + width of block ('getWidth()').
}

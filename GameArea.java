package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import tetrisblocks.*; //imports all classes of 'tetrisblocks' package

public class GameArea extends JPanel //GameArea class (represented by Color[][] background) responsible for moveBlockLeft, moveBlockRight, dropBlock, rotateBlock, moveBlockDown, spawnBlock, moveBlockToBackground, clearLines & instantiating TetrisBlock (represented by int[][] shape; has color, x & y position, rotation)
{
    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] background; //to store the previous blocks
    
    private TetrisBlock block; //The data type is 'TetrisBlock'.
    
    private TetrisBlock[] blocks; //'blocks' array is used to store shapes of blocks.
    
    public GameArea(JPanel placeholder, int columns) //constructor
    {
        //placeholder.setVisible(false); //placeholder is made invisible.
        this.setBounds( placeholder.getBounds() );
        this.setBackground( placeholder.getBackground() );
        this.setBorder( placeholder.getBorder() );
        
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;        
        
        blocks = new TetrisBlock[]{ new IShape(), //'blocks' array is instantiated with all shapes of blocks.
                                    new JShape(),
                                    new LShape(),
                                    new OShape(),
                                    new SShape(),
                                    new TShape(),
                                    new ZShape() };
    }
    
    public void initBackgroundArray()
    {
        background = new Color[gridRows][gridColumns]; //'background' size is initialised with screen size.
    }
    
    public void spawnBlock()
    {
        Random r = new Random();
        
        block = blocks[ r.nextInt( blocks.length ) ]; //choosing a random shape from 'blocks' array
        block.spawn(gridColumns); //'gridColumns' is grid-width.
    }
    
    public boolean isBlockOutOfBounds() //checks whether position of upper edge of a block is beyond upper edge of screen. If position of upper edge of a block is beyond upper edge of screen, returns true; else false. 
    {
        if(block.getY() < 0) //when position of upper edge of block ('getY()') is beyond upper edge of screen
        {
            block = null; //As in this case case will be over, so we are making 'block' variable null.
            return true;
        }
        
        return false;
    }
            
    public boolean moveBlockDown() //responsible for move block down
    {
        if (checkBottom() == false) //When 'checkBottom()' is false, block stops falling.
        {            
            return false;
        }
        
        block.moveDown();
        repaint(); //brings block to new position
        
        return true;
    }
    
    public void moveBlockRight()
    {
        if( block == null ) return; //When upper edge of block goes beyond upper edge of screen (the game is over), then 'block' becomes null, then we shall not move the block to right as the game is over.
        if( !checkRight() ) return; //If the right edge of block has reached right edge of screen, no more moving to right.
        
        block.moveRight();
        repaint(); //brings block to new position. If we don't use it, block will not move smoothly to right.
    }
    
    public void moveBlockLeft()
    {
        if( block == null ) return; //When upper edge of block goes beyond upper edge of screen (the game is over), then 'block' becomes null, then we shall not move the block to left as the game is over.
        if( !checkLeft() ) return; //If the left edge of block has reached left edge of screen, no more moving to left.
        
        block.moveLeft();
        repaint(); //brings block to new position. If we don't use it, block will not move smoothly to left.
    }
    
    public void dropBlock()
    {
        if( block == null ) return; //When upper edge of block goes beyond upper edge of screen (the game is over), then 'block' becomes null, then we shall not drop the block as the game is over.
        while( checkBottom() )
        {
            block.moveDown();
        }
        
        repaint(); //brings block to new position. If we don't use it, block will not move smoothly instantly to bottom.
    }
    
    public void rotateBlock()
    {
        if( block == null ) return; //When upper edge of block goes beyond upper edge of screen (the game is over), then 'block' becomes null, then we shall not rotate the block as the game is over.
        block.rotate();
        
        if(block.getLeftEdge() < 0) block.setX(0); //After rotating, if left edge of block is out of screen, we set x-position (left edge) of block to 0.
        if(block.getRightEdge() >= gridColumns) block.setX( gridColumns - block.getWidth() ); //After rotating, if right edge of block is out of screen, we set x-position (left edge) of block to difference between width of game-area & width of block.
        if(block.getBottomEdge() >= gridRows) block.setY( gridRows - block.getHeight() ); //After rotating, if bottom edge of block is out of screen, we set y-position (top edge) of block to difference between height of game-area & height of block.
        
        repaint(); //brings block to new position. If we don't use it, block will not rotate smoothly.
    }
    
    private boolean checkBottom() //checks whether bottom edge of block reaches bottom of game area
    {
        if( block.getBottomEdge() == gridRows) //when position of bottom edge of block is same as bottom of game area
        {
            return false;
        }
        
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int col = 0; col < w; col++) //traversing 'shape' array column by column
        {
            for(int row = h - 1; row >= 0; row--) //traversing each column bottom-up
            {
                if(shape[row][col] != 0) //when we find a non-zero element of 'shape' array
                {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1; //'+1' as we need to check the portion of 'background' array which is at bottom of 'shape' array
                    if(y < 0) break; //This condition is used as the block starts falling beyond the screen (-ve 'y'). So, as long as the entire block is not in screen, no checking.
                    if(background[y][x] != null) return false; //checking element of 'background' array located immediately below the non-zero element of 'shape' array
                    break; //as no need to continue checking other elements in the same column of 'shape' array
                }
            }
        }
        
        return true;
    }
    
    private boolean checkLeft() //checks whether left edge of block reaches left edge of screen
    {
        if(block.getLeftEdge() == 0) return false; //'block.getLeftEdge()' gives position of left edge of block which is compared with position of left edge of screen (which is 0).
            
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int row = 0; row < h; row++) //traversing 'shape' array row by row
        {
            for(int col = 0; col < w; col++) //traversing each row left to right
            {
                if(shape[row][col] != 0) //when we find a non-zero element of 'shape' array
                {
                    int x = col + block.getX() - 1; //'-1' as we need to check the portion of 'background' array which is at left of 'shape' array
                    int y = row + block.getY();
                    if(y < 0) break; //This condition is used as the block starts falling beyond the screen (-ve 'y'). So, as long as the entire block is not in screen, no checking.
                    if(background[y][x] != null) return false; //checking element of 'background' array located immediately left of the non-zero element of 'shape' array
                    break; //as no need to continue checking other elements in the same row of 'shape' array
                }
            }
        }
        
        return true;
    }
    
    private boolean checkRight() //checks whether right edge of block reaches right edge of screen
    {
        if( block.getRightEdge() == gridColumns ) return false; //'block.getRightEdge()' gives position of right edge of block which is compared with position of right edge of screen (which is 'gridColumns').
            
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        
        for(int row = 0; row < h; row++) //traversing 'shape' array row by row
        {
            for(int col = w - 1; col >= 0; col--) //traversing each row right to left
            {
                if(shape[row][col] != 0) //when we find a non-zero element of 'shape' array
                {
                    int x = col + block.getX() + 1; //'+1' as we need to check the portion of 'background' array which is at right of 'shape' array
                    int y = row + block.getY();
                    if(y < 0) break; //This condition is used as the block starts falling beyond the screen (-ve 'y'). So, as long as the entire block is not in screen, no checking.
                    if(background[y][x] != null) return false; //checking element of 'background' array located immediately left of the non-zero element of 'shape' array
                    break; //as no need to continue checking other elements in the same row of 'shape' array
                }
            }
        }
        
        return true;
    }
    
    public int clearLines() //to clear complete lines
    {
        boolean lineFilled; //checks whether a line is complete or not
        int linesCleared = 0; //stores no. of lines cleared
        //To find complete lines, we need to traverse 'background' array.
        for (int r = gridRows - 1; r >= 0; r--) //traversing 'background' array row by row starting from bottom-most row
        {
            lineFilled = true;
            
            for(int c = 0; c < gridColumns; c++) //traversing each row left to right
            {
                if(background[r][c] == null) //When we encounter a null cell in a row, we stop traversing that row; as a complete line doesn't have a null cell.
                {
                    lineFilled = false;
                    break;
                }
            }
            
            if(lineFilled) //If 'lineFilled' is true, it means the line is complete. So, we clear the line by setting all elements in the line to null.
            {
                linesCleared++; //Every time a line is cleared, value of 'linesCleared' variable is incremented.
                clearLine(r); //clearing 'r'th row of screen
                shiftDown(r); //shifting down each element above 'r'th row of screen by one unit
                clearLine(0); //As there may be some elements in top-most row of screen & they may be needed to clear and as this row is not covered in 'shiftDown()', we are clearing the top-most row.
                
                r++; //As in the loop 'r' is decreasing ('r--'), we increased 'r' by one unit; so that in the next iteration, one row isn't skipped to check whether complete or not.
                
                repaint();
            }
        }
        
        if(linesCleared > 0)
        {
            Tetris.playClear();
        }
        
        return linesCleared; //returning nos. of lines cleared
    }
    
    private void clearLine(int r) //'r' is no. of line that needs to be cleared.
    {
        for(int i = 0; i < gridColumns; i++) //When a line is complete, we clear the line by setting all elements in the line to null.
        {
            background[r][i] = null;
        }
    }
    
    private void shiftDown(int r) //shifting down each element above 'r'th row of screen by one unit
    {
        for(int row = r; row > 0; row--) //traversing 'background' array row by row starting from 'r'th row (the row which is cleared) upto bottom row of top-most row of screen (row index 1); as if we go upto top-most row (row index 0), 'row - 1' inside the loop will be -1
        {
            for(int col = 0; col < gridColumns; col++) //traversing each row left to right
            {
                background[row][col] = background[row - 1][col]; //Elements of 'background' array are set to the values of the elements immediately above them.
            }
        }
    }
    
    public void moveBlockToBackground() //checks every element of current (falling) block's shape array. Made public to be able to call in 'GameThread' class
    {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        
        Color color = block.getColor();
        
        for (int r = 0; r < h; r++)
        {
            for (int c = 0; c < w; c++)
            {
                if(shape[r][c] == 1) //'shape[r][c] == 1' means it is a colored cell.
                {
                    background[r + yPos][c + xPos] = color; //The method will set the corresponding element of the background array to the color of the block
                }
            }
        }
    }
    
    private void drawBlock(Graphics g) //drawing the blocks (shapes)
    {
        int h = block.getHeight(); //saved 'block.getHeight()' locally inside 'drawBlock()' method to optimize code
        int w = block.getWidth(); //saved 'block.getWidth()' locally inside 'drawBlock()' method to optimize code
        Color c = block.getColor(); //saved 'block.getColor()' locally inside 'drawBlock()' method to optimize code
        int[][] shape = block.getShape(); //saved 'block.getShape()' locally inside 'drawBlock()' method to optimize code
        
        for(int row = 0; row < h; row++)
        {
            for (int col = 0; col < w; col++)
            {
                if(shape[row][col] == 1)
                {
                    int x = (block.getX() + col) * gridCellSize; //position of grid of block w.r.t. top left corner of screen
                    int y = (block.getY() + row) * gridCellSize; //position of grid of block w.r.t. top left corner of screen
                    
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
    
    private void drawBackground(Graphics g) //draws the 'background' array (previous blocks)
    {
        Color color; //'Color' is a reference data type. So, default value of 'color' is null. null means no color.
        
        for (int r = 0; r < gridRows; r++)
        {
            for (int c = 0; c < gridColumns; c++)
            {
                color = background[r][c];
                
                if(color != null) //We only need to draw when the color is not null.
                {
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y)
    { //These four line of code (104 - 107) is responsible for joining a single square of the grid.
        g.setColor(color); //sets the colors of the blocks
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black); //to see the grids inside the blocks
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); //'super' is used to call the 'paintComponent()' method of parent class. Only calling 'paintComponent()' will call the subclass not the superclass as overriding is used.
                  
        drawBackground(g);
        drawBlock(g);
    }
}

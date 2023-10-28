package tetris;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class GameForm extends JFrame //GameForm (main form of project) class instantiates GameArea class & GameThread class & calls some methods (moveBlockLeft, moveBlockRight, dropBlock, rotateBlock) on key press.
{
    private GameArea ga; //'ga' stores reference of the 'GameArea' object.
    private GameThread gt; //'gt' stores reference of the 'GameThread' object.
    
    public GameForm() 
    {
        initComponents();
        
        ga = new GameArea(gameAreaPlaceholder, 10); //calling 'GameArea()' constructor
        this.add( ga );
        
        initControls();
    }
    
    private void initControls() //to make key-press responsive
    {
        InputMap in = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();
        
        in.put(KeyStroke.getKeyStroke("RIGHT"), "right"); //'RIGHT' is for right arrow key. 'right' is a string label (key) associated with keystroke. 'getKeyStroke()' is a method of class 'KeyStroke'.
        in.put(KeyStroke.getKeyStroke("LEFT"), "left");
        in.put(KeyStroke.getKeyStroke("UP"), "up");
        in.put(KeyStroke.getKeyStroke("DOWN"), "down");        
        
        am.put("right", new AbstractAction(){ //'put()' adds an action to the actionmap. 1st parameter is key (string label) that we gave to keystrokes. 2nd parameter is an action (whose name is 'right') which is an object of anonymous subclass of 'AbstractAction' class. 'new AbstractAction()' creates and instantiates an anonymous class (a class which doesn't have any name) that inherits 'AbstractAction' class.
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
            }        
        });
            
        am.put("left", new AbstractAction(){ //'put()' adds an action to the actionmap. 1st parameter is key (string label) that we gave to keystrokes. 2nd parameter is an action (whose name is 'left') which is an object of anonymous subclass of 'AbstractAction' class. 'new AbstractAction()' creates and instantiates an anonymous class (a class which doesn't have any name) that inherits 'AbstractAction' class.
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();
            }        
        });
        
        am.put("up", new AbstractAction(){ //'put()' adds an action to the actionmap. 1st parameter is key (string label) that we gave to keystrokes. 2nd parameter is an action (whose name is 'up') which is an object of anonymous subclass of 'AbstractAction' class. 'new AbstractAction()' creates and instantiates an anonymous class (a class which doesn't have any name) that inherits 'AbstractAction' class.
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
            }        
        });
        
        am.put("down", new AbstractAction(){ //'put()' adds an action to the actionmap. 1st parameter is key (string label) that we gave to keystrokes. 2nd parameter is an action (whose name is 'down') which is an object of anonymous subclass of 'AbstractAction' class. 'new AbstractAction()' creates and instantiates an anonymous class (a class which doesn't have any name) that inherits 'AbstractAction' class.
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropBlock();
            }        
        });
    }
    
    public void startGame() //responsible for creating a 'GameThread' object
    {
        ga.initBackgroundArray(); //Every time the game is started, the screen is cleared.
        gt = new GameThread(ga, this); //Instantiates 'GameThread' class. 1st parameter ('ga') is reference of 'GameArea' & 2nd parameter ('this') is reference of 'GameForm' itself.
        gt.start(); //starts game. 'start()' is used so that 'GameThread()' does the task what it is supposed to do.
    }
    
    public void updateScore(int score) //responsible for updating score
    {
        scoreDisplay.setText("Score: " + score); //'setText()' updates the text of score label.
    }
    
    public void updateLevel(int level) //responsible for updating level
    {
        levelDisplay.setText("Level: " + level); //'setText()' updates the text of level label.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameAreaPlaceholder = new javax.swing.JPanel();
        scoreDisplay = new javax.swing.JLabel();
        levelDisplay = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 23, 0, 0));
        setResizable(false);

        gameAreaPlaceholder.setBackground(new java.awt.Color(238, 238, 238));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        scoreDisplay.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        scoreDisplay.setText("Score: 0");

        levelDisplay.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        levelDisplay.setText("Level: 1");

        btnMainMenu.setText("Main Menu");
        btnMainMenu.setFocusable(false);
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(levelDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scoreDisplay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(levelDisplay))
                    .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        //tells what to be done when 'Main Menu' button is clicked
        gt.interrupt(); //to terminate the thread
        this.setVisible(false);
        Tetris.showStartup();
        
    }//GEN-LAST:event_btnMainMenuActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() { //'invokeLater()' tells what to do while 'Thread' is running. 'new Runnable()' is an instance of an anonymous class that implements 'Runnable' interface and overrides its 'run()' method.
            public void run() {
                
                new GameForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JPanel gameAreaPlaceholder;
    private javax.swing.JLabel levelDisplay;
    private javax.swing.JLabel scoreDisplay;
    // End of variables declaration//GEN-END:variables
}

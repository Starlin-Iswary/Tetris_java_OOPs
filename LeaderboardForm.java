package tetris;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class LeaderboardForm extends javax.swing.JFrame {
    
    private DefaultTableModel tm; //'tm' is a member variable of type DefaultTableModel.
    
    private String leaderboardFile = "leaderboard"; //String variable 'leaderboardFile' will store name of the file which will store leaderboard data.

    private TableRowSorter<TableModel> sorter;
    
    public LeaderboardForm()
    {
        initComponents();
        initTableData(); //We need it at start & only once.
        initTableSorter();
    }
    
    private void initTableData() //responsible for initializing the member variable 'tm'
    {
        Vector ci = new Vector(); //'ci' for column-identifiers
        ci.add("Player"); //adding title of first column of table of leaderboard form
        ci.add("Score"); //adding title of second column of table of leaderboard form
        
        tm = (DefaultTableModel) leaderboard.getModel(); //'TableModel' data type is converted to 'DefaultTableModel' data type by type-casting.
        
        try
        {
            FileInputStream fs = new FileInputStream(leaderboardFile); //'leaderboardFile' passes name of the file that we want to de-serialize (load an object to the short-term memory from a file). 'FileInputStream' class is used for de-serialization.
            ObjectInputStream os = new ObjectInputStream(fs); //'ObjectInputStream' class is used for de-serialization. 'FileInputStream' object 'fs' is used to initialize 'ObjectInputStream' object 'os'.

            tm.setDataVector( (Vector<Vector>)os.readObject(), ci); //'readObject()' is used to de-serialize vector object. 'ci' passes columns' titles. 'readObject()' returns 'Object' type which is type-casted to 'Vector' type.

            os.close();
            fs.close();
        }
        catch(Exception e){} //We left body of 'catch' block blank as nothing to happen when an exception happens.
    }
    
    private void initTableSorter()
    {
        sorter = new TableRowSorter<>(tm); //created 'TableRowSorter' object
        leaderboard.setRowSorter(sorter); //assigning table row sorter ('sorter') to table of leaderboard form by 'setRowSorter()' method
        
        ArrayList<SortKey> keys = new ArrayList<>(); //'keys' is a arraylist of type 'SortKey' which stores rows of table of leaderboard form.
        keys.add( new SortKey(1, SortOrder.DESCENDING) ); //Adding a new 'SortKey' object to the 'keys' arraylist. 1 is index of second column (score) according to which we want to sort rows of table of leaderboard form in descending order.
        
        sorter.setSortKeys(keys); //feeding list ('keys') of sorting rules to 'sorter'
    }
    
    private void saveLeaderboard() //incharge of saving leaderboard data
    {
        try
        {
            FileOutputStream fs = new FileOutputStream(leaderboardFile); //'FileOutputStream' class is used for serialization (saving an object to a file).
            ObjectOutputStream os = new ObjectOutputStream(fs); //'ObjectOutputStream' class is used for serialization (saving an object to a file). 'FileOutputStream' object 'fs' is used to initialize 'ObjectOutputStream' object 'os'.

            os.writeObject( tm.getDataVector() ); //'writeObject()' is used to serialize vector object.

            os.close();
            fs.close();
        }
        catch(Exception e){} //We left body of 'catch' block blank as nothing to happen when an exception happens.    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMainMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaderboard = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        btnMainMenu.setText("Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        leaderboard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player", "Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(leaderboard);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        //tells what to be done when 'Main Menu' button is clicked
        this.setVisible(false);
        Tetris.showStartup();
        
    }//GEN-LAST:event_btnMainMenuActionPerformed

    public void addPlayer(String playerName, int score)
    {
        tm.addRow( new Object[] {playerName, score} ); //adding a new row in leaderboard form. Type of 'playerName' is 'String', type of 'score' is 'int'; but these are passed in an array of type 'Object' as in JAVA, any data type can be treated as 'Object' data type.
        sorter.sort(); //sorting rows of table of leaderboard form, every time a new player is added
        
        saveLeaderboard(); //This method should be called, every time a new player is added.
        
        this.setVisible(true); //makes leaderboard form visible
    }
    
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
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaderboardForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaderboardForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaderboard;
    // End of variables declaration//GEN-END:variables
}

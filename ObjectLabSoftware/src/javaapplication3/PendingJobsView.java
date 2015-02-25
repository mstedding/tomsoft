package javaapplication3;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matt
 */
public class PendingJobsView extends javax.swing.JFrame
{
    /* allFileTableModel:
     * Used to hold data to display to user using the JTable class
     * Data is gathered from DB. THE D
     */
    public static DefaultTableModel allFileTableModel = null;
    static SQLMethods dba = null;
    static Reports reports = null;
    static InstanceCall inst = null;
    private static RejectDescription WhyReject = null;
    private static ApprovePage Approve = null;
    private static Options ops = null;
    //PendingUpdater pUpdate = null;
    String fileLocation = "";

    public void PendingJobsStart() 
    {
        ops = new Options();
        inst = new InstanceCall();
        reports = new Reports();
        dba = new SQLMethods();
        
        /* Creates are PendingJobs UI window componet and grabs its data model for our uses */
        initComponents();
        allFileTableModel = (DefaultTableModel) PendingTable.getModel();
        
        /* Updates table */
        updatePendingTableData();
        
        setVisible(true);
    }
    
    private static void clearPendingTable()
    {
        // clear existing rows
        while (allFileTableModel.getRowCount() > 0) 
            allFileTableModel.removeRow(0);
    }
    
    /* Returns true if updated
     * false if nothing to update
    */
    private static boolean updatePendingTable() throws FileNotFoundException, IOException, SQLException
    {
        ResultSet results = dba.searchPending();
        
        if(!results.next())
            return false;
        
        while (results.next()) 
        {
            // Build a Vector of Strings for the table row
            List<String> data = new LinkedList<>();
            data.add(results.getString("fileName"));
            data.add(results.getString("firstName") + " " + results.getString("lastName"));
            data.add(results.getString("printer"));
            data.add(results.getString("dateStarted"));
            /* Data retrieved from query is added into our object that we can use to display in the JTable */
            allFileTableModel.addRow(data.toArray());
        }
        
        return true;
    }

    /* This actually gets the pending jobs by quering the DB */
    public static void updatePendingTableData()  
    {
        //SQLMethods inj = new SQLMethods();
        
        clearPendingTable();
        try
        { 
            updatePendingTable();
        }
        catch (IOException | SQLException e)
        {
            System.out.println(e);
        }
        // While there are more results to process
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ApprovedButton = new javax.swing.JButton();
        RejectButton = new javax.swing.JButton();
        openFileInProgram = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        PendingTable = new javax.swing.JTable();
        backToMainMenu = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Art 101-001\nArt 201-002\nArt 401-004\nArt 501-005\nArt 601-006\nArt 701-007\nArt 801-009");
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pending Jobs");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Pending Jobs");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 470, 10));

        ApprovedButton.setText("Approve");
        ApprovedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApprovedButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ApprovedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, -1, -1));

        RejectButton.setText("Reject");
        RejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RejectButtonActionPerformed(evt);
            }
        });
        getContentPane().add(RejectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        openFileInProgram.setText("Review File");
        openFileInProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileInProgramActionPerformed(evt);
            }
        });
        getContentPane().add(openFileInProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 140, 20));

        PendingTable.setAutoCreateRowSorter(true);
        PendingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Project Name", "Student Name", "Printer", "Date Submitted"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(PendingTable);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 470, 250));

        backToMainMenu.setText("Back to Main Menu");
        backToMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainMenuActionPerformed(evt);
            }
        });
        getContentPane().add(backToMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 130, 20));

        jMenu1.setText("File");

        jMenuItem1.setText("Reports");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Class Settings");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem2.setText("Contents");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void RejectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RejectButtonActionPerformed
        // TODO add your handling code here:
        reject();
    }//GEN-LAST:event_RejectButtonActionPerformed
    
    private void reject() 
    {
        /*
            Object allFileTableModel.getValueAt(row, column)
          */
        int userSelectedRow = PendingTable.getSelectedRow();
        
        if (PendingTable.getSelectedRow() > -1) 
        {
            WhyReject = new RejectDescription();
            //String projectName = allFileTableModel.get;
            /* For loop moved into UtilController in a method called getSelectedRow */
            for (int i = 0; i < allFileTableModel.getRowCount(); i++) 
            {
                if (allFileTableModel.getValueAt(i, 0).equals(allFileTableModel.getValueAt(userSelectedRow, 0))) 
                {
                    /* rejectStudentSubmission -- to be moveed into UtilController
                    ( 
                        int, 
                        (String) allFileTableModel.getValueAt(userSelectedRow, 0), 
                        (String) allFileTableModel.getValueAt(userSelectedRow, 1),
                        (String) allFileTableModel.getValueAt(userSelectedRow, 3)
                    );
                    */
                    
                    WhyReject.rejectDesc
                    (
                      i, 
                      (String) allFileTableModel.getValueAt(userSelectedRow, 0),
                      (String) allFileTableModel.getValueAt(userSelectedRow, 1),
                      (String) allFileTableModel.getValueAt(userSelectedRow, 3)
                    );
                }
            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(new java.awt.Frame(), "Please select an item to reject!");
        }
    }
    
    private void ApprovedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApprovedButtonActionPerformed
        if (PendingTable.getSelectedRow() > -1) 
        {

            int i;
            String firstName = "";
            String lastName = "";
            String FileName = "";
            String DateStarted = "";
            String printer = "";
            
            /* Put all of this in a method in SQLUtils called getDataFromUserSelectedRow */
            for (i = 0; i < allFileTableModel.getRowCount(); i++) 
            {
                /* If fileName matches a single row in allFileTableModel that the user selected then get that data to query the DB
                 * for the full filePath.
                 */
                if (allFileTableModel.getValueAt(i, 0).equals(allFileTableModel.getValueAt(PendingTable.getSelectedRow(), 0))) 
                {
                    String studentName = allFileTableModel.getValueAt(i, 1).toString();
                    String[] splited = studentName.split(" ");
                    firstName = splited[0];
                    lastName = splited[1];
                    printer = allFileTableModel.getValueAt(i, 2).toString();
                    FileName = allFileTableModel.getValueAt(i, 0).toString();
                    DateStarted = allFileTableModel.getValueAt(i, 3).toString();
                }
            }
            
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(FileName);
            System.out.println(DateStarted);
            
            ResultSet result = PendingJobsView.dba.searchID("pendingjobs", firstName, lastName, FileName, DateStarted);
            String ID = "";
            
            try 
            {
                while (result.next()) 
                    ID = result.getString("idJobs");
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ApprovePage.class.getName()).log(Level.SEVERE, null, ex);
            }

            ResultSet res = PendingJobsView.dba.searchPendingWithID(ID);
            try 
            {
                while (res.next()) 
                    fileLocation = res.getString("filePath");
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ApprovePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Approve = new ApprovePage();
            Approve.Approves(FileName, printer, fileLocation, ID);
        } 
        else 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Please select a file to approve!");
        }
    }//GEN-LAST:event_ApprovedButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        reports.ReportsPage();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + inst.getPDFAdmin());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");  //print the error
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        ops.OptionsStart();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void openFileInProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileInProgramActionPerformed
        int selectedRow = PendingTable.getSelectedRow();
        if (selectedRow > -1) 
        {
            int i;
            String firstName = "";
            String lastName = "";
            String FileName = "";
            String DateStarted = "";
            String printer = "";

            for (i = 0; i < allFileTableModel.getRowCount(); i++) 
            {
                if (allFileTableModel.getValueAt(i, 0).equals(allFileTableModel.getValueAt(selectedRow, 0))) 
                {
                    String studentName = allFileTableModel.getValueAt(i, 1).toString();
                    String[] splited = studentName.split(" ");
                    firstName = splited[0];
                    lastName = splited[1];
                    printer = allFileTableModel.getValueAt(i, 2).toString();
                    FileName = allFileTableModel.getValueAt(i, 0).toString();
                    DateStarted = allFileTableModel.getValueAt(i, 3).toString();
                }
            }
            
            System.out.println(printer);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(FileName);
            System.out.println(DateStarted);
            
            
            
            try 
            {
                ResultSet result = PendingJobsView.dba.searchID("pendingjobs", firstName, lastName, FileName, DateStarted);
                if (result.next())
                    Desktop.getDesktop().open(new File(result.getString("filePath")));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ApprovePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex) 
            {
                Logger.getLogger(PendingJobsView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else 
        {
            JOptionPane.showMessageDialog(new JFrame(), "Please select a file to Review!");
        }
    }//GEN-LAST:event_openFileInProgramActionPerformed

    private void backToMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainMenuActionPerformed
        // TODO add your handling code here:
        dispose();
        new TomSoftMain().setVisible(true); 
    }//GEN-LAST:event_backToMainMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ApprovedButton;
    public static javax.swing.JTable PendingTable;
    private javax.swing.JButton RejectButton;
    private javax.swing.JButton backToMainMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton openFileInProgram;
    // End of variables declaration//GEN-END:variables
}
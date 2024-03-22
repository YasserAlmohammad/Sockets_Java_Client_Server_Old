package client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import com.borland.jbcl.layout.XYLayout;
import com.borland.jbcl.layout.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.*;
import java.io.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ClientFrame extends JFrame {
    JPanel contentPane;
    JLabel statusBar = new JLabel();
    BorderLayout borderLayout1 = new BorderLayout();
    JTextField jTextField1 = new JTextField();
    JPanel northPanel = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JPanel fileNamePanel = new JPanel();
    JLabel jLabel1 = new JLabel();
    JTextField filePathEdit = new JTextField();
    JPanel serverNamePanel = new JPanel();
    JLabel jLabel2 = new JLabel();
    JTextField serverNameEdit = new JTextField();
    JButton jButtonLoadFile = new JButton();
    JTextArea content = new JTextArea();
    public ClientFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Component initialization.
     *
     * @throws java.lang.Exception
     */
    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(borderLayout1);
        setSize(new Dimension(400, 300));
        setTitle("client window");
        statusBar.setText(" ");
        northPanel.setLayout(flowLayout1);
        northPanel.setMinimumSize(new Dimension(171, 100));
        northPanel.setPreferredSize(new Dimension(416, 90));
        jLabel1.setText("file path");
        filePathEdit.setPreferredSize(new Dimension(300, 21));
        filePathEdit.setText("");
        jLabel2.setText("server name");
        serverNameEdit.setPreferredSize(new Dimension(100, 21));
        serverNameEdit.setText("localhost");
        jButtonLoadFile.setText("load file");
        jButtonLoadFile.addActionListener(new
                ClientFrame_jButtonLoadFile_actionAdapter(this));
        contentPane.add(statusBar, java.awt.BorderLayout.SOUTH);
        contentPane.add(northPanel, java.awt.BorderLayout.NORTH);
        serverNamePanel.add(jLabel2);
        serverNamePanel.add(serverNameEdit);
        serverNamePanel.add(jButtonLoadFile);
        contentPane.add(content, java.awt.BorderLayout.CENTER);
        northPanel.add(fileNamePanel);
        fileNamePanel.add(jLabel1);
        fileNamePanel.add(filePathEdit);
        northPanel.add(serverNamePanel);
    }

    public void jButtonLoadFile_actionPerformed(ActionEvent e) {
        String filepath = filePathEdit.getText();
        Socket client = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            client = new Socket(serverNameEdit.getText(), 4000);
            out = new PrintWriter(client.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }

        catch (UnknownHostException ex) {
           JOptionPane.showMessageDialog(this,"could not connect to server");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,"could not connect to server");
        }
        try{
        if(out!=null){
            out.println(filePathEdit.getText());
            String line;
            this.content.setText("");
            while((line=in.readLine())!=null){
                this.content.append(line);
                this.content.append("\n");
            }
        }
        }
        catch(Exception ex){

        }
        try{
            if(client!=null)
                client.close();
            if(in!=null)
                in.close();
            if(out!=null)
                out.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this,"couldn't close stream");
        }
    }
}


class ClientFrame_jButtonLoadFile_actionAdapter implements ActionListener {
    private ClientFrame adaptee;
    ClientFrame_jButtonLoadFile_actionAdapter(ClientFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonLoadFile_actionPerformed(e);
    }
}

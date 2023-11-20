
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Client extends javax.swing.JFrame {

    Socket socket;
    int port = 5000;
    InetAddress address;
    DataInputStream input;
    static DataOutputStream output;
    String username;
    String ServerAddress;

    public Client() {

        initComponents();
    }
    public static void sendPrivateChat(String dest) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String sentMessage = "private#" + dest + "#" + PublicVar.message;
                    output.writeUTF(sentMessage);
                    output.flush();

                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();

    }
    @SuppressWarnings("unchecked")
    
    private void initComponents() {
    
    jButton1 = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    jLabel1 = new javax.swing.JLabel();
    ConnectToServerButton = new javax.swing.JButton();
    ConnectedLabel = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    TypeMessageTextArea = new javax.swing.JTextArea();
    SendButton = new javax.swing.JButton();
    jScrollPane3 = new javax.swing.JScrollPane();
    PrivateListList = new javax.swing.JList<>();
    jLabel3 = new javax.swing.JLabel();
    StartPrivateButton = new javax.swing.JButton();

    
    getContentPane().setLayout(new java.awt.GridBagLayout());
    java.awt.GridBagConstraints gridBagConstraints;

    
    jButton1.setText("jButton1");

   
    jTextArea1.setEditable(false);
    jTextArea1.setColumns(20);
    jTextArea1.setFont(new java.awt.Font("Arial", 1, 18));
    jTextArea1.setForeground(new java.awt.Color(102, 102, 255));
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    
    jLabel1.setFont(new java.awt.Font("Arial", 2, 15));
    jLabel3.setForeground(new java.awt.Color(255, 165, 0));
    jLabel1.setText("Welcome you in IM Chating");

    
    ConnectToServerButton.setBackground(java.awt.Color.decode("#9DB84E"));
    ConnectToServerButton.setFont(new java.awt.Font("Arial", 1, 12));
    ConnectToServerButton.setForeground(new java.awt.Color(0, 0, 0));
    ConnectToServerButton.setText("Connect");
    ConnectToServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    ConnectToServerButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ConnectToServerButtonActionPerformed(evt);
        }
    });

    
    ConnectedLabel.setFont(new java.awt.Font("Arial", 1, 12));
    ConnectedLabel.setForeground(new java.awt.Color(255, 0, 102));
    ConnectedLabel.setText("Offline");

    
    TypeMessageTextArea.setColumns(20);
    TypeMessageTextArea.setFont(new java.awt.Font("Arial", 0, 14));
    jLabel3.setForeground(new java.awt.Color(171, 104, 43));
    TypeMessageTextArea.setRows(5);
    TypeMessageTextArea.setText("Type Your Message here");
    TypeMessageTextArea.setName("msg");
    TypeMessageTextArea.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            TypeMessageTextAreaFocusGained(evt);
        }
    });
    jScrollPane2.setViewportView(TypeMessageTextArea);

    
    SendButton.setBackground(new java.awt.Color(171, 104, 43));
    SendButton.setFont(new java.awt.Font("Arial", 1, 12));
    SendButton.setForeground(new java.awt.Color(255, 255, 255));
    SendButton.setText("Send");
    SendButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    SendButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            SendButtonActionPerformed(evt);
        }
    });

    jScrollPane3.setViewportView(PrivateListList);

    
    jLabel3.setFont(new java.awt.Font("Arial", 1, 11));
    jLabel3.setForeground(new java.awt.Color(255, 165, 0));
    jLabel3.setText("Choose a user to private chat with");

    
    StartPrivateButton.setBackground(new java.awt.Color(171, 104, 43));
    StartPrivateButton.setFont(new java.awt.Font("Arial", 1, 12));
    StartPrivateButton.setForeground(new java.awt.Color(255, 255, 255));
    StartPrivateButton.setText("Start private chat");
    StartPrivateButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            StartPrivateButtonActionPerformed(evt);
        }
    });

    
    getContentPane().setBackground(java.awt.Color.decode("#FFF6B3"));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx= 0;
    gridBagConstraints.gridy = 0;
    getContentPane().add(jLabel1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    getContentPane().add(ConnectToServerButton, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    getContentPane().add(ConnectedLabel, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    getContentPane().add(jScrollPane1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    getContentPane().add(jScrollPane2, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    getContentPane().add(SendButton, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.5;
    getContentPane().add(jScrollPane3, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    getContentPane().add(jLabel3, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    getContentPane().add(StartPrivateButton, gridBagConstraints);

    pack();
}

    private void ConnectToServerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                
        if(!connected){
            ProcessClientConnection();
            ConnectToServerButton.setText("Dissconnect");
            ConnectToServerButton.setBackground(Color.red);
            ConnectedLabel.setText("Online");
            ConnectedLabel.setForeground(new java.awt.Color(0, 0, 255));
            connected=true;
        }else{
            ProcessClientDissconnection(); 
            ConnectToServerButton.setText("Connect");
            ConnectToServerButton.setBackground(Color.green);
            connected=false;
            ConnectedLabel.setText("Offline");
            ConnectedLabel.setForeground(new java.awt.Color(255, 0, 102));
        }
            }
                
            
        });
        thread.start();	
	
    }
    private void ProcessClientDissconnection(){
        DisconnectClient();
    }
    private void ProcessClientConnection(){
    boolean finish=false;
        while(true){
            username = JOptionPane.showInputDialog(this, " Enter Username 10 or less charactes ","");
            ServerAddress=JOptionPane.showInputDialog(this,"Enter Server IP","10.6.204.193");
            if((username.length()<=10 &&username.length()>0)&&(ServerAddress.length()!=0 )){
                break;
            }else{
                JOptionPane.showMessageDialog(this, "Name should be 10 or less charactes");
            }
            
        }
        try {
            jLabel1.setText("Welcome Client "  + username + " in IMChating ");
            address = InetAddress.getByName(ServerAddress);
            socket = new Socket(address, port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String dataReg = "reg#" + username;
                    output.writeUTF(dataReg);
                    output.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        thread.start();
        ProcessClientChat();
}
    private void ProcessClientChat(){
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String receivedData = input.readUTF();
                       System.out.println(receivedData);
                        String[] receivedDataSpliter = receivedData.split("#");
                      if (receivedDataSpliter[0].equals("clients")) {
                            PublishClientToList(receivedData);
                        } 
                      else  if (receivedDataSpliter[0].equals("pubmsg")) {
                            jTextArea1.append(receivedDataSpliter[1] + ":" + receivedDataSpliter[2] + "\n");
                        }
                      else if (receivedDataSpliter[0].equals("primsg")) {

                           if (PublicVar.privateChatStore.containsKey(receivedDataSpliter[1])) {
                             PublicVar.privateChatStore.get(receivedDataSpliter[1]).jTextArea1.append(receivedDataSpliter[1] + ":" +receivedDataSpliter[2]+ "\n");

                            } 
                            else if (!PublicVar.oldChatStore.containsKey(receivedDataSpliter[1])) {
                                 PrivateChat pc = new PrivateChat(receivedDataSpliter[1]);
                             
                                PublicVar.CH = receivedDataSpliter[1];
                                PublicVar.privateChatStore.put(receivedDataSpliter[1], pc);
                                pc.show();
                             PublicVar.privateChatStore.get(receivedDataSpliter[1]).jTextArea1.append(receivedDataSpliter[1] + ":" +receivedDataSpliter[2]+ "\n");
                            } else {

                                 PublicVar.privateChatStore.put(receivedDataSpliter[1], PublicVar.oldChatStore.get(receivedDataSpliter[1]));
                                 PublicVar.privateChatStore.get(receivedDataSpliter[1]).show();
                             PublicVar.privateChatStore.get(receivedDataSpliter[1]).jTextArea1.append(receivedDataSpliter[1] + ":" +receivedDataSpliter[2]+ "\n");

                        }
                      }

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        thread2.start();
    }
    private void PublishClientToList(String data){
        data=data.replace("clients#", "");
        //System.out.println("The data from server is : "+data);
        String dataSplitter[]=data.split("#");
        DefaultListModel elements = new DefaultListModel();
        for (int i = 0; i < dataSplitter.length; i++) {
            if (!dataSplitter[i].equals(username)) {
                elements.addElement(dataSplitter[i]);
                
            }
        }
        PrivateListList.setModel(elements);
    }
    private void ClearListModel(){
        DefaultListModel listModel = (DefaultListModel) PrivateListList.getModel();
        listModel.removeAllElements();
        
    }
    private void DisconnectClient(){
        try {
            ClearListModel();
            output.writeUTF("end");
            output.flush();
            input.close();
            output.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {
                 String message = TypeMessageTextArea.getText();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String sentMessage = "public#" + message;
                    output.writeUTF(sentMessage);
                    output.flush();

                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                 TypeMessageTextArea.setText("");
            }
        });
        thread.start();
    }

    private void StartPrivateButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if(!PublicVar.privateChatStore.containsKey(PrivateListList.getSelectedValue()) && !PublicVar.oldChatStore.containsKey(PrivateListList.getSelectedValue())){
        PrivateChat pc = new PrivateChat(PrivateListList.getSelectedValue());
        PublicVar.privateChatStore.put(PrivateListList.getSelectedValue(), pc);
           PublicVar.privateChatStore.get(PrivateListList.getSelectedValue()).show();
        }
        else if(PublicVar.oldChatStore.containsKey(PrivateListList.getSelectedValue())){
            PublicVar.privateChatStore.put(PrivateListList.getSelectedValue(), PublicVar.oldChatStore.get(PrivateListList.getSelectedValue()));
                PublicVar.oldChatStore.get(PrivateListList.getSelectedValue()).show();
                PublicVar.oldChatStore.remove(PrivateListList.getSelectedValue()).show();
         }
        else{
               PublicVar.privateChatStore.get(PrivateListList.getSelectedValue()).show();
        }
        
    }

    private void TypeMessageTextAreaFocusGained(java.awt.event.FocusEvent evt) {
        TypeMessageTextArea.setText("");
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        DisconnectClient();
    }

    
    public static void main(String args[]) {
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    
    private static javax.swing.JButton ConnectToServerButton;
    private javax.swing.JLabel ConnectedLabel;
    public static javax.swing.JList<String> PrivateListList;
    private javax.swing.JButton SendButton;
    private javax.swing.JButton StartPrivateButton;
    private javax.swing.JTextArea TypeMessageTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    
    private boolean connected=false;
}


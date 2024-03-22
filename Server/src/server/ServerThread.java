package server;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ServerThread extends Thread{
    ServerSocket server=null;
    static boolean listen=false;
    int portNumber=4000;
    ServerFrame par;
    ServerThread(ServerFrame par){
        listen=true;
        this.par=par;
        try{
            server = new ServerSocket(portNumber);
        }
        catch(Exception ex){
            listen=false;
            JOptionPane.showMessageDialog(null,"couldn't create server that listens to posrt "+portNumber);
        }
    }
    public void run(){
        try {
        while(listen){
            new ClientThread(server.accept(),par).start();
        }
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"error happened while trying to accept incloming connections");
        }
    }
}

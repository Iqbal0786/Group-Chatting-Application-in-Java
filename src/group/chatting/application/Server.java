
package group.chatting.application;

import java.io.*;
import java.util.*;
import java.net.*;


public class Server  implements Runnable{
    
    Socket socket;
    public static Vector client= new Vector();
    public Server(Socket socket){
        try {
             this.socket= socket;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public static void main(String[] args){
        try {
             ServerSocket s= new ServerSocket(2004);
             while (true) {                
                Socket socket= s.accept();
                Server server= new Server(socket);
                Thread thread= new Thread(server);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public void run(){
        try {
            BufferedReader reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            client.add(writer);
            while (true) {                
                String data=reader.readLine().trim();
                System.out.println("Recieved"+ data);
                for (int i = 0; i <client.size(); i++) {
                    BufferedWriter writer1= (BufferedWriter)client.get(i);
                    writer1.write(data);
                    writer1.write("\r\n");
                    writer1.flush();
                    
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}

package co.unicauca.p2pchat.server.peerthreads;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerThread extends Thread{
    
   private final ServerSocket serverSocket;
   private final Set<ServerThreadThreads> serverThreadThreads = new HashSet<>();
   
   public ServerThread(String portNumb) throws IOException{
       serverSocket = new ServerSocket(Integer.valueOf(portNumb));
   }
    @Override
      public void run(){
       try{
           while(true){
               ServerThreadThreads serverThreadThread  = new ServerThreadThreads(serverSocket.accept(), this);
               serverThreadThreads.add(serverThreadThread);
               serverThreadThread.start();
           }
       }catch(IOException e)
       {
           e.printStackTrace();
       }
   
   }
   
   public void sendMessage(String message){
       try{
          serverThreadThreads.forEach(t-> t.getPrintWriter().println(message));
       }catch(Exception e){
           
           e.printStackTrace();
       }
   }
   
  

public Set<ServerThreadThreads> getServerThreadThreads(){
       return serverThreadThreads;
   }
}

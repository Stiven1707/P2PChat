package co.unicauca.p2pchat.server.peerthreads;

import co.unicauca.p2pchat.server.peerthreads.ServerThread;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



class ServerThreadThreads extends Thread{
    private ServerThread serverThread;
   private Socket socket;
   
   private PrintWriter printWriter;
   
   public ServerThreadThreads(Socket socket, ServerThread serverThread){
       this.serverThread = serverThread;
       this.socket = socket;
   }
   
   
   public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}


public PrintWriter getPrintWriter(){
       return printWriter;
   }
   
    @Override
   public void run(){
       try{
           BufferedReader bufferedReader = new BufferedReader(new  InputStreamReader(this.socket.getInputStream()));
           this.printWriter = new PrintWriter(socket.getOutputStream(), true);
           while(true){
               serverThread.sendMessage(bufferedReader.readLine());
           }
       }catch(Exception e){
              serverThread.getServerThreadThreads().remove(this);
       }
   }
}

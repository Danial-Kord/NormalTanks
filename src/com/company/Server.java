package com.company;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this code manage server for connecting to client
 */
public class Server implements Runnable{
    private int count = 0;
    private GameState state;
    public Server(GameState state) {
        this.state = state;
    }
    @Override
    public void run() {
            ExecutorService pool = Executors.newCachedThreadPool();

            try (ServerSocket server = new ServerSocket(7654)) {
                System.out.print("Server started.\nWaiting for a client ... ");
                while (count <= 1) {
                    Socket client = server.accept();
                    count++;
                    System.out.println("client accepted!");
                    GameFrame.setConected(true);
                    pool.execute(new ClientHandler(client,state));
                }
                GameFrame.setConected(false);
                pool.shutdown();
                System.out.print("done.\nClosing serverImage ... ");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("done.");
        }
    }

/**
 * manage streams
 */
class ClientHandler implements Runnable {

    private Socket client;
    private GameState state;
    public ClientHandler(Socket client ,GameState state) {
        this.client = client;
        this.state = state;
    }

    @Override
    public void run() {

        try {


                try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream())) {
                    ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                    boolean sent =false;
                    while (!GameState.isGameOver()) {
                        sent =false;
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        if(state.getChanges().getBuildings().size() != 0 || state.getChanges().getTirs().size() != 0 || GameState.isChanged()) {
//                            out.writeInt(state.getChanges().getBuildings().size());
//                            for (int i=0;i<state.getChanges().getBuildings().size();i++) {
//                                System.out.println("sent");
//                                out.writeObject(state.getChanges().getBuildings().get(i));
//                                System.out.println(state.getChanges().getBuildings().get(0).getHealth());

//                            }
                            out.writeObject(state.getChanges());
                            sent = true;
//                            System.out.println("sent");
                            out.reset();
                            out.flush();
//                        }
                        if(sent){
                            Changes changes = new Changes();
                            state.setChanges(changes);
                        }


//                        if(sent) {
                            Changes changes = (Changes) in.readObject();
////                            System.out.println("server recived");
//////                            System.out.println(changes.getBuildings().get(changes.getBuildings().size()-1).locY);
//////                                System.out.println("client recived");
//////                                    System.out.println(buildings.get(0).locX);
//                            if (changes.getBuildings().size() != 0)
//                                state.getChanges().setRecivedBuildings(changes.getBuildings());
                            if (changes.getTirs().size() != 0)
                                state.getChanges().setRecivedTirs(changes.getTirs());
                            if(changes.getTankHuman() != null)
                            state.getChanges().setRecived(changes.getTankHuman());
//                            if (changes.getMilitaryTools().size() != 0)
//                                state.getChanges().setRecivedMilitaryTools(changes.getMilitaryTools());

//                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            System.out.print("All messages sent.\nClosing client ... ");
        }  finally {
            try {
                client.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void sendInformation(){

    }
}

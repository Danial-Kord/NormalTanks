package com.company;

import java.io.*;
import java.util.ArrayList;

/**
 * write and read information
 */
public class FileSetting {
    private static File info;
    private  ArrayList<Building> buildings;
    private  ArrayList<MilitaryTool> militaryTools;
    private TankHuman tankHuman;

    public FileSetting() {
            info = new File("src\\save\\info.akdk");
    }


    public  void writing(GameState state) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(info.getPath())) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(state.getBuildings());
                objectOutputStream.writeObject(state.getMilitaryTools());
                objectOutputStream.writeObject(state.getTank());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void reading() {
        ArrayList<Building> buildings = new ArrayList<Building>();
        ArrayList<MilitaryTool> militaryTools = new ArrayList<MilitaryTool>();
        if (info.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(info.getPath())) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                buildings = (ArrayList<Building>) objectInputStream.readObject();
                militaryTools = (ArrayList<MilitaryTool>) objectInputStream.readObject();
                tankHuman = (TankHuman)objectInputStream.readObject();
                this.buildings = buildings;
                this.militaryTools = militaryTools;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public TankHuman getTankHuman() {
        return tankHuman;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<MilitaryTool> getMilitaryTools() {
        return militaryTools;
    }
}

//    public static ArrayList<MilitaryTool> readMilitarytools(){
//        ArrayList<MilitaryTool>militaryTools = new ArrayList<MilitaryTool>();
//        if(info.exists()) {
//            try (FileInputStream fileInputStream = new FileInputStream(info.getPath())) {
//                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//                militaryTools = (ArrayList<Building>) objectInputStream.readObject();
//
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return militaryTools;
//    }


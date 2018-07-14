package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Changes implements Serializable{
    private ArrayList<Building>buildings;
    private ArrayList<Tir>tirs;
    private ArrayList<MilitaryTool>militaryTools;
    private transient ArrayList<Tir>recivedTirs;
    private transient ArrayList<Building>recivedBuildings;
    private transient ArrayList<MilitaryTool>recivedMilitaryTools;
    public Changes(){
        militaryTools = new ArrayList<MilitaryTool>();
        recivedMilitaryTools = new ArrayList<MilitaryTool>();
        recivedBuildings = new ArrayList<Building>();
        buildings=new ArrayList<Building>();
        tirs=new ArrayList<Tir>();
        recivedTirs = new ArrayList<Tir>();
    }
        public void update(ArrayList<Building>newBuildings,ArrayList<Tir>newTirs,TankHuman tankHuman,GameState state){
        for (int i=0;i<newBuildings.size();i++){
//            Building building = new Building(newBuildings.get(i).locX,newBuildings.get(i).locY);
//            building.setHealth(newBuildings.get(i).getHealth());
//            building.setDie(newBuildings.get(i).isDie());
            buildings.add((Building) newBuildings.get(i));
            System.out.println("added");
        }
        for (int i=0;i<newTirs.size();i++){
            //TODO
            Tir tir = new Tir(newTirs.get(i).getFirstX()+tankHuman.getDeltaX(),newTirs.get(i).getFirstY()+tankHuman.getDeltaY(),newTirs.get(i).getDelytaY(),newTirs.get(i).getDeltaX(),
                    newTirs.get(i).getShib(),null,newTirs.get(i).getFirstTankX()+tankHuman.getDeltaX(),newTirs.get(i).getFirstTankY()+tankHuman.getDeltaY());
            tir.setTankSorce(newTirs.get(i).getTankSorce());
            tirs.add(tir);
        }
        Iterator iterator = state.getMilitaryTools().iterator();
        militaryTools = new ArrayList<MilitaryTool>();
        while (iterator.hasNext()){
            MilitaryTool temp = (MilitaryTool)iterator.next();
//            MilitaryTool militaryTool = new MilitaryTool(temp.getFirstX(),temp.getFirstY());
//            militaryTool.setRotate(temp.getRotate());
//            militaryTool.setHealth(temp.getHealth());
//            militaryTool.setName(temp.getName());
            militaryTools.add((MilitaryTool) (temp));
//            MilitaryTool militaryTool = new MilitaryTool(state.getMilitaryTools().get(i).getFirstX(),state.getMilitaryTools().get(i).getFirstY());
//            militaryTool.setRotate(state.getMilitaryTools().get(i).getRotate());
//            militaryTool.setHealth(state.getMilitaryTools().get(i).getHealth());
//            militaryTool.setName(state.getMilitaryTools().get(i).getName());
//            militaryTools.add(militaryTool);
        }
            MilitaryTool militaryTool = new MilitaryTool(tankHuman.getLocX()+tankHuman.getDeltaX(),tankHuman.getLocY()+tankHuman.getDeltaY());
            militaryTool.setRotate(tankHuman.getRotate());
            militaryTool.setHealth(tankHuman.getHealth());
            militaryTool.setName(tankHuman.getName());
            militaryTools.add(militaryTool);
        }
        public  void removeAll(){
                if(buildings.size() != 0){
                Iterator iterator = recivedBuildings.iterator();
                while (iterator.hasNext()) {
                    iterator.remove();
                }
            }
        }
        public void removeRecivedMilitarytools(){
        recivedMilitaryTools = new ArrayList<MilitaryTool>();
        }

    public void setTirs(ArrayList<Tir> tirs) {
        this.tirs = tirs;
    }

    public ArrayList<MilitaryTool> getMilitaryTools() {
        return militaryTools;
    }

    public void setMilitaryTools(ArrayList<MilitaryTool> militaryTools) {
        this.militaryTools = militaryTools;
    }

    public ArrayList<MilitaryTool> getRecivedMilitaryTools() {
        return recivedMilitaryTools;
    }

    public void setRecivedMilitaryTools(ArrayList<MilitaryTool> recivedMilitaryTools) {
        this.recivedMilitaryTools = recivedMilitaryTools;
    }

    public void removeRecivedTirs(){
        recivedTirs = new ArrayList<Tir>();
    }
    public void setRecivedTirs(ArrayList<Tir> recivedTirs) {
        this.recivedTirs = recivedTirs;
    }

    public ArrayList<Tir> getTirs() {
        return tirs;
    }

    public ArrayList<Tir> getRecivedTirs() {
        return recivedTirs;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public  void removeBuilding(){
//            if(buildings.size() != 0){
//                Iterator iterator = buildings.iterator();
//                while (iterator.hasNext()) {
//                    iterator.remove();
//                }
//            }
            buildings = new ArrayList<Building>();
        }
        public void removeRecivedBuilding(){
        recivedBuildings = new ArrayList<Building>();
        }
    public void setRecivedBuildings(ArrayList<Building> recivedBuildings) {
        this.recivedBuildings = recivedBuildings;
    }

    public ArrayList<Building> getRecivedBuildings() {
        return recivedBuildings;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }
}

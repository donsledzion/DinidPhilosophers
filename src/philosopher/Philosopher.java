package philosopher;

import java.util.ArrayList;
import java.util.List;
import fork.Fork;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Group;
import table.Table;


public class Philosopher {

    private final int ID;
    private boolean isEating;
    private List<Fork> forks;
    private List<Philosopher> forkRequests;
    private short portion;
    private short bite;
    private Table table;

    public Philosopher(int _ID, short _bite, Table _table){

        System.out.println("Creation of philosopher no. "+_ID+".");
        this.ID = _ID;
        this.isEating = false;
        this.forks = new ArrayList<Fork>();
        this.portion = 100;
        this.bite = _bite;
        this.forkRequests = new ArrayList<Philosopher>();
        this.table = _table;
    }

    public boolean getIsEating(){ return this.isEating;}

    public void setIsEating(boolean toItOrNotToIt){ this.isEating = toItOrNotToIt;}

    public int getID(){
        return this.ID;
    }

    public List<Philosopher> getForkRequests(){ return this.forkRequests;}

    public boolean askForFork(){
        int forksLack = 2-this.getForks().size();
        System.out.println("Philosopher no. "+this.getID()+" needs "+ forksLack+" fork(s) to eat");

        for(int i = this.getForks().size() ; i <=2 ; i++){
            if(this.getID()==1){
                if(forkRequest(this.table.getPhilosophers().get(this.table.getPhilosophers().size()-1))==true){
                    forksLack--;
                    if(forksLack<1){
                        return true;
                    }
                } else {
                    if(forkRequest(this.table.getPhilosophers().get(1))==true){
                        forksLack--;
                        if(forksLack<1){
                            return true;
                        }
                    }
                }
            } else if (this.getID()==this.table.getPhilosophers().size()){
                if(forkRequest(this.table.getPhilosophers().get(this.table.getPhilosophers().size()-2))==true){
                    forksLack--;
                    if(forksLack<1){
                        return true;
                    }
                } else {
                    if(forkRequest(this.table.getPhilosophers().get(0))==true){
                        forksLack--;
                        if(forksLack<1){
                            return true;
                        }
                    }
                }
            } else {
                if(forkRequest(this.table.getPhilosophers().get(this.getID()-2))==true){
                    forksLack--;
                    if(forksLack<1){
                        return true;
                    }
                } else {
                    if(forkRequest(this.table.getPhilosophers().get(this.getID()))==true){
                        forksLack--;
                        if(forksLack<1){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getAmountOfFreeForks(){
        int freeForks = 0;
        for(int i = 0 ; i < this.getForks().size() ; i++){
            if(!this.getForks().get(i).getIsClean()) freeForks++;
        }
        return freeForks;
    }

    public boolean forkRequest(Philosopher p){
        System.out.println("===================================================================================================");
        System.out.println("Philosopher no. "+ this.getID() + " asks Philosopher no. " + p.getID() + "for his fork...");
        if(p.getIsEating()==true){
            if(p.getForkRequests().size()>0){
                for(Philosopher phil:this.getForkRequests()){
                    if(phil.getID()==this.getID()){
                        System.out.println("Dude! I've already promised You one fork - don't be greedy!");
                        return false;
                    }
                }
            } else if(p.getForkRequests().size()>1) {
                System.out.println("Buddy! I'm eating and I've promised already all my forks!");
                return false;
            }
            p.forkRequests.add(this);
            System.out.println("...and he says NO PROBLEMO - as soon as I'm finished with my portion! O-M-N-O-M-N-O-M-N-O-M");
            return true;
        } else if(p.getAmountOfFreeForks()==0){
            System.out.println("...and he says SORRY DUDE! I have no free fork!");
            return false;
        } else if(p.getAmountOfFreeForks()==p.getForkRequests().size()){
            System.out.println("...and he says MI SCUSI! I have promised already all my free forks!");
            return false;
        } else {
            p.forkRequests.add(this);
            System.out.println("...and he says ok buddy!");
            return true;
        }
    }

    public void eat(){
        //System.out.println("Philosopher no. "+ this.ID + " eats!" );
        System.out.println("Philosopher no. "+ this.ID + " has finished his meal!" );
        if(this.getIsEating()==true) {
            if (this.portion > 0) {
                this.portion -= this.bite;
                for (Fork f : this.getForks()) {
                    f.spoil();
                }
            }
            this.setIsEating(false);
        }
    }

    public void takeNewFork(){
        if(this.forks.size()<2){
            this.forks.add(new Fork());
        }
    }

    public void giveAwayForks(){
        for(Philosopher p: this.getForkRequests()){
            passFork(p,this.getForks().get(this.getForks().size()-1));
        }
        this.getForkRequests().clear();
    }

    public void passFork(Philosopher toWhom, Fork fork){
        fork.wash();
        this.forks.remove(fork);
        toWhom.forks.add(fork);
        System.out.println("Philosopher no. " + this.ID + " passed his fork to Philosopher no. "+ toWhom.ID);
    }

    public void showPromises(){
        if(this.getForkRequests().size()>0) {
            System.out.println("==============================================================================");
            System.out.println("Philosopher no. "+this.getID()+" has promised forks for:");
            for (Philosopher p : this.getForkRequests()) {
                System.out.println("====> Philosopher no. "+ p.getID());
            }
        } else {
            System.out.println("Philosopher no. "+this.getID()+" has no commitments!");
        }
    }

    public int getPortion(){
        return this.portion;
    }

    public List<Fork> getForks(){ return this.forks;}

    public void reportStatus(){
        if(forks.size()==1) System.out.println("Philosopher no." + this.ID + " has " + forks.size() + " fork, and " + this.getPortion() + "% filled plate.");
        else                System.out.println("Philosopher no." + this.ID + " has " + forks.size() + " forks, and " + this.getPortion() + "% filled plate.");
    }
}

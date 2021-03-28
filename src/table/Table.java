package table;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import philosopher.Philosopher;
import fork.Fork;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Philosopher> philosophers;
    private List<Fork> forks;
    private double radius;
    private double centerX;
    private double centerY;

    public Table(int guests, short bite){
        this.philosophers = new ArrayList<Philosopher>();
        this.forks = new ArrayList<Fork>();
        this.radius = 250.0f;
        this.centerX = 400.0f;
        this.centerY = 400.0f;

        for(int i = 1 ; i <= guests ; i++ ){
            this.philosophers.add(new Philosopher(i,bite,this));
        }
        //this.handOutForks();
    }

    public void tableStatus(){

        for (Philosopher p:philosophers){
            p.reportStatus();
        }
    }

    public void handOutForks(){
        for(Philosopher p:philosophers){
            p.takeNewFork();
        }
    }

    public void initialHandOut(){
        for(int i = 0 ; i < this.getPhilosophers().size() ; i++){
            if(this.getPhilosophers().get(i).getID()<this.getPhilosophers().size()){
                if(this.getPhilosophers().get(i).getID()<this.getPhilosophers().get(i+1).getID()){
                    this.getPhilosophers().get(i).takeNewFork();
                } else {
                    this.getPhilosophers().get(i+1).takeNewFork();
                }

            } else {
                if(this.getPhilosophers().get(i).getID()<this.getPhilosophers().get(0).getID()){
                    this.getPhilosophers().get(i).takeNewFork();
                } else {
                    this.getPhilosophers().get(0).takeNewFork();
                }
            }

        }
    }

    public List<Philosopher> getPhilosophers(){
        return this.philosophers;
    }

    public double getRadius(){
        return this.radius;
    }

    public double getCenterX(){
        return this.centerX;
    }

    public double getCenterY(){
        return this.centerY;
    }

    public Group drawTable(){

        Color tableColor = Color.SANDYBROWN;

        Circle table = new Circle();
        table.setCenterX(this.centerX);
        table.setCenterY(this.centerY);
        table.setRadius(this.getRadius());
        table.setFill(tableColor);

        return new Group(table);
    }

    public Group drawPlates(){
        Color plateColor = Color.LIGHTYELLOW;
        Color foodFlavour = Color.INDIANRED;

        int seats = this.getPhilosophers().size();
        double radius = this.getRadius()*2/seats;

        double radStep = 2*Math.PI/seats;

        Group plates = new Group();
        for(int i = 0 ; i < seats; i++){

            double centerX = this.getCenterX()+(this.getRadius()-radius)*1.00f*Math.cos(i*radStep);
            double centerY = this.getCenterY()+(this.getRadius()-radius)*1.00f*Math.sin(i*radStep);

            Circle plate = new Circle();
            plate.setCenterX(centerX);
            plate.setCenterY(centerY);

            plate.setRadius(radius);
            plate.setFill(plateColor);
            plates.getChildren().add(plate);

            Circle food = new Circle();
            food.setCenterX(centerX);
            food.setCenterY(centerY);

            food.setRadius(radius*0.008f*this.philosophers.get(i).getPortion());
            food.setFill(foodFlavour);
            plates.getChildren().add(food);
        }

        return plates;
    }

    public void washForks(){
        for(Philosopher p:this.getPhilosophers()){
            p.getForks().get(0).wash();
        }
    }

    public void newRound(){
        for(Philosopher p: this.getPhilosophers()){
            if(p.getForks().size()>1){
                p.setIsEating(true);
            } else { p.setIsEating(false);}
        }
        for(Philosopher p: this.getPhilosophers()){
            if(p.getIsEating()==true) {
                System.out.println("Philosopher no. " + p.getID() + " i eating: O-M-N-O-M-N-O-M-N-O-M");
            } else {
                p.askForFork();
            }
        }
        for(Philosopher p: this.getPhilosophers()){
            if(p.getIsEating()==true){
                p.eat();
            }
            p.giveAwayForks();


        }
    }

    public void reportCommitments(){
        for(Philosopher p: this.getPhilosophers()){
            p.showPromises();
        }
    }

    public Group drawForks(){

        Group forks = new Group();

        int seats = this.getPhilosophers().size();
        double radius = getRadius()/4.5;
        double radStep = 2*Math.PI/seats;

        for(int i = 0 ; i < seats; i++){

            if(!this.philosophers.get(i).getForks().isEmpty()){
                for(int j = 0 ; j < this.getPhilosophers().get(i).getForks().size();    j++) {
                    Color forkColor = this.philosophers.get(i).getForks().get(0).getColor();
                    Polygon fork = this.philosophers.get(i).getForks().get(0).getShape(this.getCenterX(), this.getCenterY(), radius, i * (360 / seats)-j*(180/seats),seats);
                    fork.setFill(forkColor);
                    forks.getChildren().add(fork);
                }
            }
        }
        return forks;
    }

    public Group drawPhilosophers(){
        Color plateColor = Color.LIGHTPINK;
        int seats = this.getPhilosophers().size();
        double radius = getRadius()*2/seats;

        double radStep = 2*Math.PI/seats;

        Group guests = new Group();

        for(int i = 0 ; i < seats; i++){
            Circle guest = new Circle();
            guest.setCenterX(getCenterX()+(getRadius()+radius)*1.0*Math.cos(i*radStep));
            guest.setCenterY(getCenterY()+(getRadius()+radius)*1.0*Math.sin(i*radStep));

            guest.setRadius(radius);
            guest.setFill(plateColor);
            guests.getChildren().add(guest);
        }

        return guests;
    }

    public void everyoneEats(){
        for(Philosopher p:this.philosophers){
            p.eat();
        }
    }

}

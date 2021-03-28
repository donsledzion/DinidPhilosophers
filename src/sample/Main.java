package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import table.Table;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        int invitations = 10;   // number of philosophers at the table
        short bite = 2;         // percentage of food ate at once


        Table dinner = new Table(invitations,bite); // creation of new table
        dinner.tableStatus();

        //===============================================================
        //=================== BUTTONS ===================================
        //===============================================================
        Button eatButton = new Button("Eat shit!");

        eatButton.setLayoutX(100);
        eatButton.setLayoutY(20);

        Button washButton = new Button("Wash it!");

        washButton.setLayoutX(170);
        washButton.setLayoutY(20);

        Button addForksButton = new Button("Takeaway!");

        addForksButton.setLayoutX(250);
        addForksButton.setLayoutY(20);

        Button initialHandoutButton = new Button("Initial handout!");

        initialHandoutButton.setLayoutX(100);
        initialHandoutButton.setLayoutY(20);

        Button newRoundButton = new Button("NEW ROUND!");

        newRoundButton.setLayoutX(100);
        newRoundButton.setLayoutY(50);

        Button commitmentsButton = new Button("SHOW COMMITMENTS!");

        commitmentsButton.setLayoutX(200);
        commitmentsButton.setLayoutY(50);

        //===============================================================

        Group root = dinner.drawPhilosophers();
        root.getChildren().add(dinner.drawTable());
        root.getChildren().add(dinner.drawPlates());
        root.getChildren().add(dinner.drawForks());
        //root.getChildren().add(eatButton);
        //root.getChildren().add(washButton);
        //root.getChildren().add(addForksButton);
        root.getChildren().add(initialHandoutButton);
        root.getChildren().add(newRoundButton);
        //root.getChildren().add(commitmentsButton);

        eatButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dinner.everyoneEats();
                root.getChildren().add(dinner.drawPlates());
                root.getChildren().add(dinner.drawForks());
            }
        });
        washButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //dinner.everyoneEats();
                //root.getChildren().add(dinner.drawPlates());
                dinner.washForks();
                root.getChildren().add(dinner.drawForks());
            }
        });

        addForksButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dinner.handOutForks();
                root.getChildren().add(dinner.drawForks());
            }
        });

        initialHandoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dinner.initialHandOut();
                root.getChildren().add(dinner.drawForks());
            }
        });

        newRoundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dinner.newRound();
                root.getChildren().add(dinner.drawTable());
                root.getChildren().add(dinner.drawPlates());
                root.getChildren().add(dinner.drawForks());

            }
        });

        commitmentsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dinner.reportCommitments();
                //root.getChildren().add(dinner.drawPlates());
                //root.getChildren().add(dinner.drawForks());

            }
        });

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 800));




        primaryStage.show();
    }


    public static void main(String[] args) {



        launch(args);
    }
}

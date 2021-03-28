package fork;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Fork {

    private boolean isClean;

    public Fork(){
        System.out.println("New fork just landed on the table\n");
        isClean = false;
    }

    public void spoil(){
        isClean = false;
        System.out.println("Fork has been spoiled");
    }
    public void wash(){
        isClean = true;
        System.out.println("Fork has been washed");
    }
    public boolean getIsClean(){
        return this.isClean;
    }

    public Color getColor(){
        if(this.getIsClean()==true) return Color.SILVER;
        else return Color.MAROON;
    }

    public Polygon getShape(double X, double Y, double radius, double angle, int seats){
        Polygon fork = new Polygon();

        double tempRad = radius*(seats-2)/7.5;

        double factor = tempRad;
        fork.getPoints().addAll(new Double[]{
                0.0,    10.0,
                4.0,    11.0,
                0.0,    13.0,
                6.0,    13.0,
                8.0,    11.0,
                20.0,   11.0,
                21.0,   10.0,
                20.0,   9.0,
                8.0,    9.0,
                6.0,    7.0,
                0.0,    7.0,
                4.0,    9.0

        });

//==========================================================
//============= SCALING ====================================
//==========================================================
        Scale scale = new Scale();

        scale.setPivotX(0.0);
        scale.setPivotY(0.0);

        scale.setX(30.0/seats);
        scale.setY(30.0/seats);

        fork.getTransforms().addAll(scale);

//==========================================================
//============= TRANSLATION ================================
//==========================================================
        Translate translate = new Translate(X/scale.getX()+factor,Y/scale.getY());

        fork.getTransforms().addAll(translate);
//==========================================================
//============= ROTATION ===================================
//==========================================================
        Rotate rotate = new Rotate();

        rotate.setPivotX(-(factor));
        rotate.setPivotY(0);
        rotate.setAngle(angle);

        fork.getTransforms().addAll(rotate);

        return fork;
    }
}

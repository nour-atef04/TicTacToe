package backend;

public class Square {

    Shape shape;

    void addShape(Shape shape){
        this.shape = shape;
    }

    Shape getShape(){
        return this.shape;
    }

    boolean isEmpty(){
        return (this.shape == null);
    }

}

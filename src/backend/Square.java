package backend;

public class Square {

    Shape shape;
    int row;
    int col;

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

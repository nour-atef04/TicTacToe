package backend;


public class Square {

    Shape shape;
    int row;
    int col;

    void addShape(Shape shape){
        this.shape = shape;
    }

    public Shape getShape(){
        return this.shape;
    }

    boolean isEmpty(){
        return (this.shape == null);
    }

    //TODO: REMOVE THIS METHOD AFTER TESTING
    public String printShape(){
        if(this.shape == null) return " ";
        if(this.shape.equals(Shape.X)) return "X";
        else return "O";
    }

}

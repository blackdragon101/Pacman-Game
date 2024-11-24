import java.util.ArrayList;

public class DirectedWalker {
    private point2 curr_position;
    private String direction;
    private point2 previous_location;
    private ArrayList<Block> hurdles = new ArrayList<>();
    public String toString() {
        return curr_position.toString();
    }
    public DirectedWalker() {
        curr_position = new point2(0,0);
    }
    public DirectedWalker(int xx,int yy) {
        curr_position = new point2(xx,yy);
    }
    public point2 walkto(String dir, ArrayList<Block> hurdles) {
        // r for right: inc in x(positive x axis)
        // l for left: dec in x(negative x axis)
        // u for up: inc in y(positive y axis)
        // d for down: dec in y(negative y axis)

        this.hurdles= hurdles;
        direction = new String(dir);
        previous_location = new point2(curr_position.getX(),curr_position.getY());
        if(dir.equals("r")) {
            curr_position.SetX(curr_position.getX()+32);
        }
        if(dir.equals("l")) {
            curr_position.SetX(curr_position.getX()-32);
        }
        if(dir.equals("u")) {
            curr_position.SetY(curr_position.getY()-32);
        }
        if(dir.equals("d")) {
            curr_position.SetY(curr_position.getY()+32);
        }
        for (Block locations:hurdles) {
            if(curr_position.getX()==locations.x && curr_position.getY()== locations.y) {
//                System.out.println("hurdle encountered");
                curr_position = previous_location;
                return curr_position;
            }
        }
        return curr_position;
    }

}

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.ArrayList;
public class RandomWalker {
    private point2 curr_position;
    private String direction;
    private point2 previous_position;
    boolean encounter = false;
    public String toString() {
        return curr_position.toString();
    }

    public RandomWalker() {
        curr_position = new point2(0,0);
    }
    public RandomWalker(int xx,int yy) {
        curr_position = new point2(xx,yy);
        Random rand = new Random();
        ArrayList<String> directions = new ArrayList<>();
        directions.add("r");
        directions.add("l");
        directions.add("u");
        directions.add("d");
        int RandomIndex = rand.nextInt(4);
        this.direction = directions.get(RandomIndex);
    }

    public point2 walk(ArrayList<Block> hurdles) {

        // now we will pass the hurdles into the function

        Random rand = new Random();
//        ArrayList<String> directions = new ArrayList<>();
//        directions.add("r");
//        directions.add("l");
//        directions.add("u");
//        directions.add("d");
//        int RandomIndex = rand.nextInt(4);
//        direction = directions.get(RandomIndex);
        if (encounter) {
            ArrayList<String> directions = new ArrayList<>();
            directions.add("r");
            directions.add("l");
            directions.add("u");
            directions.add("d");
            int RandomIndex = rand.nextInt(4);
            direction = directions.get(RandomIndex);  // Choose a new random direction
            encounter = false;  // Reset encounter flag
        }
        int steps = 32;
        previous_position = new point2(curr_position.getX(),curr_position.getY());
//        System.out.println("Ghost current position: " + curr_position.getX() + ", " + curr_position.getY());
        if(direction.equals("r")) {
            curr_position.SetX(curr_position.getX()+steps);
        }
        if(direction.equals("l")) {
            curr_position.SetX(curr_position.getX()-steps);
        }
        if(direction.equals("u")) {
            curr_position.SetY(curr_position.getY()-steps);
        }
        if(direction.equals("d")) {
            curr_position.SetY(curr_position.getY()+steps);
        }

        for (Block locations:hurdles) {
            if(curr_position.getX()==locations.x && curr_position.getY()== locations.y) {
//                System.out.println("Hurdle encountered");
                encounter = true;
                curr_position =  previous_position;
                return curr_position;
            }
        }
        return curr_position;
    }
    // driver of this class

}

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JFrame;
public class Pacman extends JPanel implements ActionListener, KeyListener {

    // we have created sets like data types to store that where and...
    // what are going to be the properties of the objects that we are going to store on board.
    ArrayList<Tile> walls;
    HashSet<Tile> foods;
    ArrayList<Tile> ghosts;
    Tile pacman; // bcz pacman has only one Tile or tile.
    public Clip clip;
    private boolean Collided = false;
    private boolean gameOver = false;
    private boolean Win;
    private JLabel gameWinOrLose;
    private JLabel scoreLabel;
    private int rowcount = 15;
    private int colunmcount = 15;
    private int tilesize = 32; // it is the size of a single tile of the board game.
    private int boardWidth = colunmcount * tilesize;
    private int boardHeight = rowcount * tilesize;
    private DirectedWalker pacmanWalker;
    private RandomWalker[] GhostWalker;
    private Timer gameLoop;
    int score = 0;
    int lives = 3;
    String inCaseofNotWon="Game Playing";
    String direction = "";

    private String[] tileMap = {
            "XXXXXXXXXXXXXXX",
            "XX     XX   XXX",
            "X   XX     XXXX",
            "X  XXX  XX    X",
            "XXX      O  XXX",
            "X   XX o XX   X",
            "X   XXbprXX  XX",
            "XX    XXX     X",
            "XXXX   X     XX",
            "X    XX  X   XX",
            "X    XXXXX    X",
            "X     P       X",
            "XX    XX     XX",
            "XXXXXXXXXXXXXXX"
    };

    private Image wallImage;
    private Image blueGhost;
    private Image redGhost;
    private Image pinkGhost;
    private Image orangeGhost;

    private Image pacmanDown;
    private Image pacmanUp;
    private Image pacmanLeft;
    private Image pacmanRight;

    //    function for removing eaten food
    public void foodEaten() {
        ArrayList<Tile> eatenF = new ArrayList<>();
        for (Tile food:foods) {
            if(hasCollided(pacman,food)) {
                eatenF.add(food);
                score++;
            }
        }
        for(Tile tobeRemoved:eatenF) {
            foods.remove(tobeRemoved);
        }
        updateScoreLabel();
    }

    //    to update the score and lives
    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score + " | Lives: " + lives);
    }

    private void updateState() {
        gameWinOrLose.setText(Win? "Game Won":inCaseofNotWon);
    }
    //    to change the image direction of pacman
    public void directionChange() {
        if (direction.equals("u")) {
            pacman.image = pacmanUp;
        }
        if (direction.equals("d")) {
            pacman.image = pacmanDown;
        }
        if (direction.equals("r")) {
            pacman.image = pacmanRight;
        }
        if (direction.equals("l")) {
            pacman.image = pacmanLeft;
        }
    }


    //    function for collision with ghost
    public void GhostCollision() {
        if(HasGhostCollided(pacmanWalker,GhostWalker)) {
            Collided = true;
            if(lives!=0) {
                lives -= 1;
            }
            resetPosition();
            gameLoop.stop();
            Timer pauseTimer = new Timer(2000, e -> {
                gameLoop.start();
            }); // Restart the loop after delay
            pauseTimer.setRepeats(false);// Only execute once
            pauseTimer.start();
            updateScoreLabel();
        }
    }

    //To Check if All the food is Eaten
    public void CheckAllEaten(){
        if(foods.size()==0){
            gameOver=true;
            Win=true;
            updateState();
            clip.stop();
            gameLoop.stop();
        }
    }

    public void resetPosition(){
        pacman.x = pacman.startX;
        pacman.y = pacman.startY;

//        for (int i = 0; i < ghosts.size(); i++) {
//            Tile ghost = ghosts.get(i);
//            ghost.x = ghost.startX;
//            ghost.y = ghost.startY;
//        }


    }



    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP) direction = "u";
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) direction = "d";
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) direction = "l";
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) direction = "r";

    }


    // making a constructor of the pacman class.

    public Pacman(Clip newClip) {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.BLACK);
        this.clip = newClip;
//     we are creating an array type object here to initialise the reference of ghostWalker.
        GhostWalker = new RandomWalker[4];

        addKeyListener(this);
        setFocusable(true);

        walls = new ArrayList<>();
        foods = new HashSet<>();
        ghosts = new ArrayList<>();

        // load images.
        wallImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./wall.png"))).getImage();
        blueGhost = new ImageIcon(Objects.requireNonNull(getClass().getResource("./blueGhost.png"))).getImage();
        redGhost = new ImageIcon(Objects.requireNonNull(getClass().getResource("./redGhost.png"))).getImage();
        pinkGhost = new ImageIcon(Objects.requireNonNull(getClass().getResource("./pinkGhost.png"))).getImage();
        orangeGhost = new ImageIcon(Objects.requireNonNull(getClass().getResource("./orangeGhost.png"))).getImage();
        pacmanDown = new ImageIcon(Objects.requireNonNull(getClass().getResource("./pacmanDown.png"))).getImage();
        pacmanUp = new ImageIcon(Objects.requireNonNull(getClass().getResource("./pacmanUp.png"))).getImage();
        pacmanLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("./pacmanLeft.png"))).getImage();
        pacmanRight = new ImageIcon(Objects.requireNonNull(getClass().getResource("./pacmanRight.png"))).getImage();

//      it means after the delay of every 170 milliseconds, the actionPerformed function is called.
        gameLoop = new Timer(170,this);
        gameLoop.start();
        createMaze();
//        we are creating label here
        scoreLabel = new JLabel("Score:"+ score +" | Lives: " + lives);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.BLACK);


//        we are creating win or lose label here
        gameWinOrLose = new JLabel(Win? "Game Won":inCaseofNotWon);
        gameWinOrLose.setFont(new Font("Arial", Font.BOLD, 20));
        gameWinOrLose.setForeground(Color.black);
//Dealing with The logic of the music here


        int rowcount = 21;
        int colunmcount = 19;
        int tilesize = 32; // it is the size of a single tile of the board game.
        int boardWidth = colunmcount * tilesize;
        int boardHeight = rowcount * tilesize;
        JFrame frame = new JFrame(); // creates a frame for us.
        frame.setSize(boardWidth,boardHeight);
        //sets the size of the frame: provide width and height
        frame.setTitle("Pacman Game"); // sets title of frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this exits the program when we close the frame window...or else the program does not stop when we close the window.
        frame.setResizable(false);
        // It does not allow the window to resize.
        // makes the frame visible
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.add(scoreLabel, BorderLayout.NORTH); // border layout for adding on top
        frame.add(gameWinOrLose, BorderLayout.SOUTH); //To add at the bottom
        this.requestFocus();
        // we have created an object of class pacman,and now we will add it to frame.
        frame.pack();
        frame.setVisible(true);



    }
    public void createMaze() {
        int ghostIndex = 0;
        for(int r=0; r<tileMap.length;r++) {
            for(int c=0; c<colunmcount;c++) {
                String row = tileMap[r];
                char tileChar = row.charAt(c);
                int x = c*tilesize;
                int y = r*tilesize;
                if(tileChar == 'X'){ // it means it has to be replaced with wall
                    Tile wall = new Tile(wallImage,x,y,tilesize,tilesize);
                    walls.add(wall);
                }
                else if (tileChar=='b') { // adding blue ghost
                    Tile ghost = new Tile(blueGhost,x,y,tilesize,tilesize);
                    ghosts.add(ghost);
                    GhostWalker[ghostIndex] = new RandomWalker(x,y);
                    ghostIndex++;
                }
                else if (tileChar=='p') { //adding pink ghost image
                    Tile ghost = new Tile(pinkGhost,x,y,tilesize,tilesize);
                    ghosts.add(ghost);
                    GhostWalker[ghostIndex] = new RandomWalker(x,y);
                    ghostIndex++;
                }
                else if (tileChar=='r') { //adding red ghost
                    Tile ghost = new Tile(redGhost,x,y,tilesize,tilesize);
                    ghosts.add(ghost);
                    GhostWalker[ghostIndex] = new RandomWalker(x,y);
                    ghostIndex++;
                }
                else if (tileChar=='o') { //adding orange ghost
                    Tile ghost = new Tile(orangeGhost,x,y,tilesize,tilesize);
                    ghosts.add(ghost);
                    GhostWalker[ghostIndex] = new RandomWalker(x,y);
                    ghostIndex++;
                }
                else if (tileChar=='P') {
                    pacman = new Tile(pacmanLeft,x,y,tilesize,tilesize);
                    pacmanWalker = new DirectedWalker(x,y);
                }
                else if (tileChar==' ') {
                    Tile food = new Tile(null,x+14,y+14,4,4);
                    foods.add(food);
                }
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
//        we are drawing the pacman here.
        g.drawImage(pacman.image,pacman.x, pacman.y,pacman.width, pacman.height,null);
        for(Tile ghost:ghosts) {
            g.drawImage(ghost.image,ghost.x,ghost.y,ghost.width,ghost.height,null);
        }
        for (Tile wall:walls) {
            g.drawImage(wall.image,wall.x, wall.y, wall.width, wall.height, null);
        }
        g.setColor(Color.GRAY);
        for (Tile food:foods) {
            g.fillRect(food.x, food.y, food.width, food.height);
        }
    }
    public boolean hasCollided(Tile a,Tile b) {
//      we have subtracted 14 because the food was placed in the middle of the tile.
        if(a.x==b.x-14 && a.y==b.y-14) {
            return true;
        }
        return false;
    }

    public boolean HasGhostCollided(DirectedWalker pac,RandomWalker[] ghosted) {
        int pacPrevX = pac.getPreviousX();  // we have created methods to get previous position of the directed walker.
        int pacPrevY = pac.getPreviousY();

        for (RandomWalker ghost : ghosted) {
            int ghostPrevX = ghost.getPreviousX();
            int ghostPrevY = ghost.getPreviousY();

            // Check if positions have swapped
            if ((pac.getDirectedX() == ghost.getRandomX() && pac.getDirectedY() == ghost.getRandomY()) ||
                    (pacPrevX == ghost.getRandomX() && pacPrevY == ghost.getRandomY() &&
                            pac.getDirectedX() == ghostPrevX && pac.getDirectedY() == ghostPrevY)) {
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        // we are Updating ghost positions with RandomWalker
//        this is for when the game is lost and pacman has lost all his lives.
        if(lives<1){
            gameOver = true;
            inCaseofNotWon="Game Over";
            clip.stop();
            updateState();
            gameLoop.stop();
        }
        for (int i = 0; i < GhostWalker.length; i++) {
            if(GhostWalker[i] != null) {
                Tile ghost = ghosts.get(i);
                point2 newPosition = GhostWalker[i].walk(walls);
                ghost.x = newPosition.getX();
                ghost.y = newPosition.getY();
// this is the linking point of the tile type objects and......
// the Random Walker objects in the Ghost Walker Array.
            }
        }
        //Pacman and Directed Walker Logic in the key released section
        if (!direction.isEmpty()) {
            point2 newPoint  = pacmanWalker.walkto(direction,walls);
//            this collided represents the collision of ghost and pacman.
            if(!Collided){
                pacman.x = newPoint.getX();
                pacman.y = newPoint.getY();
            }
            else if(Collided==true) {
                pacman.x = pacman.startX;
                pacman.y = pacman.startY;
                newPoint.SetX(pacman.startX);
                newPoint.SetY(pacman.startY);
                Collided = false;
            }
        }
        directionChange();
        foodEaten();
        CheckAllEaten();
        GhostCollision();
        // Repaint to see the next new positions
//        it visually shows the next updated positions when the action performed is called.
        repaint();
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException,Exception{

        File file= new File("C:\\Users\\ATIF TRADERS\\IdeaProjects\\Pacman\\src\\StockTune-City Nights Pulse Glow_1731524799.wav");
        AudioInputStream audioStream= AudioSystem.getAudioInputStream(file);
        Clip clip= AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        new Pacman(clip);
//        making an anonymous object because name assign ki need nhi h it just has to start the game
    }
}

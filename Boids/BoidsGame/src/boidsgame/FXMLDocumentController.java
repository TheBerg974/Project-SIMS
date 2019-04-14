package boidsgame;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private Label nbOfBoidsRemain;
    @FXML
    private Label timeLabel;

    //time
    double time = ArenaSetup.getTime() + 1;

    //Player scores
    Label scorePlayer1 = new Label();
    Label scorePlayer2 = new Label();
    Label scorePlayer3 = new Label();
    Label scorePlayer4 = new Label();

    private double lastFrameTime = 0.0;

    //Animation Counters
    int animationBoid;
    int animationPredator = 1;
    int counter = 0;
    int orbAnimation = 1;

    // PLAYER MOVEMENT //**//
    //transitions between pendulum and projectile motion
    boolean isKeyPressedP1 = false;
    boolean isKeyPressedP2 = false;
    boolean isKeyPressedP3 = false;
    boolean isKeyPressedP4 = false;

    int onButtonPressedCheckP1 = 0;
    int onButtonReleasedCheckP1 = 0;
    int onButtonPressedCheckP2 = 0;
    int onButtonReleasedCheckP2 = 0;
    int onButtonPressedCheckP3 = 0;
    int onButtonReleasedCheckP3 = 0;
    int onButtonPressedCheckP4 = 0;
    int onButtonReleasedCheckP4 = 0;

    //gets the screen size in order to place the elements of the game
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final double screenWidth = screenBounds.getWidth() - 10;
    final double screenHeight = screenBounds.getHeight() - 40;

    //**// ARRAYLISTS //**//
    private ArrayList<Boid> boidFlock = new ArrayList<>();
    private ArrayList<Predator> predatorFlock = new ArrayList<>();
    private ArrayList<Predator> scaryPredatorFlock = new ArrayList<>();
    private ArrayList<Player> playerBunch = new ArrayList<>();
    private ArrayList<Player> activePlayers = new ArrayList<>();

    private ArrayList<Mast> mastBunch = new ArrayList<>();
    private ArrayList<Spring> springBunch = new ArrayList<>();
    private ArrayList<Spike> spikeBunch = new ArrayList<>();
    private ArrayList<Orb> orbBunch = new ArrayList<>();

    private ArrayList<Boid> killFlock = new ArrayList<>();
    private ArrayList<Predator> killPack = new ArrayList<>();
    private ArrayList<Node> removalCandidates = new ArrayList<>();

    @FXML
    private void onButtonPressed(KeyEvent b) {

        if (b.getCode() == KeyCode.Q) {
            isKeyPressedP1 = true;
            if (onButtonPressedCheckP1 == 0) {
                playerBunch.get(0).switchMovements(mastBunch, playerBunch.get(0).getCircle().getCenterX(), playerBunch.get(0).getCircle().getCenterY(), 2);
            }
            onButtonPressedCheckP1++;
            onButtonReleasedCheckP1 = 0;
        } //player 2 swing key
        else if (b.getCode() == KeyCode.R) {
            isKeyPressedP2 = true;
            if (onButtonPressedCheckP2 == 0) {
                playerBunch.get(1).switchMovements(mastBunch, playerBunch.get(1).getCircle().getCenterX(), playerBunch.get(1).getCircle().getCenterY(), 2);

            }
            onButtonPressedCheckP2++;
            onButtonReleasedCheckP2 = 0;
        } //player 3 swing key
        else if (b.getCode() == KeyCode.U) {
            isKeyPressedP3 = true;
            if (onButtonPressedCheckP3 == 0) {
                playerBunch.get(2).switchMovements(mastBunch, playerBunch.get(2).getCircle().getCenterX(), playerBunch.get(2).getCircle().getCenterY(), 2);
            }
            onButtonPressedCheckP3++;
            onButtonReleasedCheckP3 = 0;
        } //player 4 swing key
        else if (b.getCode() == KeyCode.P) {
            isKeyPressedP4 = true;
            if (onButtonPressedCheckP4 == 0) {
                playerBunch.get(3).switchMovements(mastBunch, playerBunch.get(3).getCircle().getCenterX(), playerBunch.get(3).getCircle().getCenterY(), 2);
            }
            onButtonPressedCheckP4++;
            onButtonReleasedCheckP4 = 0;
        }
    }

    @FXML
    private void onButtonReleased(KeyEvent b) {

        //player 1 swing key
        if (b.getCode() == KeyCode.Q) {
            isKeyPressedP1 = false;
            if (onButtonReleasedCheckP1 == 0) {
                playerBunch.get(0).switchMovements(mastBunch, playerBunch.get(0).getCircle().getCenterX(), playerBunch.get(0).getCircle().getCenterY(), 1);
            }
            onButtonReleasedCheckP1++;
            onButtonPressedCheckP1 = 0;
        } //player 2 swing key
        else if (b.getCode() == KeyCode.R) {
            isKeyPressedP2 = false;
            if (onButtonReleasedCheckP2 == 0) {
                playerBunch.get(1).switchMovements(mastBunch, playerBunch.get(1).getCircle().getCenterX(), playerBunch.get(1).getCircle().getCenterY(), 1);
            }
            onButtonReleasedCheckP2++;
            onButtonPressedCheckP2 = 0;
        } //player 3 swing key
        else if (b.getCode() == KeyCode.U) {
            isKeyPressedP3 = false;
            if (onButtonReleasedCheckP3 == 0) {
                playerBunch.get(2).switchMovements(mastBunch, playerBunch.get(2).getCircle().getCenterX(), playerBunch.get(2).getCircle().getCenterY(), 1);
            }
            onButtonReleasedCheckP3++;
            onButtonPressedCheckP3 = 0;
        } //player 4 swing key
        else if (b.getCode() == KeyCode.P) {
            isKeyPressedP4 = false;
            if (onButtonReleasedCheckP4 == 0) {
                playerBunch.get(3).switchMovements(mastBunch, playerBunch.get(3).getCircle().getCenterX(), playerBunch.get(3).getCircle().getCenterY(), 1);
            }
            onButtonReleasedCheckP4++;
            onButtonPressedCheckP4 = 0;
        }
    }
    ///////////INITIALIZE///////////////////////////////////////////////////////    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //panel.setCursor(Cursor.NONE); this is to hide the cursor.... not right now tho

        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();
        //keeps tracks of where to spawn boids
        int boidsLocation = 0;

        //setting the background
        panel.setBackground(AssetManager.getBackgroundImage(screenWidth + 10, screenHeight + 40));

        //**// BOIDS //**//
        //spawning Boids 
        for (int x = 0; x < ArenaSetup.getNbOfBoids(); ++x) {
            switch (boidsLocation) {
                case 0:
                    boidFlock.add(new Boid(400, 300));
                    boidsLocation++;
                    break;
                case 1:
                    boidFlock.add(new Boid(screenWidth - 400, 300));
                    boidsLocation++;
                    break;
                case 2:
                    boidFlock.add(new Boid(screenWidth / 2, 800));
                    boidsLocation = 0;
                    break;
            }
        }
        //Adds boids to the panel
        for (Boid b : boidFlock) {
            addToPane(b.getRectangle());
        }

        //**// PREDATORS //**//
        //creates a predator and adds it to the panel
        for (int x = 0; x < ArenaSetup.getNumberOfPredators(); x++) {
            predatorFlock.add(new Predator(0, 0, false));
        }
        //Adds predator to the panel
        for (Predator p : predatorFlock) {
            addToPane(p.getRectangle());
        }

        //**// ORBS //**//
        //creates orbs 
        //  //orb player 1
        orbBunch.add(new Orb(90, 60));
        //  //orb player 2
        orbBunch.add(new Orb(90, (screenHeight / 2) + 60));
        //  //orb player 3
        orbBunch.add(new Orb(screenWidth - 90, 60));
        //  //orb player 4
        orbBunch.add(new Orb(screenWidth - 90, (screenHeight / 2) + 60));
        //adds orbs to panel
        for (Orb orb : orbBunch) {
            addToPane(orb.getCircle());
            addToPane(orb.getLine());
        }

        //**// MASTS //**// 
        //creates the 4 mandatory masts
        Mast mast1 = new Mast(orbBunch.get(0).getCircle().getCenterX() + 100, orbBunch.get(0).getCircle().getCenterY());
        mastBunch.add(mast1);
        addToPane(mast1.getCircle());
        Mast mast2 = new Mast(orbBunch.get(1).getCircle().getCenterX() + 100, orbBunch.get(1).getCircle().getCenterY());
        mastBunch.add(mast2);
        addToPane(mast2.getCircle());
        Mast mast3 = new Mast(orbBunch.get(2).getCircle().getCenterX() - 100, orbBunch.get(2).getCircle().getCenterY());
        mastBunch.add(mast3);
        addToPane(mast3.getCircle());
        Mast mast4 = new Mast(orbBunch.get(3).getCircle().getCenterX() - 100, orbBunch.get(3).getCircle().getCenterY());
        mastBunch.add(mast4);
        addToPane(mast4.getCircle());

        //creates the other masts and adds them to the pane
        generateMasts();

        //**// SPRINGS //**// 
        //creates the 4 mandatory springs
        Spring spring1 = new Spring(0, orbBunch.get(0).getCircle().getCenterY() + 250);
        springBunch.add(spring1);
        addToPane(spring1.getRectangle());
        Spring spring2 = new Spring(0, orbBunch.get(1).getCircle().getCenterY() + 250);
        springBunch.add(spring2);
        addToPane(spring2.getRectangle());
        Spring spring3 = new Spring(orbBunch.get(2).getCircle().getCenterX(), orbBunch.get(2).getCircle().getCenterY() + 250);
        springBunch.add(spring3);
        addToPane(spring3.getRectangle());
        Spring spring4 = new Spring(orbBunch.get(3).getCircle().getCenterX(), orbBunch.get(3).getCircle().getCenterY() + 250);
        springBunch.add(spring4);
        addToPane(spring4.getRectangle());

        //creates and adds springs to the panel
        generateSprings();

        //**// SPIKES //**//
        //setting up all the spikes according to the size of the screen
        for (int x = 0; x < (int) (screenHeight / 30); x++) {
            Spike spike1 = new Spike(0, 30 * x, 1);
            Spike spike2 = new Spike(screenWidth - 20, 30 * x, 2);
            spikeBunch.add(spike1);
            spikeBunch.add(spike2);
            addToPane(spike1.getRectangle());
            addToPane(spike2.getRectangle());
        }
        for (int x = 1; x < (int) (screenWidth / 30); x++) {
            Spike spike1 = new Spike(x * 30, screenHeight - 20, 3);
            spikeBunch.add(spike1);
            addToPane(spike1.getRectangle());
        }

        //**// PLAYERS //**//  
        //creating players based on user input
        for (int x = 1; x < 5; x++) {
            Player player = new Player(x);
            switch (x) {
                case 1:
                    if (ArenaSetup.isPlayer1Playing()) {
                        player = new Player(orbBunch.get(x - 1).getCircle().getCenterX(), orbBunch.get(x - 1).getCircle().getCenterY(), mastBunch, x);
                        activePlayers.add(player);
                    }
                    break;
                case 2:
                    if (ArenaSetup.isPlayer2Playing()) {
                        player = new Player(orbBunch.get(x - 1).getCircle().getCenterX(), orbBunch.get(x - 1).getCircle().getCenterY(), mastBunch, x);
                        activePlayers.add(player);
                    }
                    break;
                case 3:
                    if (ArenaSetup.isPlayer3Playing()) {
                        player = new Player(orbBunch.get(x - 1).getCircle().getCenterX(), orbBunch.get(x - 1).getCircle().getCenterY(), mastBunch, x);
                        activePlayers.add(player);
                    }
                    break;
                case 4:
                    if (ArenaSetup.isPlayer4Playing()) {
                        player = new Player(orbBunch.get(x - 1).getCircle().getCenterX(), orbBunch.get(x - 1).getCircle().getCenterY(), mastBunch, x);
                        activePlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
            playerBunch.add(player);
            addToPane(player.getCircle());
            addToPane(player.getString());
        }

        //  // player scores
        //  //  //player 1
        scorePlayer1 = new Label();
        scorePlayer1.setLayoutX(orbBunch.get(0).getCircle().getCenterX() - 15);
        scorePlayer1.setLayoutY(orbBunch.get(0).getCircle().getCenterY() + 45);
        scorePlayer1.setFont(new Font("Harlow Solid Italic", 40));
        scorePlayer1.setTextFill(Paint.valueOf("purple"));
        scorePlayer1.setText("0");
        scorePlayer1.toFront();
        addToPane(scorePlayer1);
        //  //  //player 2
        scorePlayer2 = new Label();
        scorePlayer2.setLayoutX(orbBunch.get(1).getCircle().getCenterX() - 15);
        scorePlayer2.setLayoutY(orbBunch.get(1).getCircle().getCenterY() + 45);
        scorePlayer2.setFont(new Font("Harlow Solid Italic", 40));
        scorePlayer2.setTextFill(Paint.valueOf("blue"));
        scorePlayer2.setText("0");
        scorePlayer2.toFront();
        addToPane(scorePlayer2);
        //  //  //player 3
        scorePlayer3 = new Label();
        scorePlayer3.setLayoutX(orbBunch.get(2).getCircle().getCenterX() - 15);
        scorePlayer3.setLayoutY(orbBunch.get(2).getCircle().getCenterY() + 45);
        scorePlayer3.setFont(new Font("Harlow Solid Italic", 40));
        scorePlayer3.setTextFill(Paint.valueOf("green"));
        scorePlayer3.setText("0");
        scorePlayer3.toFront();
        addToPane(scorePlayer3);
        //  //  //player 4
        scorePlayer4 = new Label();
        scorePlayer4.setLayoutX(orbBunch.get(3).getCircle().getCenterX() - 15);
        scorePlayer4.setLayoutY(orbBunch.get(3).getCircle().getCenterY() + 45);
        scorePlayer4.setFont(new Font("Harlow Solid Italic", 40));
        scorePlayer4.setTextFill(Paint.valueOf("red"));
        scorePlayer4.setText("0");
        scorePlayer4.toFront();
        addToPane(scorePlayer4);

        /////ANIMATION TIMER////////////////////////////////////////////////////
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                counter++;
                ArenaSetup.isGameOver = false;

                //**// LABELS //**// 
                //  //updating the total boid counter label
                nbOfBoidsRemain.setLayoutX((screenWidth - nbOfBoidsRemain.getWidth()) / 2);
                nbOfBoidsRemain.setLayoutY(50);
                nbOfBoidsRemain.toFront();
                nbOfBoidsRemain.setFont(new Font("Harrington", 40));
                nbOfBoidsRemain.setText("Boids Left: " + boidFlock.size());

                //  //updating the time label
                timeLabel.setLayoutX((screenWidth - timeLabel.getWidth()) / 2);
                timeLabel.setLayoutY(20);
                timeLabel.toFront();
                timeLabel.setFont(new Font("OCR A Extended", 40));
                if (((int) time % 60) < 10) {
                    timeLabel.setText(((int) time / 60) + ":" + "0" + ((int) time % 60));
                } else {
                    timeLabel.setText(((int) time / 60) + ":" + ((int) time % 60));
                }
                time -= frameDeltaTime;
                //  //if time reaches 0, end the game
                if (time < 0 || boidFlock.isEmpty()) {
                    Endgame();
                }

                //updates player 1's score
                scorePlayer1.setText("" + playerBunch.get(0).getScore());
                //updates player 2's score
                scorePlayer2.setText("" + playerBunch.get(1).getScore());
                //updates player 3's score
                scorePlayer3.setText("" + playerBunch.get(2).getScore());
                //updates player 4's score
                scorePlayer4.setText("" + playerBunch.get(3).getScore());

                //**// COUNTER //**//
                //updates the orb animator
                if (counter % 6.0 == 0) {
                    if (orbAnimation == 7) {
                        orbAnimation = 0;
                    } else {
                        orbAnimation++;
                    }
                    for (int x = 1; x < 5; x++) {
                        //updates the orb sprites
                        orbBunch.get(x - 1).getCircle().setFill(AssetManager.playerOrbSprites(x, orbAnimation));
                    }
                }

                //**// SPRING AND MAST //**//
                //set the springs and masts to default  
                for (Spring spring : springBunch) {
                    spring.getRectangle().setFill(AssetManager.springSkin(true));
                }
                for (Mast mast : mastBunch) {
                    mast.getCircle().setFill(AssetManager.mastSkin(false));
                }

                //**// PLAYER MOVEMENT AND INTERACTIONS //**//
                //updates the player's location + movement if player exists               
                if (playerBunch.get(0).isReal) {
                    playerBunch.get(0).update(isKeyPressedP1);
                }
                if (playerBunch.get(1).isReal) {
                    playerBunch.get(1).update(isKeyPressedP2);
                }
                if (playerBunch.get(2).isReal) {
                    playerBunch.get(2).update(isKeyPressedP3);
                }
                if (playerBunch.get(3).isReal) {
                    playerBunch.get(3).update(isKeyPressedP4);
                }

                //changes the appearance of the closest mast when the player is in projectile motion and when the player exists            
                if (!isKeyPressedP1 && ArenaSetup.isPlayer1Playing()) {
                    Mast m = playerBunch.get(0).findClosestMast(mastBunch, playerBunch.get(0).getCircle().getCenterX(), playerBunch.get(0).getCircle().getCenterY());
                }
                if (!isKeyPressedP2 && ArenaSetup.isPlayer2Playing()) {
                    Mast m = playerBunch.get(1).findClosestMast(mastBunch, playerBunch.get(1).getCircle().getCenterX(), playerBunch.get(1).getCircle().getCenterY());
                }
                if (!isKeyPressedP3 && ArenaSetup.isPlayer3Playing()) {
                    Mast m = playerBunch.get(2).findClosestMast(mastBunch, playerBunch.get(2).getCircle().getCenterX(), playerBunch.get(2).getCircle().getCenterY());
                }
                if (!isKeyPressedP4 && ArenaSetup.isPlayer4Playing()) {
                    Mast m = playerBunch.get(3).findClosestMast(mastBunch, playerBunch.get(3).getCircle().getCenterX(), playerBunch.get(3).getCircle().getCenterY());
                }
                for (Player player : activePlayers) {
                    //connects the player to the orb via line
                    if (player.isReal) {
                        orbBunch.get(player.getPlayer() - 1).connectToPlayer(player);
                    }

                    //collision with spring
                    for (Spring spring : springBunch) {
                        if (spring.getRectangle().getBoundsInParent().intersects(player.getCircle().getBoundsInParent())) {
                            spring.boingg(player);
                        }
                    }

                    //kills that player if he touches the spikes
                    for (Spike spike : spikeBunch) {
                        if (spike.getRectangle().getBoundsInParent().intersects(player.getCircle().getBoundsInParent())) {
                            deathOfPlayer(player, orbBunch.get(player.getPlayer() - 1));
                        }
                    }
                }

                //**// BOUNDS //**//
                //kill the player if he goes outside the arena (horizontaly only)
                for (Player player : activePlayers) {
                    if ((player.getCircle().getCenterX() < 0) || (player.getCircle().getCenterX() > screenWidth) || (player.getCircle().getCenterY() > screenHeight)) {
                        deathOfPlayer(player, orbBunch.get(player.getPlayer() - 1));
                    }
                }

                //**// BOIDS AND PREDATORS //**//
                //moves all the boids and predators or ends the game if there are no boids left
                if (boidFlock.isEmpty()) {
                    Endgame();
                } else {
                    animateAllTheBoidzAndPredators();
                }

                if (!timeLabel.getScene().getWindow().isShowing()) {
                    ArenaSetup.p1 = playerBunch.get(0);
                    ArenaSetup.p2 = playerBunch.get(1);
                    ArenaSetup.p3 = playerBunch.get(2);
                    ArenaSetup.p4 = playerBunch.get(3);
                    ArenaSetup.isGameOver = true;
                    
                    ArenaSetup.clearWinner();

                    int winnerNumber = activePlayers.get(0).getPlayer();
                    int winnerScore = activePlayers.get(0).getScore();
                    for (int x = 1; x < activePlayers.size(); x++) {
                        if (winnerScore < activePlayers.get(x).getScore()) {
                            winnerNumber = activePlayers.get(x).getPlayer();
                            winnerScore = activePlayers.get(x).getScore();
                        }
                    }
                    ArenaSetup.winnerPlayer.add(winnerNumber);
                    for(int x = 1; x < activePlayers.size(); x++)
                    {
                        if(winnerScore == activePlayers.get(x).getScore())
                        {
                            ArenaSetup.winnerPlayer.add(activePlayers.get(x).getPlayer());
                        }
                    }
                    this.stop();
                }

                //**// HITLIST //**//
                //removal of dead boids and predators 
                for (Node no : removalCandidates) {
                    removeFromPane(no);
                }
                for (Boid kill : killFlock) {
                    boidFlock.remove(kill);
                }
                for (Predator kill : killPack) {
                    scaryPredatorFlock.remove(kill);
                }
            }
        }.start();
    }

    //warps boids from one side of the screen to the other
    public void boidsBendTheCorners(Boid boid) {
        if (boid.position.getX() > screenWidth) {
            boid.position.setX(0);
        }
        if (boid.position.getX() < 0) {
            boid.position.setX(screenWidth);
        }
        if (boid.position.getY() > screenHeight) {
            boid.position.setY(0);
        }
        if (boid.position.getY() < 0) {
            boid.position.setY(screenHeight);
        }
    }

    //warps predators from one side of the screen to the other
    public void predatorsBendTheCorners(Predator predator) {
        if (predator.position.getX() > screenWidth) {
            predator.position.setX(0);
        }
        if (predator.position.getX() < 0) {
            predator.position.setX(screenWidth);
        }
        if (predator.position.getY() > screenHeight) {
            predator.position.setY(0);
        }
        if (predator.position.getY() < 0) {
            predator.position.setY(screenHeight);
        }
    }

    //changes boid sprites
    public void animateBoidSprites(ArrayList<Boid> boid) {
        if (animationBoid == 3) {
            animationBoid = 0;
        } else {
            animationBoid++;
        }
        for (Boid bb : boid) {
            if (bb.gointLeft()) {
                bb.getRectangle().setFill(AssetManager.flippedBirds(animationBoid, bb.getBoidType()));
            } else {
                bb.getRectangle().setFill(AssetManager.normalBirds(animationBoid, bb.getBoidType()));
            }
        }
    }

    //changes predator sprite
    public void animatePredatorSprites() {
        if (animationPredator == 5) {
            animationPredator = 0;
        } else {
            animationPredator++;
        }
        for (Predator pp : predatorFlock) {
            pp.animation(true, animationPredator);
        }
        for (Predator pp : scaryPredatorFlock) {
            pp.animation(false, animationPredator);
        }
    }

    //animates boids and predators 
    public void animateAllTheBoidzAndPredators() {
        for (Boid b : boidFlock) {
            //moves boid flock
            b.run(boidFlock, predatorFlock, activePlayers);
            //warps around corners
            boidsBendTheCorners(b);

        }
        //animate both flocks' sprites
        if (counter % 6.0 == 0) {
            animateBoidSprites(boidFlock);
            animatePredatorSprites();
        }

        for (Predator p : predatorFlock) {
            //warps around corners
            predatorsBendTheCorners(p);
            //moves predator flock
            p.updatePredator(boidFlock, predatorFlock);
        }
        for (Predator pre : scaryPredatorFlock) {
            predatorsBendTheCorners(pre);
            pre.updateScaryPredator(activePlayers, scaryPredatorFlock);
        }

        //collison boid - predator
        for (Predator p : predatorFlock) {
            for (Boid boids : boidFlock) {
                if (p.getRectangle().getBoundsInParent().intersects(boids.getRectangle().getBoundsInParent())) {
                    killFlock.add(boids);
                    removalCandidates.add(boids.getRectangle());
                }
            }
        }
        //collision player - boid
        for (Player player : activePlayers) {
            for (Boid boids : boidFlock) {
                if (player.getCircle().getBoundsInParent().intersects(boids.getRectangle().getBoundsInParent())) {
                    killFlock.add(boids);
                    removalCandidates.add(boids.getRectangle());
                    //increases the player's score
                    player.setScore(player.getScore() + 1);
                }
            }
        }

        //collison player - predator
        for (Predator p : scaryPredatorFlock) {
            for (Player player : activePlayers) {
                if (p.getRectangle().getBoundsInParent().intersects(player.getCircle().getBoundsInParent())) {
                    fromScaryToNormalPredator(p, player);
                }
            }
        }
    }

    //generates masts' location based on separation
    public void generateMasts() {
        Random random = new Random();
        //creates an arraylist containing circles that will be used to determine proximity
        ArrayList<Circle> testList = new ArrayList<>();
        //adds the mandatory masts
        for (Mast mast : mastBunch) {
            testList.add(new Circle(mast.getCircle().getCenterX(), mast.getCircle().getCenterY(), ArenaSetup.getMastRadius()));
        }
        //check marker in order to avoid having an infinite loop
        int infiniteLoopAvoid = 0;

        while (testList.size() < ArenaSetup.getNumberOfMasts() + 4) {
            //records the size of the testlist before any change
            int previousSize = testList.size();
            //return false if the circle doesn't neet the criteria
            boolean willInclude = true;
            //creates a circle with random location
            Circle test = new Circle();
            test.setCenterX(random.nextInt((int) screenWidth - 400) + 200);
            test.setCenterY(random.nextInt((int) screenHeight - 200) + 100);
            test.setRadius(ArenaSetup.getMastRadius());

            //checks if the circle respects the separation radius
            for (Circle circle : testList) {
                if (circle.getBoundsInParent().intersects(test.getBoundsInParent())) {
                    willInclude = false;
                }
            }
            //includes the successful circles
            if (willInclude) {
                testList.add(test);
                infiniteLoopAvoid = 0;
            }

            //counts the number of unsuccessful iterations
            if (previousSize == testList.size()) {
                infiniteLoopAvoid++;
            }
            //if the number of unsuccessful iterations is too big, break the loop
            if (infiniteLoopAvoid > 20) {
                break;
            }
        }

        //replace the circle with masts
        for (int x = 4; x < testList.size(); x++) {
            Mast newMast = new Mast(testList.get(x).getCenterX(), testList.get(x).getCenterY());
            mastBunch.add(newMast);
            addToPane(newMast.getCircle());

        }
    }

    //generates springs' location based on separation
    public void generateSprings() {
        Random random = new Random();
        //creates an arraylist containing circles that will be used to determine proximity
        ArrayList<Circle> testList = new ArrayList<>();
        //adds the mandatory spings
        for (Spring spring : springBunch) {
            Circle mandatoryCircle = new Circle();
            mandatoryCircle.setRadius(ArenaSetup.getSpringRadius());
            mandatoryCircle.setCenterX(spring.getRectangle().getX() + 50);
            mandatoryCircle.setCenterY(spring.getRectangle().getY() + 10);
            testList.add(mandatoryCircle);
        }
        //check marker in order to avoid having an infinite loop
        int infiniteLoopAvoid = 0;

        //swaps the line on which the spring will be located
        boolean topSpringFirst = true;

        while (testList.size() < ArenaSetup.getNumberOfSprings() + 4) {
            //records the size of the testlist before any change
            int previousSize = testList.size();
            //return false if the circle doesn't neet the criteria
            boolean willInclude = true;

            //creates a circle with random location
            Circle test = new Circle();

            if (topSpringFirst) {
                test.setCenterX(random.nextInt((int) screenWidth - 400) + 200);
                test.setCenterY(testList.get(3).getCenterY() + 100);
                test.setRadius(ArenaSetup.getSpringRadius());
            } else {
                test.setCenterX(random.nextInt((int) screenWidth - 400) + 200);
                test.setCenterY(testList.get(0).getCenterY() + 100);
                test.setRadius(ArenaSetup.getSpringRadius());
            }

            //checks if the circle respects the separation radius
            for (Circle circle : testList) {
                if (circle.getBoundsInParent().intersects(test.getBoundsInParent())) {
                    willInclude = false;
                }

            }
            //includes the successful circles
            if (willInclude) {
                testList.add(test);
                infiniteLoopAvoid = 0;
            }

            //counts the number of unsuccessful iterations
            if (previousSize == testList.size()) {
                infiniteLoopAvoid++;
            }
            //if the number of unsuccessful iterations is too big, break the loop
            if (infiniteLoopAvoid > 20) {
                break;
            }

            topSpringFirst = !topSpringFirst;
        }

        //replace the circle with springs
        for (int x = 4; x < testList.size(); x++) {
            Spring newSpring = new Spring(testList.get(x).getCenterX() - 50, testList.get(x).getCenterY() - 10);
            springBunch.add(newSpring);
            addToPane(newSpring.getRectangle());

        }
    }

    //consequences of a player's death
    public void deathOfPlayer(Player player, Orb orb) {
        //spawns 5 new boids
        for (int x = 0; x < 6; x++) {
            Boid boid = new Boid(player.getCircle().getCenterX(), player.getCircle().getCenterY());
            boidFlock.add(boid);
            addToPane(boid.getRectangle());
        }
        //reduce the player's score
        player.setScore(player.getScore() - 5);

        //changes the player's location
        player.getCircle().setCenterX(orb.getCircle().getCenterX());
        player.getCircle().setCenterY(orb.getCircle().getCenterY());
        player.normalVelocityX = 0;
        player.normalVelocityY = 0;
        player.angularVelocity = 0;
        player.switchMovements(mastBunch, player.getCircle().getCenterX(), player.getCircle().getCenterY(), 2);

        //spawns a scary predator 
        Predator scary = new Predator(screenWidth / 2, screenHeight / 2, true);
        scaryPredatorFlock.add(scary);
        addToPane(scary.getRectangle());
    }

    //transforms a scary predator into a normal predator on collision with player
    public void fromScaryToNormalPredator(Predator scaryPredator, Player player) {
        //penalises the player
        player.setScore(player.getScore() - 10);
        //creates new normal predator 
        Predator replacement = new Predator(scaryPredator.getPosition().getX(), scaryPredator.getPosition().getY(), false);
        predatorFlock.add(replacement);
        addToPane(replacement.getRectangle());
        //removes scary predator 
        killPack.add(scaryPredator);
        removalCandidates.add(scaryPredator.getRectangle());

    }

    //closes the game
    public void Endgame() {
        timeLabel.getScene().getWindow().hide();
    }

    public void addToPane(Node node) {
        panel.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        panel.getChildren().remove(node);
    }

}

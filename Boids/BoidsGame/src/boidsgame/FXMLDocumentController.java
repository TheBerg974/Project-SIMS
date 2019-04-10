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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private Label nbOfBoidsRemain;
    @FXML
    private Label timeLabel;

    private double lastFrameTime = 0.0;
    int animationBoid;
    int animationPredator = 1;
    int counter = 0;

    boolean isKeyPressed = false;
    boolean isPlayerDead = false;

    //changes between the animation sprites for the orbs
    int orbAnimation = 1;
    double time = ArenaSetup.getTime() + 1;

    //for swing
    int onButPressedCheck = 0;
    int onButReleasedCheck = 0;

    //gets the screen size in order to place the elements of the game
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    final double screenWidth = screenBounds.getWidth() - 10;
    final double screenHeight = screenBounds.getHeight() - 40;

    private ArrayList<Boid> boidFlock = new ArrayList<>();
    private ArrayList<Predator> predatorFlock = new ArrayList<>();
    private ArrayList<Predator> scaryPredatorFlock = new ArrayList<>();
    private ArrayList<Boid> killFlock = new ArrayList<>();
    private ArrayList<Node> removalCandidates = new ArrayList<>();

    private ArrayList<Player> playerBunch = new ArrayList<>();
    private ArrayList<Mast> mastBunch = new ArrayList<>();
    private ArrayList<Spring> springBunch = new ArrayList<>();
    private ArrayList<Spike> spikeBunch = new ArrayList<>();
    private ArrayList<Orb> orbBunch = new ArrayList<>();

    @FXML
    private void handleClickButton(MouseEvent e) {
//        Boid b = new Boid(e.getSceneX(), e.getSceneY());
//        boidFlock.add(b);
//        addToPane(b.getRectangle());

//        Predator p = new Predator(e.getSceneX(), e.getSceneY());
//        predatorFlock.add(p);
//        addToPane(p.getRectangle());
//        Predator p2 = new Predator(e.getSceneX(), e.getSceneY());
//        scaryPredatorFlock.add(p2);
//        addToPane(p2.getRectangle());
        Player player = new Player(e.getSceneX(), e.getSceneY(), mastBunch, 1);
        playerBunch.add(player);
        addToPane(player.getCircle());
        addToPane(player.getString());
    }

    @FXML
    private void onButtonPressed(KeyEvent b) {
        if (b.getCode() == KeyCode.M) {
            if (!isPlayerDead) {
                isKeyPressed = true;
                if (onButPressedCheck == 0) {
                    for (Player play : playerBunch) {
                        play.switchMovements(mastBunch, play.getCircle().getCenterX(), play.getCircle().getCenterY(), 2);
                    }

                }
                onButPressedCheck++;
                onButReleasedCheck = 0;
            }
        }
    }

    @FXML
    private void onButtonReleased(KeyEvent b) {
        if (b.getCode() == KeyCode.M) {
            isKeyPressed = false;
            if (onButReleasedCheck == 0) {
                for (Player play : playerBunch) {
                    play.switchMovements(mastBunch, play.getCircle().getCenterX(), play.getCircle().getCenterY(), 1);
                }
            }
            onButReleasedCheck++;
            onButPressedCheck = 0;
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

        //**// MASTS //**// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //creates and adds masts to the panel
        for (int x = 0; x < 20; x++) {
            Mast mast = new Mast();
            mastBunch.add(mast);
            addToPane(mast.getCircle());
        }
        
        //**// SPRINGS //**// ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //creates and adds springs to the panel
        for (int x = 0; x < 12; x++) {
            Spring spring = new Spring(150 * x, 500);
            springBunch.add(spring);
            addToPane(spring.getRectangle());
        }
        for (int x = 0; x < 12; x++) {
            Spring spring = new Spring(150 * x, 960);
            springBunch.add(spring);
            addToPane(spring.getRectangle());
        }

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
        
        //**// PLAYERS //**//  //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Player player = new Player(100, 100, mastBunch, 1);
        playerBunch.add(player);
        addToPane(player.getCircle());
        addToPane(player.getString());

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
        /////ANIMATION TIMER////////////////////////////////////////////////////
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                counter++;
                
                //updating the total boid counter label
                nbOfBoidsRemain.setLayoutX((screenWidth - nbOfBoidsRemain.getWidth()) / 2);
                nbOfBoidsRemain.setLayoutY(50);
                nbOfBoidsRemain.toFront();
                nbOfBoidsRemain.setFont(new Font("Harrington", 40));
                nbOfBoidsRemain.setText("Boids Left: " + boidFlock.size());

                //updating the time label
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
                if (time < 0 || boidFlock.size() == 0) {
                    Endgame();
                }

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
                //player's movement and interactions //////////////////////////////////////////////////////////////////////////////////////////////
                for (Player player : playerBunch) {
                    //updates the player's location + movement
                    player.update(isKeyPressed);

                    //connects the player to the orb via line
                    orbBunch.get(player.getPlayer()-1).connectToPlayer(player);

                    //collision with springs + interaction with spring
                    for (Spring spring : springBunch) {
                        spring.getRectangle().setFill(AssetManager.springSkin(true));
                        if (spring.getRectangle().getBoundsInParent().intersects(player.getCircle().getBoundsInParent())) {
                            spring.boingg(player);
                        }
                    }

                    //changes the appearance of the closest mast & sets the others to default
                    for (Mast mast : mastBunch) {
                        mast.getCircle().setFill(AssetManager.mastSkin(false));
                    }
                    if (!isKeyPressed) {
                        Mast m = player.findClosestMast(mastBunch, player.getCircle().getCenterX(), player.getCircle().getCenterY());
                        m.getCircle().setFill(AssetManager.mastSkin(true));
                    }
                    //kills that player if he gets too close
                    for (Spike spike : spikeBunch) {
                        if (spike.getRectangle().getBoundsInParent().intersects(player.getCircle().getBoundsInParent())) {
                            deathOfPlayer(player, orbBunch.get(player.getPlayer() - 1));                            
                        }
                    }
                }
                //kill the player if he goes outside the arena (horizontaly only)
                for (Player player : playerBunch) {
                    if ((player.getCircle().getCenterX() < 0) || (player.getCircle().getCenterX() > screenWidth) || (player.getCircle().getCenterY() > screenHeight)) {
                        deathOfPlayer(player, orbBunch.get(player.getPlayer() - 1));
                    }
                }

                //moves all the boids and predators or ends the game if there are no boids left
                if (boidFlock.isEmpty()) {
                    Endgame();
                } else {
                    animateAllTheBoidzAndPredators();
                }

                //removal of dead boids
                for (Node no : removalCandidates) {
                    removeFromPane(no);
                }
                for (Boid kill : killFlock) {
                    boidFlock.remove(kill);
                }

            }
        }.start();
    }

    //warps boids from one side of the screen to the other
    public void boidsBendTheCorners(Boid boid) 
    {
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
    public void animateBoidSprites(ArrayList<Boid> boid) {///different sprites + direction!!
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

    public void animateAllTheBoidzAndPredators() {
        for (Boid b : boidFlock) {
            //moves boid flock
            b.run(boidFlock, predatorFlock, playerBunch);
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
            pre.updateScaryPredator(playerBunch, scaryPredatorFlock);
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
        for (Player player : playerBunch) {
            for (Boid boids : boidFlock) {
                if (player.getCircle().getBoundsInParent().intersects(boids.getRectangle().getBoundsInParent())) {
                    killFlock.add(boids);
                    removalCandidates.add(boids.getRectangle());
                }
            }
        }

        //collison player - predator
        for (Predator p : scaryPredatorFlock) {
            for (Player player : playerBunch) {
                if (p.getRectangle().getBoundsInParent().intersects(player.getCircle().getBoundsInParent())) {
                    //deathOfPlayer(player, orbBunch.get(0));
                }
            }
        }
    }

    public void deathOfPlayer(Player player, Orb orb) {

        for (int x = 0; x < 6; x++) {
            Boid boid = new Boid(player.getCircle().getCenterX(), player.getCircle().getCenterY());
            boidFlock.add(boid);
            addToPane(boid.getRectangle());
        }

        player.getCircle().setCenterX(orb.getCircle().getCenterX());
        player.getCircle().setCenterY(orb.getCircle().getCenterY());
        player.normalVelocityX = 0;
        player.normalVelocityY = 0;
        player.angularVelocity = 0;
        player.switchMovements(mastBunch, player.getCircle().getCenterX(), player.getCircle().getCenterY(), 2);

        Predator scary = new Predator(screenWidth / 2, screenHeight / 2, true);
        //scary.setMaxSpeed(1);
        scaryPredatorFlock.add(scary);
        addToPane(scary.getRectangle());
    }

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

package boidsgame;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane panel;
    private double lastFrameTime = 0.0;
    int animationBoid;
    int animationPredator;
    int counter = 0;

    private ArrayList<Boid> boidFlock = new ArrayList<>();
    private ArrayList<Predator> predatorFlock = new ArrayList<>();
    private ArrayList<Boid> killFlock = new ArrayList<>();
    private ArrayList<Node> removalCandidates = new ArrayList<>();
    
    

    @FXML
    private void handleClickButton(MouseEvent e) {
//        Boid b = new Boid(e.getSceneX(), e.getSceneY());
//        boidFlock.add(b);
//        addToPane(b.getRectangle());

//        Predator p = new Predator(e.getSceneX(), e.getSceneY());
//        predatorFlock.add(p);
//        addToPane(p.getRectangle());
        
        
    }

    ///////////INITIALIZE///////////////////////////////////////////////////////    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //panel.setCursor(Cursor.NONE); this is to hide the cursor.... not right now tho
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();
        AssetManager.getAllAssets();

        //spawning Boids and adds them to the panel
        for (int x = 0; x < 300; ++x) {
            boidFlock.add(new Boid(400, 300));
            boidFlock.add(new Boid(000, 000));
            boidFlock.add(new Boid(700, 700));
        }
        for (Boid b : boidFlock) {
            addToPane(b.getRectangle());
        }
        //creates a predator and adds it to the panel
        predatorFlock.add(new Predator(20, 20));
        for (Predator p : predatorFlock) {
            addToPane(p.getRectangle());
        }
        
        Player player = new Player();
        addToPane(player.getCircle());
        
        
        /////ANIMATION TIMER////////////////////////////////////////////////////
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
                counter++;
                
                             
                
                //moves all the boids and predators
                //animateAllTheBoidzAndPredators();
                
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
    public void boidsBendTheCorners(Boid boid) //arena change///////////////////////////////////////////////////////////////////////////
    {
        if (boid.position.getX() > panel.getWidth()) {
            boid.position.setX(0);
        }
        if (boid.position.getX() < 0) {
            boid.position.setX(panel.getWidth());
        }
        if (boid.position.getY() > panel.getHeight()) {
            boid.position.setY(0);
        }
        if (boid.position.getY() < 0) {
            boid.position.setY(panel.getHeight());
        }
    }
    
    //warps predators from one side of the screen to the other
    public void predatorsBendTheCorners(Predator predator) {
        if (predator.position.getX() > panel.getWidth()) {
            predator.position.setX(0);
        }
        if (predator.position.getX() < 0) {
            predator.position.setX(panel.getWidth());
        }
        if (predator.position.getY() > panel.getHeight()) {
            predator.position.setY(0);
        }
        if (predator.position.getY() < 0) {
            predator.position.setY(panel.getHeight());
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
    public void animatePredatorSprites(ArrayList<Predator> predator) {
        if (animationPredator == 5) {
            animationPredator = 0;
        } else {
            animationPredator++;
        }
        for (Predator pp : predator) {
            if (pp.isGoingLeft()) {
                pp.getRectangle().setFill(AssetManager.flippedPredators(animationPredator));
            } else {
                pp.getRectangle().setFill(AssetManager.normalPredators(animationPredator));
            }
        }
    }
    
    public void animateAllTheBoidzAndPredators()
    {
        for (Boid b : boidFlock) {
                    //moves boid flock
                    b.run(boidFlock,predatorFlock);
                    //warps around corners
                    boidsBendTheCorners(b);

                }
                //animate both flocks' sprites
                if (counter % 6.0 == 0) {
                    animateBoidSprites(boidFlock);
                    animatePredatorSprites(predatorFlock);
                }

                for (Predator p : predatorFlock) {
                    //warps around corners
                    predatorsBendTheCorners(p);
                    //moves predator flock
                    p.updatePredator(boidFlock, predatorFlock);
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
    }
    
    public void addToPane(Node node) {
        panel.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        panel.getChildren().remove(node);
    }

}

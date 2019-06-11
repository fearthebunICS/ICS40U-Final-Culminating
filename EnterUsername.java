import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 2
 * June 10, 2019
 * Time spent: 2 hours 30 minutes
 * EnterUsername class extends Application
 * Creates an enter username screen
 * Modification: added different methods to help store the TextField input
 *
 * Variable        Type       Purpose
 * stage           Stage      creates the stage
 * name                String     name entered into the textfield
 * confirm         Button     creates the ok button
 * text            TextField  place where user enters name
 *
 */
public class EnterUsername {

    /**
     * Creates the stage
     */
    private Stage stage;

    /**
     * The name entered into the text field.
     */
    private String name;

    /**

     * The confirm button.

     */

    private Button confirm;

    /**

     * The textfield where the user enter's their name.

     */

    private TextField text;

    /**
     * Constructor of the class
     * @param stage Stage Object
     */
    public EnterUsername(Stage stage) {

        this.stage = stage;
    }

    /**
     * Creates a scene and adds an enter username to the screen using hbox
     */
    public void start() {
        try {

            // set title for the stage
            stage.setTitle("Paper Bag Inc.");

            // create a text field
            text = new TextField();

            // set preferred column count
            text.setPrefColumnCount(20);

            //create a button
            Image img = new Image(getClass().getResourceAsStream("Resources/OK button.png"));
            confirm = new Button();
            confirm.setGraphic(new ImageView(img));
            confirm.setStyle("-fx-background-color: transparent;");



            // add the label, text field and button
            HBox hbox = new HBox(text, confirm);

            // set spacing
            hbox.setSpacing(10);

            // set alignment for the HBox
            hbox.setAlignment(Pos.CENTER);

            Image newGame = new Image("Resources/enterUsername.png");
            BackgroundImage buttonImage = new BackgroundImage(newGame, null, null, null, null);
            Background background = new Background(buttonImage);

            // set background
            hbox.setBackground(background);

            // set the scene
            // create a scene
            Scene scene = new Scene(hbox, 1120, 600);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }


    }

    /**
     * @return the name from the text field
     */

    public String getName() {

        return name;

    }


    /**
     * sets the instance variable name to the parameter name
     * @param name name of the TextField
     */
    public void setName(String name) {

        this.name = name;

    }



    /**
     *
     * @return the confirm button
     */

    public Button getConfirm() {

        return confirm;

    }

    /**

     * @return the text textfield
     */

    public TextField getText() {

        return text;
    }
}

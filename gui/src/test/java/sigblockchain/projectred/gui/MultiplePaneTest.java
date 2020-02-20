package sigblockchain.projectred.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


public class MultiplePaneTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("scene.fxml"));

		stage.setScene(new Scene(root));
		stage.show();
	}

	@Test
	public void testSwitchingPane(){

	}
}

package sigblockchain.projectred.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)

public class MultiplePaneTest {

	@Start
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("scene.fxml"));

		stage.setScene(new Scene(root));
		stage.show();
	}

	@Test
	public void testSwitchingPane() {

	}
}

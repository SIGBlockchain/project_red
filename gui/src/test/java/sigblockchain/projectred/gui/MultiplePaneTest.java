package sigblockchain.projectred.gui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

@ExtendWith(ApplicationExtension.class)

public class MultiplePaneTest {

	@Start
	private void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource("scene.fxml"));

		stage.setScene(new Scene(root));
		stage.show();
	}

	@Test
	public void testSwitchingPane(FxRobot robot) {
		robot.clickOn("#navKeyBtn");

		FxAssert.verifyThat("#navKeyBtn", LabeledMatchers.hasText("Keys"));
		assertFalse(robot.lookup("#accountsPane").queryAs(Pane.class).isVisible());
		assertTrue(robot.lookup("#accountsPane").queryAs(Pane.class).isDisabled());
		assertTrue(robot.lookup("#keysPane").queryAs(Pane.class).isVisible());
		assertFalse(robot.lookup("#keysPane").queryAs(Pane.class).isDisabled());

		robot.clickOn("#navAccountsBtn");
		assertTrue(robot.lookup("#accountsPane").queryAs(Pane.class).isVisible());
		assertFalse(robot.lookup("#accountsPane").queryAs(Pane.class).isDisabled());
		assertFalse(robot.lookup("#keysPane").queryAs(Pane.class).isVisible());
		assertTrue(robot.lookup("#keysPane").queryAs(Pane.class).isDisabled());
	}
}

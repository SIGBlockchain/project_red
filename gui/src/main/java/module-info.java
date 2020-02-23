module sigblockchain.projectred.gui {
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires org.apache.logging.log4j;

	opens sigblockchain.projectred.gui to javafx.fxml, javafx.graphics;
	exports sigblockchain.projectred.gui;
}
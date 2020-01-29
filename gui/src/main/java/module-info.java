module sigblockchain.projectred.gui {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    opens sigblockchain.projectred.gui to javafx.fxml, javafx.graphics;
    exports sigblockchain.projectred.gui;
}
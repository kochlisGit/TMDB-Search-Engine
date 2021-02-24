package controls;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

import java.util.List;

public abstract class ComboBoxDecorator<T>
{
    protected final ComboBox<T> cmb;
    private final List<T> cmbFields;

    public ComboBoxDecorator(ComboBox<T> cmb) {
        this.cmb = cmb;
        this.cmbFields = buildItemList();
    }

    protected abstract List<T> buildItemList();

    protected abstract ListCell<T> getCellFactory();
    protected abstract ListCell<T> getButtonCell();

    public void decorate() {
        cmb.setItems( FXCollections.observableList(cmbFields) );
        cmb.setCellFactory( param -> getCellFactory() );
        cmb.setButtonCell( getButtonCell() );
        cmb.getSelectionModel().selectFirst();
    }
}

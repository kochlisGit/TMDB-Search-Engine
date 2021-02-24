package controls;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.util.Arrays;
import java.util.List;

public class FieldBoxDecorator extends ComboBoxDecorator<FieldItem>
{
    public FieldBoxDecorator(ComboBox<FieldItem> cmb) {
        super(cmb);
    }

    @Override
    protected List<FieldItem> buildItemList() {
        return Arrays.asList(
                new FieldItem("Titles", FontAwesome.Glyph.FILM),
                new FieldItem("Keywords", FontAwesome.Glyph.KEY),
                new FieldItem("Actors", FontAwesome.Glyph.USERS)
        );
    }

    private static class FieldListCell extends ListCell<FieldItem>
    {
        @Override
        protected void updateItem(FieldItem item, boolean isEmpty) {
            super.updateItem(item, isEmpty);
            if (item == null || isEmpty) {
                setText(null);
                setGraphic(null);
            }
            else {
                setText( item.getFieldName() );
                Glyph glyph = new Glyph( "FontAwesome", item.getFieldGlyph() );
                glyph.setColor( Color.valueOf("#cfd8dc") );
                setGraphic(glyph);
            }
        }
    }
    private static class ButtonCell extends ListCell<FieldItem>
    {
        @Override
        protected void updateItem(FieldItem item, boolean isEmpty) {
            super.updateItem(item, isEmpty);
            if (item == null || isEmpty) {
                setText(null);
            }
            else {
                setText( item.getFieldName() );
            }
        }
    }

    @Override protected ListCell<FieldItem> getCellFactory() {
        return new FieldListCell();
    }
    @Override protected ListCell<FieldItem> getButtonCell() {
        return new ButtonCell();
    }
}

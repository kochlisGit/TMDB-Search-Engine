package controls;

import org.controlsfx.glyphfont.FontAwesome;

public class FieldItem
{
    private final String fieldName;
    private final FontAwesome.Glyph fieldGlyph;

    public FieldItem(String fieldName, FontAwesome.Glyph fieldGlyph) {
        this.fieldName = fieldName;
        this.fieldGlyph = fieldGlyph;
    }

    public String getFieldName() {
        return fieldName;
    }
    public FontAwesome.Glyph getFieldGlyph() {
        return fieldGlyph;
    }
}

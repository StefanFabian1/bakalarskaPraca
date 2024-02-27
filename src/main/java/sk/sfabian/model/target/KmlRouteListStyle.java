package sk.sfabian.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ListStyle")
public class KmlRouteListStyle {
    @Element
    private String listItemType;

    @Element
    private String bgColor;

    @Element
    private int maxSnippetLines;

    public String getListItemType() {
        return listItemType;
    }

    public void setListItemType(String listItemType) {
        this.listItemType = listItemType;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public int getMaxSnippetLines() {
        return maxSnippetLines;
    }

    public void setMaxSnippetLines(int maxSnippetLines) {
        this.maxSnippetLines = maxSnippetLines;
    }
}

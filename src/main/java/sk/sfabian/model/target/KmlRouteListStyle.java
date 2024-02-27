package sk.sfabian.model.target;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ListStyle")
public class KmlRouteListStyle {
    @Element(required = false)
    private String listItemType;

    @Element(required = false)
    private String bgColor;

    @Element(required = false)
    private Integer maxSnippetLines;

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

    public Integer getMaxSnippetLines() {
        return maxSnippetLines;
    }

    public void setMaxSnippetLines(Integer maxSnippetLines) {
        this.maxSnippetLines = maxSnippetLines;
    }
}

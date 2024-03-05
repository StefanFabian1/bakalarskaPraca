package sk.sfabian.model.target.tour;

import org.simpleframework.xml.Element;
import sk.sfabian.business.DataProcessHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KmlRouteTimeSpan {


    public KmlRouteTimeSpan() {}
    public KmlRouteTimeSpan(LocalDateTime start, LocalDateTime actual) {
        this.begin = start.format(DataProcessHelper.formatter);
        this.end = actual.format(DataProcessHelper.formatter);
    }
    @Element
    private String begin;
    @Element
    private String end;
    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

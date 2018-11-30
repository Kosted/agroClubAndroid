package helperClasses;

import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

public class Swipe {

    public long time;

    public PointOption start;
    public PointOption end;

    public Swipe(WebActions web_a, int startX, int startY, int endX, int endY, long time) {
        Dimension size = web_a.getDriver().manage()
                .window()
                .getSize();

        this.time = time;
        start = new PointOption();
        end = new PointOption();
        start.withCoordinates(size.width /100 * startX, size.height /100 *startY).build();
        end.withCoordinates(size.width /100 *endX, size.height /100 *endY).build();


//        this.start.x = startX;
//        this.start.y = startY;
//        this.end.x = endX;
//        this.end.y = endY;
    }

    public void setStart(int x, int y) {
        start.withCoordinates(x, y);
    }

    public void setEnd(int x, int y) {
        end.withCoordinates(x, y);
    }
}

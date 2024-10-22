package pages;
import core.element.MobileObject;
import org.openqa.selenium.By;

public class NavigationBar {
    protected MobileObject btnView(String label){
        return new MobileObject(By.xpath("//android.view.View[@content-desc='" + label + "']"));
    }

    public void clickView(String label){
        btnView(label).tapByW3C();
    }
}

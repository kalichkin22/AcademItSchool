import ru.kalichkin.temperature.common.TemperatureConverter;
import ru.kalichkin.temperature.common.View;
import ru.kalichkin.temperature.controller.Controller;
import ru.kalichkin.temperature.view.AppView;

public class TemperatureApplication {
    public static void main(String[] args) {
        View view = new AppView();
        Controller controller = new Controller(view);
        view.addViewListener(controller);
        view.startApplication();
    }
}


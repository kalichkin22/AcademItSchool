import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.controller.Controller;
import ru.academits.kalichkin.minesweeper.model.Field;
import ru.academits.kalichkin.minesweeper.text.Text;


public class MinesweeperApp {
    public static void main(String[] args) {
        View view = new Text();
        Field field = new Field(9, 10);
        Controller controller = new Controller(field, view);
        view.setViewListener(controller);
        view.startApplication();
    }
}

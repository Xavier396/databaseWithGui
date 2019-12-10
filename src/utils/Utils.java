package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Utils {

    /*
     * type=0:警告框,只有1个按钮
     * type=1:信息框,只有1个按钮
     * type=2:确认框,可以有2个按钮
     * type=3:错误框,只有1个按钮
     * */

    public static Optional<ButtonType> dialogCreator(int type, String title, String content, String header)
    {
        Optional<ButtonType> res=null;
        switch (type)
        {
            case 0:
                Alert information = new Alert(Alert.AlertType.WARNING,content);
                information.setTitle(title); //设置标题，不设置默认标题为本地语言的information
                information.setHeaderText(header);
                res=information.showAndWait();
                break;
            case 1:
                Alert in = new Alert(Alert.AlertType.INFORMATION,content);
                in.setTitle(title); //设置标题，不设置默认标题为本地语言的information
                in.setHeaderText(header);
                res=in.showAndWait();
                break;
            case 2:
                Alert info = new Alert(Alert.AlertType.CONFIRMATION,content);
                info.setTitle(title); //设置标题，不设置默认标题为本地语言的information
                info.setHeaderText(header); //设置头标题，默认标题为本地语言的information
                res=info.showAndWait();
                break;
            case 3:
                Alert inf = new Alert(Alert.AlertType.ERROR,content);
                inf.setTitle(title); //设置标题，不设置默认标题为本地语言的information
                inf.setHeaderText(header); //设置头标题，默认标题为本地语言的information
                res=inf.showAndWait();
                break;

        }
        return res;
    }
}

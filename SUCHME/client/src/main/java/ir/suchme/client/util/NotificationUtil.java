package ir.suchme.client.util;

import ir.suchme.common.dto.base.BaseResponseDTO;
import javafx.scene.control.Alert;

import java.util.Objects;

/**
 * Created by mohammad on 6/9/17.
 */
public class NotificationUtil {

    public static boolean check(BaseResponseDTO responseDTO){
        if(!Objects.equals(responseDTO.getResponseCode(), "0")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(responseDTO.getError());
            alert.showAndWait();
            return true;
        }
        return false;
    }

    public static void OK(BaseResponseDTO responseDTO){
        if(!check(responseDTO)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("موفقیت آمیز بود");
            alert.showAndWait();
        }
    }
}

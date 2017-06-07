//package ir.suchme.client;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.Collection;
//import java.util.Locale;
//import java.util.ResourceBundle;
//
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.NodeOrientation;
//import javafx.scene.Parent;
//
//public class FxmlUtil {
//	public static Parent loadFxml(URL url, ResourceBundle resource, Locale locale, Collection<? extends String> styleSheets) throws IOException {
//		FXMLLoader loader = new FXMLLoader(url);
//		loader.setResources(resource);
//		Parent root = loader.load();
//		if(locale != null)
//			root.setNodeOrientation(getNodeOrientation(locale));
//		if(styleSheets != null && styleSheets.size() != 0)
//			root.getStylesheets().addAll(styleSheets);
//		return root;
//	}
//
//	public static Parent loadFxml(URL url, ResourceBundle resource, Locale locale) throws IOException {
//		return loadFxml(url, resource, locale, null);
//	}
//
//	public static NodeOrientation getNodeOrientation(Locale locale) {
//		if(locale.getLanguage().equals("fa"))
//			return NodeOrientation.RIGHT_TO_LEFT;
//		else if(locale.getLanguage().equals("en"))
//			return NodeOrientation.LEFT_TO_RIGHT;
//		else
//			return NodeOrientation.INHERIT;
//	}
//}

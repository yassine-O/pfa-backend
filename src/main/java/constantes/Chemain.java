package constantes;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Chemain {
	
	public static Path pathQuestion(long id) {
		return Paths.get(System.getProperty("user.home")+"/pfaApp/questions", id+".mp4");
	}
	
	public static Path pathEntretien(long id) {
		return Paths.get(System.getProperty("user.home")+"/pfaApp/entretiens", id+".mp4");
	}
	
	public static Path pathPresentation(long id) {
		return Paths.get(System.getProperty("user.home")+"/pfaApp/presentations", id+".mp4");
	}

}

import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;


public class Pictures {
	static Image platform;
	Image ball;
	URL url;
	static StartingPoint sp;
	static AudioClip Bounce;
	static AudioClip back;
	static int level = 1;
	public Pictures(StartingPoint sp){
		try{
			url = sp.getDocumentBase();
		}catch(Exception e){
			
		}
		
		Bounce = sp.getAudioClip(url, "Music/bounce.au");
		back = sp.getAudioClip(url, "Music/Back.au");
		platform = sp.getImage(url, "images/Tiles.png");
		
		this.sp = sp;
	}
}

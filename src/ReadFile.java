import java.io.*;

import java.util.*;

public class ReadFile{

	private Formatter xr;
	StartingPoint sp;
	private Scanner x;
	String a;
	int score;
	
	public void openFile(){
		try{
			x = new Scanner(new File("HighScore.txt"));
		}catch(Exception e){
			
		}
	}
	
	public void ReadFile(){
		while(x.hasNext()){
			a = x.next();
			x.close();
			
		}
	}
	public int getString(){
		score = Integer.parseInt(a);
		return score;
	}
	
	public boolean setWrite(StartingPoint sp){
		this.sp = sp;
		if(sp.getScore()<getString()){
			return true;
		}else {
			return false;
		}
	}
	
	public void WriteFile(){
		if(setWrite(sp)==true){
			xr.format("%d", sp.getScore());
			xr.close();
		}else {
			xr.format("%d", a);
			xr.close();
		}
		
	}
	
}


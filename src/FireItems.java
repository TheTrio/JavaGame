import java.awt.Color;
import java.io.File;
import java.lang.*;
import java.util.*;

import java.awt.Graphics;


public class FireItems {
	private Formatter x;
	StartingPoint sp;
	public FireItems(){
		
	}

	public void write() {
		try{
			x = new Formatter("C:\\Users\\Shashwat\\Desktop\\HighScore.txt");
		}catch(Exception e){
			
		}
		
		
	}
	
	public void AddRecord(StartingPoint sp){
		this.sp = sp;
		int scoreNum = sp.getScore();
		x.format("%d",scoreNum);
		x.close();
	}
}

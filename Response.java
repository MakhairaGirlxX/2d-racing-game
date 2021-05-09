import java.util.*;

public interface Response {	
	int changeInPosition(int dieThrow, int playerPos, PositionInfo handler);	
}

class TypeOneResponse implements Response{	
	public int changeInPosition(int dieThrow, int playerPos, PositionInfo handler){
		int change = dieThrow + ( handler.getLeadingPlayerPosition() - playerPos ) / 2;
		return (dieThrow > 2)? change : -change;
	}	
	
	public String toString(){ return "Type 1"; }
}

class TypeTwoResponse implements Response{	
	public int changeInPosition(int dieThrow, int playerPos, PositionInfo handler){
		return ( dieThrow % 2 == 0 )? 3 * dieThrow : dieThrow;
	}	
	public String toString(){ return "Type 2"; }
}

class TypeThreeResponse implements Response{	
	public int changeInPosition(int dieThrow, int playerPos, PositionInfo handler){
		int change = dieThrow + ( playerPos - handler.getTrailingPlayerPosition() ) / 2;
		return (dieThrow > 2)? -change : change;
	}
	
	public String toString(){ return "Type 3"; }
}

class ResponseTypeFactory{
	public static Response getResponseType(){
		Random rnd = new Random();
		int selection = 1 + rnd.nextInt( 3 );
		Response response = null;
		switch( selection ){
		case 1: response = new TypeOneResponse(); 
				break;
		case 2: response = new TypeTwoResponse();
				break;
		case 3: response = new TypeThreeResponse();	
		}
		return response;
	}
}
	
	

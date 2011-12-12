package control;

public class OthelloTimer {
	public static long startTime;
	public static long endTime;
	public static long roundStartTime;
	public static long roundEndTime;
	
	public static void reset() {
		startTime = System.currentTimeMillis();
		endTime = System.currentTimeMillis();
		roundStartTime = System.currentTimeMillis();
		roundEndTime = System.currentTimeMillis();
	}
	
	public static void resetRound() {
		roundStartTime = System.currentTimeMillis();
		roundEndTime = System.currentTimeMillis();
	}
	
	public static long roundTimeElapsed() {
		roundEndTime = System.currentTimeMillis();
		return roundEndTime - roundStartTime;
	}
	
	public static long timeElapsed() {
		endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

}

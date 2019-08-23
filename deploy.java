import java.io.*;

class deploy
{
	public static void main(String[] args) throws Exception{
		String s=null;
		int returnval=1;
		String name="Anirudh";
		
		String command="python base.py "+name+" "+Integer.toString(returnval);
		// System.out.println(command);
		Process p = Runtime.getRuntime().exec(command);

		// String command="python base.py "+Integer.toString(returnval);
		// Process p = Runtime.getRuntime().exec(command);

		// BufferedReader stdInput = new BufferedReader(new 
  //                InputStreamReader(p.getInputStream()));

		// while ((s = stdInput.readLine()) != null) {
  //               System.out.println(s);
  //           }
	}
}

//ID:F2 7A 4E 43 >> changed to F2 7A 4E 43
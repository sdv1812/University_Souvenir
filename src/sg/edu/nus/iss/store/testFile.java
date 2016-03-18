import java.io.BufferedReader;
import java.io.FileReader;

public class testFile {
	
	private static String memberID="F3424235";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Reading File from Java code");
		//checkMember(memberID);
		testFile obj = new testFile();
		obj.checkMember(memberID);
		//String fileName =
	}
		
	public String checkMember(String memberID) {
		
		String msg = null;
		
		try{
			System.out.println("enter");
			String fileName = "Data/Members.dat";
			FileReader inputFile = new FileReader(fileName);
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(inputFile); 
			String line;
			while ((line = bufferReader.readLine()) != null)   {
				String[] memberString = line.split(",");
				System.out.println("if loop");
				System.out.println(memberString[1]);
				if (memberString[1] .equals(memberID) && !( memberString[2] .equals("-1")) ){
					System.out.println("member exists, but it's Not first Transaction");
					msg = "MemberExists";
				}else if ( memberString[1] == memberID && memberString[2] == "-1"  ){
					//return "FirstTransaction";
					return "FirstTransaction";
				}else {
					//return "NonMember";
					msg = "NonMember";
				}
				//if memberID="F3424235"; {
				/*System.out.println(memberString);
				System.out.println(memberString[0]);
				System.out.println(memberString[1]);
				System.out.println(memberString[2]);
				*/
				//}
	            //System.out.println(line);
			}
			bufferReader.close();
		}catch(Exception e) {
			System.out.println("Error while reading file line by line: " + e.getMessage());
		}
		return msg;
	}
}
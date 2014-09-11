
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.io.*;

public class Scanner14 {
	List<String> output = new ArrayList<String>();
	List<String> reserved = new ArrayList<String>();
	String LParen = "LParen";
	String RParen = "RParen";
	String Comma = "Comma";
	String SemiColon = "SemiColon";
	String AssignOp = "AssignOp";
	String MinusOp = "MinusOp";
	String PlusOp = "PlusOp";
	int counts = 0;
	
	
	public Scanner14() {
		reserved.add("BEGIN");
		reserved.add("READ");
		reserved.add("WRITE");
		reserved.add("END");
	}
	public List<String> generateTokens(File testFile) {
		try{
			BufferedReader input = new BufferedReader(new FileReader(testFile));
			try {
				String line = null;
				while (( line = input.readLine()) != null){
					StringTokenizer st = new StringTokenizer(line);
					while (st.hasMoreElements()) {
						StringBuilder sb = new StringBuilder();
						String temp = st.nextElement().toString();
						String word = "";
						if(temp.matches("[a-zA-Z]+")) {
							for(String str: reserved) {
								if(temp.equals(str)) {
									output.add(temp);
								}
							}
						}
						else {
							if(temp.startsWith("--")){
								continue;
							}
							else {
								for(int i=0; i<temp.length(); i++){
									char check = temp.charAt(i);
									if(Character.isLetter(check)) {
										word = word + check;
									}
									else if(check == '_') word = word + check;
									else if(Character.isDigit(check)) word = word + check;
									else {
										
										output.add(word); word = "";
										if(check == '(') output.add(LParen);
										if(check == ')') output.add(RParen);
										if(check == ',') output.add(Comma);
										if(check == ';') {output.add(SemiColon); ++counts;}
										if(check == ':' && temp.charAt(i+1) == '=') output.add(AssignOp);
										if(check == '-') output.add(MinusOp);
										if(check == '+') output.add(PlusOp);
										
									}
								}
							}
							
						}				
					}
				}
			} catch(IOException ie) {
				ie.printStackTrace();
			} finally {
				input.close();
			} 
		} catch(IOException ie) {
			ie.printStackTrace();
		}
		return output;
	}
	
	public void displayTokens(List<String> output) {
		//System.out.println("******* Generated Tokens *******");
		List<String> list = new ArrayList<String>();
		for(String st: output) {
			if(st.length() >0) list.add(st);
			
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Fall 2014\\Compiler - Boris\\Programs\\output.txt")); 
			writer.flush();
			//writer.write("Generated Tokens are as below:"); writer.newLine();
			for(String str: list) {
			  writer.write(str);
			  writer.newLine();
			}
			writer.close();
			//System.out.println("Done!!!");
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public int getCounts() {
		return counts;
	}
	
	/*public static void main(String[] args){
		File testFile = new File("D:\\Fall 2014\\Compiler - Boris\\Programs\\input.txt");
		File testFile1 = new File("D:\\Fall 2014\\Compiler - Boris\\Programs\\input1.txt");
		Scanner14 scanner = new Scanner14();
		List<String> output = scanner.generateTokens(testFile1); // ********************* PASS testFile1 instead testFile ****************
		scanner.displayTokens(output);
	}*/
}

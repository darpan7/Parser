
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import java.io.*;


public class Parser141 {
	List<String> final_output = new ArrayList<String>();
	List<String> store_tokens = new ArrayList<String>();
	int index = 0;
	int counts = 0;
	String sysGoal = "";
	String Lparen = ""; String Rparen = ""; String Stm = ""; String StmList = ""; String Read = ""; String Begin = ""; String End = "";
	String Id = ""; String Semicolon = ""; String Write = ""; String before = "";
	
	
	public Parser141() {	
	}
	public void parseTokens(File output_file, int counts) {
		try {
			this.counts = counts;
			BufferedReader input = new BufferedReader(new FileReader(output_file));
			try {
				String line = null;
				while(( line = input.readLine()) != null) {
					store_tokens.add(line);
				}
			} catch(IOException ie) {
				ie.printStackTrace();
			} finally {
				input.close();
			}
		} catch(IOException ie) {
			ie.printStackTrace();
		}
		Proc_SystemGoal();
		Proc_Program(index, counts);
	}
	
	public void Proc_SystemGoal() {
		sysGoal = "<program>$";
		System.out.println("SystemGoal");
		System.out.println("--> " + sysGoal);
		final_output.add("<program>$");
		final_output.add("\n");
	}
	public void Proc_Program(int index, int counts) {
		int id_1 = matchBegin(index, counts);
		int id_2 = CallStatementLists(id_1, counts);
		int id_3 = matchEnd(id_2);	
		//System.out.println("Coming ..."+ store_tokens.size());
	}
	public int matchBegin(int index, int counts) {
		String str = store_tokens.get(index);
		if(str.equals("BEGIN")) {
			Begin = "begin";
			StmList = "<stmList>";
			End = "end$";
			final_output.add("begin");
			for(int i=0; i<counts; i++) {
				final_output.add("<stmList>");
				Stm = Stm + "<statement>";
			}
			System.out.println("--> "+ Begin  +" " + StmList +" "+ End);
			System.out.println("--> "+ Begin +" " + Stm + " " + End);
			before = Begin;
			final_output.add("$");
			final_output.add("\n");
			//--counts;
			int i = index + 1; this.index = this.index + 1;
			return i;
		}
		else return 0;
	}
	public int CallStatementLists(int id_1, int counts) {
		this.counts = counts;
		for(int i=0; i<counts; i++) {
			 CallStatement(id_1, counts);	 
		}
		return this.index;
	}
	public void CallStatement(int id_1, int counts) {
		id_1 = this.index;
		String st = store_tokens.get(id_1);
		if(st.equals("READ")) {
			//System.out.println("Inside Read Token....!!!");
			final_output.add("begin read");
			Read = "read";
			id_1 = this.index;
			++id_1; this.index++;
			if(store_tokens.get(id_1).equals("LParen")) {
				final_output.add("(");
				Lparen = "(";
				++id_1; this.index++;
				int id_temp = Idlists(id_1); this.index = id_temp;
				Rparen = ")";
				++id_temp; ++this.index;
				if(store_tokens.get(id_temp).equals("SemiColon")) {
					final_output.add(";");
					Semicolon = ";";
					id_1 = this.index;
					++id_1;
					++this.index;
					counts = this.counts; Stm = "";
					for(int i=0; i<(counts-1); i++) {
						final_output.add("<stmList>");
						Stm = Stm + "<Statement>";
					}
					--this.counts;
					System.out.println("--> "+ Begin +" "+ Read +" "+ Lparen +" "+ Id +" "+ Rparen +" "+ Semicolon +" "+ Stm +" "+ End);
					final_output.add("$");
					final_output.add("\n");
				} 
				else {
					System.out.println("Error occurred during parsing Semicolon inside Read");
				}
			}
			else {
				System.out.println("Error occurred during parsing READ");
			}
		}
		else if(st.equals("WRITE")) {
			//System.out.println("Inside Write token....!!!");
			id_1 = this.index;
			final_output.add("write");
			Write = "write";
			++id_1; ++this.index;
			if(store_tokens.get(id_1).equals("LParen")) {
				final_output.add("(");
				Lparen = "(";
				++id_1; this.index++;
				int id_temp = Exprsnlists(id_1); this.index = id_temp;
				Rparen = ")";
				++id_temp; ++this.index;
				if(store_tokens.get(id_temp).equals("SemiColon")) {
					final_output.add(";");
					Semicolon = ";";
					id_1 = this.index;
					++id_1;
					++this.index;
					counts = this.counts; Stm = "";
					for(int i=0; i<(counts-1); i++) {
						final_output.add("<stmList>");
						Stm = Stm + "<Statement>";
					}
					--this.counts;
					System.out.println("--> "+ Begin +" "+ Write +" "+ Lparen +" "+ Id +" "+ Rparen +" "+ Semicolon +" "+ Stm +" "+ End);
					final_output.add("$");
					final_output.add("\n");
				} 
				else {
					System.out.println("Error occurred during parsing Semicolon inside write");
				}
			}
			else {
				System.out.println("Error occurred during parsing WRITE");
			}
		}
		else if(!st.equals("WRITE") && !st.equals("READ") && !st.equals("END") && st.matches("[a-zA-Z0-9_]+")) {
			//System.out.println(" Inside statement....!!!!" + st);
			counts = this.counts; Stm = "";
			for(int i=0; i<(counts-1); i++) {
				final_output.add("<stmList>");
				Stm = Stm + "<Statement>";
			}
			System.out.println("--> " + before + " Id :=" + " <expression> " + Stm + End);
			id_1 = this.index;
			Id = "";
			final_output.add("Id");
			Id = Id + "Id";
			++id_1; ++this.index;
			if(store_tokens.get(id_1).equals("AssignOp")) {
				final_output.add(":=");
				Id = Id + ":=";
				++id_1; ++this.index;
				while(!store_tokens.get(id_1).equals("SemiColon")) {
					if(store_tokens.get(id_1).equals("PlusOp") || store_tokens.get(id_1).equals("MinusOp")) {
						final_output.add(store_tokens.get(id_1));
						Id = Id + store_tokens.get(id_1);
						before = before + "Id :=";
						System.out.println("-->" + before + "Id :=<primary> <addop> <expression>" + Stm + End );
						++this.index;
					}
					else if(!store_tokens.get(id_1).equals("LParen") && !store_tokens.get(id_1).equals("RParen") && !store_tokens.get(id_1).matches("[0-9]+") && store_tokens.get(id_1).matches("[a-zA-Z0-9_]+")) {
						final_output.add("Id");
						Id = Id + "Id";
						++this.index;
					}
					else if(store_tokens.get(id_1).matches("[0-9]+")) {
						final_output.add("IntLiteral");
						Id = Id + "IntLiteral";
						++this.index;
					}
					else if(store_tokens.get(id_1).equals("LParen")) {
						final_output.add("(");
						Id = Id + "(";
						++this.index;
					}
					else if(store_tokens.get(id_1).equals("RParen")) {
						final_output.add(")");
						Id = Id + ")";
						++this.index;
					}
					else System.out.println("Error occurred during parsing semicolon while loop");
					++id_1;
				}
				final_output.add(";");
				Semicolon = ";";
				++id_1; ++this.index;
				counts = this.counts; Stm = "";
				for(int i=0; i<(counts-1); i++) {
					final_output.add("<stmList>");
					Stm = Stm + "<Statement>";
				}
				--this.counts;
				System.out.println("--> "+ Begin +" "+ Id + " " + Semicolon +" "+ Stm +" "+ End);
				final_output.add("$");
				final_output.add("\n");
			}
			else System.out.println("Error occurred during parsing AssignOp :" + store_tokens.get(id_1) + " at - "+id_1);
			
		}
		else {
			System.out.println("Error occurred during parsing No TOKEN MATCHED!!!");
		}
	}
	public int Idlists(int id_1) {
		List<String> _sublist = store_tokens.subList(id_1, store_tokens.size());
		int _index = _sublist.indexOf("RParen");
		_index = id_1 + _index;
		Id = "";
		for(int i = id_1; i<_index; i++) {
			if(store_tokens.get(i).equals("Comma")) {
				final_output.add(",");
				Id = Id + ",";
			}
			else if (store_tokens.get(i).matches("[a-zA-Z0-9_]+")) {
				final_output.add("Id");
				Id = Id + "Id";
			}
			else System.out.println("Error occurred during parsing Idlists");
		}
		final_output.add(")");
		return _index;
	}
	public int Exprsnlists(int id_1) {
		List<String> _sublist = store_tokens.subList(id_1, store_tokens.size());
		int _index = _sublist.indexOf("RParen");
		_index = id_1 + _index;
		Id = "";
		for(int i = id_1; i<_index; i++) {
			if(store_tokens.get(i).equals("LParen")) {
				final_output.add("(");
				Id = Id + "(";
			}
			else if(store_tokens.get(i).equals("RParen")) {
				final_output.add(")");
				Id = Id + ")";
			}
			else if(store_tokens.get(i).equals("Comma")) {
				final_output.add(",");
				Id = Id + ",";
			}
			else if(store_tokens.get(i).equals("PlusOp") || store_tokens.get(i).equals("MinusOp")) {
				final_output.add(store_tokens.get(i));
				Id = Id + store_tokens.get(i);
			}
			else if (!store_tokens.get(i).matches("[0-9]+") && store_tokens.get(i).matches("[a-zA-Z0-9_]+")) {
				final_output.add("Id");
				Id = Id + "Id";
			}
			else if(store_tokens.get(i).matches("[0-9]+")) {
				final_output.add("IntLiteral");
				Id = Id + "IntLiteral";
			}
			else System.out.println("Error occurred during parsing  In ExceptionList");
		}
		final_output.add(")");
		return _index;
		
	}
	
	public int matchEnd(int id_2) {
		String str = store_tokens.get(id_2);
		if(str.equals("END")) {
			//System.out.println("Inside End token....!!!");
			final_output.add("end");
			final_output.add("$");
			final_output.add("\n");
			//--counts;
			//int i = index + 1; this.index = this.index + 1;
			return id_2;
		}
		else return 0;
	}
	public static void main(String[] args) {
		Parser141 parser = new Parser141();
		File testFile = new File("D:\\Fall 2014\\Compiler - Boris\\Programs\\input2.txt");
		File testFile1 = new File("D:\\Fall 2014\\Compiler - Boris\\Programs\\input.txt");
		Scanner14 scanner = new Scanner14();
		List<String> output = scanner.generateTokens(testFile);
		scanner.displayTokens(output);
		File output_file = new File("D:\\Fall 2014\\Compiler - Boris\\Programs\\output.txt");
		parser.parseTokens(output_file, scanner.getCounts());
		System.out.println("!!!........... Parsed...........!!!");
	}
}

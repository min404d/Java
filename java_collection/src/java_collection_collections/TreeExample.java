package java_collection_collections;

import java.util.Set;
import java.util.TreeSet;


public class TreeExample {
 public static void main(String[] args) {
	 Set<TreeImp> program =new TreeSet<>();
	
	 program.add(new TreeImp("1","Java"));
	 program.add(new TreeImp("2","Python"));
	 program.add(new TreeImp("3","C ++ "));
	 program.add(new TreeImp("3","C ++"));
	 
	 System.out.println("Student sorted by name :");
	 for(TreeImp s:program) {
		 System.out.println(s);
	 }
	 boolean exists = program.contains(new TreeImp("2", "Python"));
     System.out.println("Python ! " + exists);

   
     program.remove(new TreeImp("2", "Python"));
     
     System.out.println("Removed:");
	 for(TreeImp s:program) {
		 System.out.println(s);
	 }
	}
 
 }


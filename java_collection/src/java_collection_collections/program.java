package java_collection_collections;

public class program {

	String id;
    String program;
   
    program(String program){
    	this.program=program;
    }
    program(String id, String name, int age) {
        this.id = id;
        this.program = program ;
       
    }

    public String toString() {
        return "[" + id + "] " + program ;
    }
}

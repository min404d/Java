package java_collection_collections;

import java.util.Objects;

public class TreeImp  implements Comparable<TreeImp> {
    String id;
    String program;

   public  TreeImp(String id, String program) {
        this.id = id;
        this.program = program;
    }

  
	@Override
    public int compareTo(TreeImp other) {
        return this.program.compareToIgnoreCase(other.program);
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof program)) return false;
        program s = (program) o;
        return id.equals(s.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + program;
    }
}

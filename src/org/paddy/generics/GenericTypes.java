package org.paddy.generics;
import java.util.List;
/**
 E - Element (used extensively by the Java Collections Framework)K - Key
 N - Number
 T - Type
 V - Value
 S,U,V etc. - 2nd, 3rd, 4th types
*/
public class GenericTypes<N, T> {
    private List<N> nList;
    private T type;
    public void setN(List<N> nList) {
        this.nList = nList;
    }
    public List<N> getN() { return nList; }
    public void setT(T type) {
        this.type = type;
    }
    public T getT() { return type; }
}

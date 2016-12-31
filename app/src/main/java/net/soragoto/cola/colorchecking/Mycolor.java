package net.soragoto.cola.colorchecking;

/**
 * Created by cola on 2016/12/30.
 */

public class Mycolor {
    public String name;
    public int r, g, b;

    Mycolor( String name, int r, int g, int b){
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void set( String name, int r, int g, int b){
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
    }
}

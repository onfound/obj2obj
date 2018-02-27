import java.io.*;

public class Launcher {

    public static void main(String[] args) {
        Obj2obj obj2obj = new Obj2obj("test1.obj");
        obj2obj.addFirstObj("src/Head.obj");
        obj2obj.addSecondObj("src/KaskaEbanaya.obj");
    }
}

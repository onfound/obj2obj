public class Launcher {

    public static void main(String[] args) {

//        Util.downsize(3, "Igor.obj");
//        Util.offset(28.0f, 65.0f, -0.65f, "IgorTemp.obj");
//        Util.rotate(1.0f,-1.0f,1.0f,"IgorTemp1.obj");
//        Util.addObj2Obj("Igor1.obj","completeVersion.obj");



        final ModelObj object = new ModelObj("res/face1.obj");
        final ModelObj object1 = new ModelObj("res/face2.obj");
        final ModelObj result = Util.addObj2Obj(object, object1);
    }
}

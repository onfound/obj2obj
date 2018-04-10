import java.util.List;

public class ModelObj {
    int countV;
    int countVt;
    int countVn;
    List<Face> faces;

    ModelObj(String path) {

    }

    private class Face{

        Face(Vertex v1, Vertex v2, Vertex v3){

        }
    }

    private class Vertex{
        float x;
        float y;
        float z;
    }
}

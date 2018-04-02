package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Obj2obj {
    private long countV = 0;
    private long countVn = 0;
    private long countVt = 0;
    private String fileName;
    private List<Float> faceCoords;

    Obj2obj(String fileName) {
        this.fileName = fileName;
        faceCoords = new ArrayList<>();
    }

    public void addObj2Obj(String source, String target) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);

            BufferedReader readerSource = new BufferedReader(new FileReader(new File(source)));
            String lineSource = readerSource.readLine();
            fileWriter.write("#----------FirstObjV---------------\n");
            while (lineSource != null) {
                String[] strings = lineSource.split(" ");
                switch (strings[0]) {
                    case "v":
                        fileWriter.write(lineSource + "\n");
                        faceCoords.add(Float.valueOf(strings[1]));
                        countV++;
                        break;
                    case "vt":
                        fileWriter.write(lineSource + "\n");
                        countVt++;
                        break;
                    case "vn":
                        fileWriter.write(lineSource + "\n");
                        countVn++;
                        break;
                }
                if (strings[0].equals("f")) break;
                lineSource = readerSource.readLine();
            }
            BufferedReader readerTarget = new BufferedReader(new FileReader(new File(target)));
            String lineTarget = readerTarget.readLine();
            fileWriter.write("#----------SecondObjV/F---------------\n");
            while (lineTarget != null) {
                String[] strings = lineTarget.split(" ");
                StringBuilder sb = new StringBuilder();
                if (strings[0].equals("f")) {
                    sb.append("f ");
                    for (int i = 1; i < strings.length; i++) {
                        String[] values = strings[i].split("/");
                        sb.append(Integer.valueOf(values[0]) + countV);
                        if (values.length != 1) {
                            sb.append("/");
                            sb.append(Integer.valueOf(values[1]) + countVt);
                            if (values.length != 2) {
                                sb.append("/");
                                sb.append(Integer.valueOf(values[2]) + countVn);
                            }
                        }
                        sb.append(" ");
                    }
                    sb.append("\n");
                    fileWriter.write(sb.toString());
                } else fileWriter.write(lineTarget + '\n');
                lineTarget = readerTarget.readLine();
            }
            fileWriter.write("#----------FirstObjF---------------\n");
            while (lineSource != null) {
                fileWriter.write(lineSource + "\n");
                lineSource = readerSource.readLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void downsize(int k, String obj) {
        try {
            File fileFirstObj = new File(obj);
            FileReader fr = new FileReader(fileFirstObj);
            BufferedReader reader = new BufferedReader(fr);
            FileWriter fileWriter = new FileWriter("IgorTemp.obj");
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                if (strings[0].equals("v")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("v ");
                    for (int i = 1; i < strings.length; i++) {
                        if (!strings[i].isEmpty()) {
                            Float newFloat = Float.valueOf(strings[i]) / k;
                            sb.append(newFloat);
                            sb.append(" ");
                        }
                    }
                    fileWriter.write(sb.toString() + "\n");
                } else fileWriter.write(line + "\n");
                line = reader.readLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void offset(float x, float y, float z, String obj) {
        float[] coords = new float[]{x, y, z};
        try {
            File fileFirstObj = new File(obj);
            FileReader fr = new FileReader(fileFirstObj);
            BufferedReader reader = new BufferedReader(fr);
            FileWriter fileWriter = new FileWriter("IgorTemp1.obj");
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                if (strings[0].equals("v")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("v ");
                    for (int i = 1; i < 4; i++) {
                        if (!strings[i].isEmpty()) {
                            Float newFloat = Float.valueOf(strings[i]) - coords[i - 1];
                            sb.append(newFloat);
                            sb.append(" ");
                        }
                    }
                    fileWriter.write(sb.toString() + "\n");
                } else fileWriter.write(line + "\n");
                line = reader.readLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void rotate(float x, float y, float z, String obj) {
        float[] coords = new float[]{x, y, z};
        try {
            File fileFirstObj = new File(obj);
            FileReader fr = new FileReader(fileFirstObj);
            BufferedReader reader = new BufferedReader(fr);
            FileWriter fileWriter = new FileWriter("Igor1.obj");
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                if (strings[0].equals("v")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("v ");
//                    float newFloat = Float.valueOf(strings[i])
                    for (int i = 1; i < 4; i++) {
                        if (!strings[i].isEmpty()) {
                            Float newFloat = Float.valueOf(strings[i]) * coords[i - 1];
                            sb.append(newFloat);
                            sb.append(" ");
                        }
                    }
                    fileWriter.write(sb.toString() + "\n");
                } else fileWriter.write(line + "\n");
                line = reader.readLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

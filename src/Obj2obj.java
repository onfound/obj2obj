import java.io.*;

class Obj2obj {
    private long countV=0;
    private String fileName;
    private String fileName1 = "test2.obj";
    private int max = 0;
    Obj2obj(String fileName) {
        this.fileName = fileName;
    }

    protected void addFirstObj(final String path) {
        try {
            File fileObj = new File(path);
            FileReader fr = new FileReader(fileObj);
            FileWriter fileWriter = new FileWriter(fileName);

            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                if (strings[0].equals("v")){
                    countV++;
                    fileWriter.write(line + '\n');
                }
                StringBuilder sb = new StringBuilder();
                if (strings[0].equals("f")){
                    sb.append("f ");
                    for (int i = 1; i < strings.length ; i++) {
                        String[] values = strings[i].split("/");
                        sb.append(values[0]);
                        sb.append(" ");
                    }
                    sb.append("\n");
                    fileWriter.write(sb.toString());
                }
                line = reader.readLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void addSecondObj(final String path) {
        int a = 0;
        try {
            File fileFirstObj = new File(path);
            FileReader fr = new FileReader(fileFirstObj);
            FileWriter fileWriter = new FileWriter(fileName1);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                if (strings[0].equals("v")){
                    fileWriter.write(line + '\n');
                }
                StringBuilder sb = new StringBuilder();
                if (strings[0].equals("f")){
                    sb.append("f ");
                    for (int i = 1; i < strings.length ; i++) {
                        String[] values = strings[i].split("/");
                        System.out.println(countV);
                        a = (int) (Integer.valueOf(values[0]) + countV);
                        if (a>max) max = a;
                        sb.append(Integer.valueOf(values[0])+countV);
                        sb.append(" ");
                    }
                    sb.append("\n");
                    fileWriter.write(sb.toString());
                }
                line = reader.readLine();
            }
            fileWriter.close();
            System.out.println("max = " + max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

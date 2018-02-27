import java.io.*;

class Obj2obj {
    private long countV=1;
    private String fileName;
    private String fileName1 = "test2.obj";
    private File firstFile;
    private File secondFile;

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
                if (strings[0].equals("f")){
                    fileWriter.write(line + '\n');
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void addSecondObj(final String path) {
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

                        sb.append(Integer.valueOf(values[0])+countV);
                        sb.append(" ");
                    }
                    sb.append("\n");
                    fileWriter.write(sb.toString());
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

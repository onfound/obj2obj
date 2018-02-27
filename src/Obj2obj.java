import java.io.*;

class Obj2obj {
    private long countV = 0;
    private long countVn = 0;
    private long countVt = 0;
    private String fileName;

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
                if (strings[0].equals("v")) {
                    if (strings[1].equals("t")) {
                        countVt++;
                    } else if (strings[1].equals("n")) {
                        countVn++;
                    } else countV++;

                }
                fileWriter.write(line + '\n');

                line = reader.readLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void addSecondObj(final String path) {
        try {
            File fileFirstObj = new File(path);
            FileReader fr = new FileReader(fileFirstObj);
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(" ");
                if (strings[0].equals("v")) {
                    fileWriter.write(line + '\n');
                }
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
                }
                line = reader.readLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Consolidator {

    public static void main(String[] args) throws Exception {
        String path = null;
        if (args.length > 0) {
            path = args[0];
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (path == null) {
                System.out.println("Enter path");
                path = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        path = path.replace("\\", "/");
        List<String> files = FileUtils.getAllFiles(path);
        StringBuilder ddlContent = new StringBuilder();
        StringBuilder dmlContent = new StringBuilder();
        StringBuilder errorContent = new StringBuilder();
        String extension;
        ArrayList<String> ddl = new ArrayList<>();
        ArrayList<String> dml = new ArrayList<>();
        ArrayList<String> error = new ArrayList<>();
        for (String filename : files) {
            int i = filename.lastIndexOf('.');
            if (i > 0) {
                extension = filename.substring(i + 1);
                if (!"sql".equalsIgnoreCase(extension))
                    continue;
                if ("driver.sql".equals(filename))
                    continue;
                br = new BufferedReader(new FileReader(filename));
                if (filename.contains("ddl")) {
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null)
                        ddl.add(sCurrentLine);
                    continue;
                }
                if (filename.contains("dml")) {
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
                        dml.add(sCurrentLine);
                    }
                    continue;
                }
                error.add(filename);
            }
        }
        String NEWLINE = "\r\n";
        for (String line : ddl) {
            ddlContent.append(NEWLINE).append(line);
        }
        for (String line : dml) {
            dmlContent.append(NEWLINE).append(line);
        }
        for (String line : error) {
            errorContent.append(NEWLINE).append(line);
        }
        try {
            System.out.println(ddlContent);
            PrintWriter writer = new PrintWriter(path + "/ddl.sql", "UTF-8");
            writer.print(ddlContent);
            writer.close();

            writer = new PrintWriter(path + "/dml.sql", "UTF-8");
            writer.print(dmlContent);
            writer.close();

            if (!error.isEmpty()) {
                writer = new PrintWriter(path + "/ERROR.txt", "UTF-8");
                writer.print(errorContent);
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

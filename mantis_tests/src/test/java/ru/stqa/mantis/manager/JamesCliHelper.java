package ru.stqa.mantis.manager;

import java.io.File;

public class JamesCliHelper extends HelperBase{
    public JamesCliHelper (ApplicationManager manager){
        super(manager);
    }
    public void addUser(String email, String password) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "-cp",
                    "james-server-jpa-app.lib/*",
                    "org.apache.james.cli.ServerCmd",
                    "AddUser",
                    email,
                    password
            );
            pb.directory(new File(manager.property("james.workingDir")));
            pb.redirectErrorStream(true);

            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                throw new RuntimeException("AddUser failed. Exit code: " + exitCode);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error while executing AddUser CLI", e);
        }
    }

}

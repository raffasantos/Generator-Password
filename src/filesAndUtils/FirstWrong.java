package filesAndUtils;

import java.io.*;
import java.util.HashMap;

public class FirstWrong {
    String desktop = "C:\\Users\\brpla\\OneDrive\\Área de Trabalho";
    FileReader reader = new FileReader(desktop + "\\FilePasswords/passwords.svc");
    FileWriter writer;

    public FirstWrong() throws FileNotFoundException {
        boolean existsFile = (new File(desktop + "FilePasswords\\password.svc").exists());

        if(existsFile) {
        } else {
            File file = new File(desktop + "\\FilePasswords");
            file.mkdir();
            reader = new FileReader(desktop + "\\FilePasswords/passwords.svc");

        }
        }

    public void randomPassword(Integer idSenha){
        HashMap map = new HashMap<Integer, String>();
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .useEspecial(false)
                .build();

        try (BufferedReader br = new BufferedReader(reader)) {
            writer = new FileWriter(desktop + "\\FilePasswords/passwords.svc");

            BufferedWriter bw = new BufferedWriter(writer);

            String line = br.readLine();
            while (line != null) {

                String[] fields = line.split(",");

                Integer id = Integer.parseInt(fields[0]);
                String password = fields[1];

                if(map.get(id) == null){
                    map.put(id, password);
                }

                line = br.readLine();
            }

            if(line == null && map.containsKey(idSenha) == true){
                System.out.println("O Id: + "+ idSenha + " já existe: " + map.get(idSenha));
                String newPassword = passwordGenerator.generate(4);
                map.put(map.size() + 1, newPassword);
                bw.write((idSenha) + "," + newPassword);
                bw.flush();
            } else {
                String newPassword = passwordGenerator.generate(4);
                map.put(map.size() + 1, newPassword);
                bw.write((idSenha) + "," + newPassword);
                bw.flush();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}

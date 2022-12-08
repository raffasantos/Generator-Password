package filesAndUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generator {
    File file = new File("C:\\Users\\brpla\\OneDrive\\Área de Trabalho\\FilePasswords/passwords.txt");

    List<String> list = new ArrayList<>();
    public String generateRandom(Integer sizePassword){
        if(file.exists()){
        } else {
            System.out.println("Não existe!");
            File filePath = new File("C:\\Users\\brpla\\OneDrive\\Área de Trabalho\\FilePasswords");
            filePath.mkdir();
        }

        String newPassword = generate(sizePassword);

        try {
            FileReader fl = new FileReader(file);
            BufferedReader br = new BufferedReader(fl);

            String line = br.readLine();
            while(line != null){
                String[] lineSplit = line.split(",");
                list = Arrays.asList(lineSplit);

                line = br.readLine();
            }
            br.close();
            fl.close();
        } catch (IOException e) {
            System.out.println("Error: "+ e.getMessage());
        }


        if(list.indexOf(newPassword) >= 0){
        } else {
            try {
                FileWriter writer = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(writer);
                bw.write(newPassword + ",");

                bw.close();
                writer.close();
                bw.close();
                return "Nova senha: " + newPassword;
            } catch (IOException e) {
                System.out.println("Error: "+ e.getMessage());
            }
        }
        return "Senha já existe!";
    }

    public void passwordList(){
        try {
            FileReader fl = new FileReader(file);
            BufferedReader br = new BufferedReader(fl);

            String line = br.readLine();
            while(line != null){
                String[] lineSplit = line.split(",");
                list = Arrays.asList(lineSplit);

                line = br.readLine();
            }

            br.close();
            fl.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i < list.size(); i++){
            System.out.println("Senha " + i + ": " + list.get(i));
        }
    }

    PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
            .useLower(true)
            .useEspecial(true)
            .useUpper(true)
            .useDigits(true)
            .build();

    public String generate(Integer sizePassword){
        return passwordGenerator.generate(sizePassword);
    }
}

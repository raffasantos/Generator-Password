package front;

import filesAndUtils.Generator;

import java.util.Scanner;

public class Menu {
    Scanner sc;
    Generator generator = new Generator();
    private int exists=9;

    public void main(){
        sc = new Scanner(System.in);

        do {
            System.out.println("Bem vindo ao gerador de senhas aleatórias!");
            System.out.println();
            System.out.println("Digite:");
            System.out.println("1 - Para ver todas as senhas já criadas.");
            System.out.println("2 - Para gerar uma nova senha.");
            System.out.println("-- digite 0 para sair do sistema --");
            exists = sc.nextInt();

            switch(exists){
                case 1:
                    passwordListMenu();
                break;
                case 2:
                    newPasswordMenu();
                break;
            }
        } while(exists != 0);
    }

    //Read
    public void passwordListMenu(){
        generator.passwordList();
    }

    //Create
    public void newPasswordMenu(){
        sc = new Scanner(System.in);

        do {
            System.out.println("-- digite 0 para voltar ao menu principal --");
            System.out.print("Informe o tamanho da senha: ");
            int passwordSize = sc.nextInt();

            if(passwordSize == 0) {
                System.out.println("Senha não pode ser igual ou menor a 0!");
                return;
            }

            System.out.println(generator.generateRandom(passwordSize));
            return;
        }while(exists != 0);
    }
}

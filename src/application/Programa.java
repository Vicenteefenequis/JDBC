package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import entities.Aluno;
import jdbc.AlunoJDBC;

public class Programa {

	public static void main(String[] args) {
		
        try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
                System.out.print("######## Menu ########"
                                 + "\n1- Cadastrar"
                                 + "\n2- Listar"
                                 + "\n3- Alterar"
                                 + "\n4- Excluir"
                                 + "\n5- Sair");
                System.out.print("\n\tOp??o: ");
                opcao = Integer.parseInt(console.nextLine());
                System.out.println("\n\n\n\n");
                
                if (opcao == 1){
                    
                    Aluno a = new Aluno();
                    AlunoJDBC acao = new AlunoJDBC();
                    
                    System.out.print("\n*** Cadastrar Aluno ***\n\r");
                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());
                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());
                    System.out.print("Data de nascimento: ");
                    a.setDt_nasc( new Date(console.nextLine()) );
                    
                    acao.salvar(a);
                    console.nextLine();
                    System.out.println("\n\n\n\n");
                }
                else if(opcao == 2) {
                	AlunoJDBC acao = new AlunoJDBC();
                	System.out.print("\n***** Listagem de Alunos ***\n\r");
                	List<Aluno> alunos = acao.listar();
                	for(Aluno aluno: alunos) {
                		System.out.println("Id :" + aluno.getId());
                		System.out.println("Nome :" + aluno.getNome());
                		System.out.println("Data de Nascimento :" + aluno.getDt_nasc());
                		System.out.println("Sexo :" + aluno.getSexo());
                		System.out.print("\t---------------------------\n\n");
                	}
                }
                else if(opcao == 3) {
                	Aluno aluno = new Aluno();
                	AlunoJDBC acao = new AlunoJDBC();
                	int selectionMenu = 0;
                  	System.out.print("\n***** Alteracao de Alunos ***\n\r");
                  	
                  	System.out.print("Digite o id do aluno que deseja alterar: ");
                  	aluno.setId(Integer.parseInt(console.nextLine())); 
                  	
            
                  	
                  	
                  	 System.out.print("\n1- Nome"
                             + "\n2- Sexo"
                             + "\n3- Data de nascimento\n"
                           );
                  	 System.out.print("\n*** Selecione os Campos que Deseja alterar: ");
                  	 selectionMenu = Integer.parseInt(console.nextLine());
                  	 
                  	 if(selectionMenu == 1) {
                  		 System.out.print("Digite o novo nome: ");
                  		 aluno.setNome(console.nextLine());
                  	 }else if(selectionMenu == 2) {
                  		 System.out.print("Digite o novo Sexo: ");
                  		 aluno.setSexo(console.nextLine());
                  	 }else {
                  		System.out.print("Digite a nova Data de nascimento: ");
                  		 aluno.setDt_nasc( new Date(console.nextLine()) );
                  	 }
                  	 
                  	 acao.alterar(aluno);
                  	             	
                }
                else if(opcao == 4) {
                	AlunoJDBC acao = new AlunoJDBC();
                	System.out.print("\n **** Deletar aluno por id ***** \n\r");
                	System.out.print("Digite o id do aluno que deseja apagar: ");
                	int id =  Integer.parseInt(console.nextLine());
                	acao.apagar(id);
                }
            } while(opcao != 5);
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
	}
}

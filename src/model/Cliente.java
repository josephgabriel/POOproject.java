package model;
import java.util.Scanner;

public class Cliente {
    private int id[]; // isso não é so um vetor, tenho de arrumar depois para ser um sistema dinamico com capacidade flexivel de criação de contas
    private String nome;
    private String email;
    private String endereco;
    private int telefone;
    private boolean situacao;
    
   public void clienteCadastrar(){
        Scanner sc = new Scanner(System.in);
       
        try{
            
        for(int i = 0; i < id.length; i++){
            if(id[i] == null) //corrigir esse boga dps, é pra analisar os clientes com id zerado
                id[i] = i;
        }
        
        System.out.println("------CADASTRO------\n");
        
        System.out.println("Nome Completo: ");
        nome = sc.nextLine();
        
        System.out.println("Email: ");
        email = sc.nextLine();
        
        System.out.println("Endereço: ");
        endereco = sc.nextLine();
        
        
        System.out.print("Telefone (Como sequência numerica): ");
        telefone = sc.nextInt();
        
        situacao = true;
         
        }
        catch(Exception error){
            System.out.println("Erro no cadastro!" + error.toString());
        }
        sc.close();
    }
   
   public void clienteDesativar(){
       if(this.situacao == true){
           this.situacao = false;
           System.out.println("Conta desativada com sucesso!");
       }
       else
           System.out.println("Não é possível desativar uma conta já inativa");
   }
   
   public void clienteReativar(){
        if(this.situacao == false){
           this.situacao = true;
           System.out.println("Conta reativada com sucesso!");
       }
       else
           System.out.println("Não é possível reativar uma conta já ativa");
   }
   
   public void clienteExibir(){
       System.out.println("Nome : " + this.nome);
       System.out.println("Email: " + this.email);
       
       System.out.println("Endereço: ");
       
       if(this.endereco != null)
       System.out.print(endereco);
       
       else
           System.out.print("*Vazio*");
       
       System.out.println("Telefone: ");
       
       if(this.telefone != null) //corrigir essa comparação dps
       System.out.print(telefone);
       
       else
           System.out.print("*Vazio*");
       
        if(this.situacao == true)
       System.out.println("Conta Ativa");
       
       else
           System.out.println("Conta Inativa");
   }
   
   public void clienteListar(){
       for(int i = 0; i < id.length; i++)
        clienteExibir();    
   }
   
   public void clienteAtualizar(){
       //deve ter condição se checar que o cliente escolhido existe
       int alteracao;
       Scanner sc = new Scanner (System.in);
       
       try{
       
       System.out.println("Selecione uma opção: \n1- Alterar Nome\n2-Alterar Email\n3-Alterar Endereço\n4-Alterar Telefone\n5- Ativar/desativar Conta");
       
       alteracao = sc.nextInt();
       
       switch(alteracao){
           case 1: 
               System.out.println("Informe o novo nome: ");
               this.nome = sc.nextLine();
               break;
           case 2:
               System.out.println("Informe o novo email: ");
               this.email = sc.nextLine();
               break;
           case 3:
               System.out.println("Informe o novo endereço: ");
               this.endereco = sc.nextLine();
               break;
           case 4:
               System.out.println("Informe o novo telefone (sequência numerica)");
               this.telefone = sc.nextInt();
               break;
           case 5:
               if(this.situacao == true)
                   clienteDesativar();
               else
                   clienteReativar();
               
               break;
           default: System.out.println("Opção inválida"); break; //corrigir para, enquanto uma opinião nao valida ser inserida, o programa repetir o switch
         }    
       }
       catch(Exception error){
           System.out.println("Erro!" + error.toString());
       }
       sc.close();
   }
   
   public void clienteDeletar(){
       System.out.println("Tem Certeza que deseja apagar o Cliente " + this.id + "? 1 - Sim  2 - Não");
       int opcao;
       Scanner sc = new Scanner(System.in);
       
       opcao = sc.nextInt();
       
       switch(opcao){
           case 1: 
               //falta adicionar um comando que atualize os id anteriores e posteriores
               this.nome = null;
               this.email = null;
               this.endereco = null;
               this.telefone = null;
               this.situacao = null;
               System.out.println("Usuário deletado com Sucesso!");
               break;
               
           case 2: 
               break;
               
           default: System.out.println("Opção inválida! Tente novamente!"); break; //novamente, arrumar o break
       }
   }
   
    //em andamento...
}

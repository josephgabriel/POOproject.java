package classes;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private boolean situacao;

    public Cliente(String nome, String email, String endereco, String telefone) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.situacao = true;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { //Achei que nao precisava, ja que o id(codigo) é auto icremento, mas dps vi que precisa
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Cliente ID: " + id + "\n" +
               "Nome: " + nome + "\n" +
               "Email: " + email + "\n" +
               "Endereço: " + endereco + "\n" +
               "Telefone: " + telefone + "\n" +
               "Situação: " + (situacao ? "Ativa" : "Inativa");
    }
}

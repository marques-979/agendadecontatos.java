package src.app;

// Classe que representa um contato da agenda
public class Contato {
    private String nome;
    private String telefone;
    private String email;
    private String aniversario;

    public Contato(String nome, String telefone, String email, String aniversario) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.aniversario = aniversario;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAniversario() { return aniversario; }
    public void setAniversario(String aniversario) { this.aniversario = aniversario; }
    public void exibirInfo() {
        // Saída: exibe os dados do contato no console
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Aniversário: " + aniversario);
    }
}

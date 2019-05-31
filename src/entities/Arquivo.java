package entities;

public class Arquivo {
    
    private String nome;
    private String url;
    private String[] arquivos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String[] getArquivos() {
        return arquivos;
    }

    public void setArquivos(String arquivos, int posicao) {
        this.arquivos[posicao] = arquivos;
    }
    
}
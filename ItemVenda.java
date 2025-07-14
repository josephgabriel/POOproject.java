import java.math.BigDecimal;


public class ItemVenda {
    private long id;
    private Venda venda;
    private Produto produto;
    private int quantidade;
    private BigDecimal subTotal = BigDecimal.ZERO;
    private BigDecimal valorUnitario = BigDecimal.ZERO;


    public ItemVenda(Venda venda, int quantidade, Produto produto) {
        this.venda = venda;
        this.quantidade = quantidade;
        this.produto = produto;
        this.valorUnitario = produto.getPreço();// aqui é para pegar o preço do produto que ainda não tem
        calcularSubTotal();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void calcularSubTotal() {
        this.subTotal = this.valorUnitario.multiply(new BigDecimal(this.quantidade));
        if(this.subTotal.compareTo(BigDecimal.ZERO) < 0){ // se o subTotal for menor que zero, ele retorna 0
            this.subTotal = BigDecimal.ZERO; //
        }
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;// falta verificar se a quantidade é menor que zero
        calcularSubTotal();
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valor) {
        this.valorUnitario = valor;
    }



    // ainda falta implementar os metodos
}

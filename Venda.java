package classes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private Cliente cliente;
    private Timestamp dataVenda;
    private List<ItemVenda> itens = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Timestamp getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Timestamp dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return itens.stream().mapToDouble(i -> i.getPrecoUnitario() * i.getQuantidade()).sum();
    }
}

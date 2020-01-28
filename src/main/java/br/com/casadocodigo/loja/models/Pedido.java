package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Pedido {

	private int id;
	private BigDecimal valor;
	private Calendar data;
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public Pedido() {
	}
	
	public Pedido(int id, BigDecimal valor, Calendar data, List<Produto> produtos) {
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.produtos = produtos;
	}
	
	public String getDataPedidoBonita() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		return sdf.format(data.getTime());
	}
	public String getTitulos() {
		if (produtos.isEmpty()) {
			return "Nenhum";
		} else if (produtos.size() == 1) {
			return produtos.get(0).getTitulo();
		} else {
			
			String titulos = "";
			Iterator<Produto> ite = produtos.iterator();
			
			while (ite.hasNext()) {
				titulos += ite.next().getTitulo();
				if (ite.hasNext()) {
					titulos += ", ";
				}
			}
			
			return titulos;
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	@Override
	public String toString() {
		return "[ Pedido Id: "+this.id+", valor: "+this.valor+", dataBonita: "+this.getDataPedidoBonita()+" ]";
	}
	
}

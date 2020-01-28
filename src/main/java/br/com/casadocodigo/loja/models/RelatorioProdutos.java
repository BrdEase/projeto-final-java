package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RelatorioProdutos {

	private Calendar dataDaGeracao;

	private int quantidadeProdutos;

	private List<Produto> listaDeProdutos = new ArrayList<Produto>();

	private RelatorioProdutos() {
		this.dataDaGeracao = Calendar.getInstance();
	}

	public static RelatorioProdutos gerar(List<Produto> listaDeProdutos, Calendar dataFiltro) {
		List<Produto> listaFiltrada = new ArrayList<Produto>();

		for (Produto produto : listaDeProdutos) {
			if (produto.getDataLancamento().after(dataFiltro)) {
				listaFiltrada.add(produto);
			}
		}

		RelatorioProdutos relatorio = new RelatorioProdutos();
		relatorio.setListaDeProdutos(listaFiltrada);
		relatorio.setQuantidadeProdutos(listaFiltrada.size());

		return relatorio;
	}

	public static RelatorioProdutos gerar(List<Produto> listaDeProdutos) {

		RelatorioProdutos relatorio = new RelatorioProdutos();
		relatorio.setListaDeProdutos(listaDeProdutos);
		relatorio.setQuantidadeProdutos(listaDeProdutos.size());
		
		return relatorio;
	}

	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	private void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	private void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public Calendar getDataDaGeracao() {
		return dataDaGeracao;
	}

}

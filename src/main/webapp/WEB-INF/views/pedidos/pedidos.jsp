<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Pedidos">

	<div class="container">
		<h1>Lista de Pedidos</h1>
		<!-- <p>${sucesso}</p>
		<p>${falha}</p> -->

		<table class="table table-bordered">
			<tr>
				<th>ID</th>
				<th>Valor</th>
				<th>Data Pedido</th>
				<th>Titulos</th>
			</tr>
			<c:forEach items="${pedidos }" var="pedido">
				<tr>
					<td>${pedido.id }</td>
					<td>${pedido.valor }</td>
					<td>${pedido.dataPedidoBonita }</td>
					<td>${pedido.titulos }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</tags:pageTemplate>
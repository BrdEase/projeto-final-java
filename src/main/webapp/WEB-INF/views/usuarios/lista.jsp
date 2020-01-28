<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<c:url value="/" var="contextPath" />

<tags:pageTemplate titulo="Usuários">

	<div class="container">
		<h2><a href="${s:mvcUrl('UC#form').build() }">Novo Usuario</a></h2>
	
		<h1>Lista de Usuários</h1>
		<p>${sucesso}</p>
		<!-- <p>${falha}</p> -->

		<table class="table table-bordered">
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Roles</th>
				<th></th>
			</tr>
			<c:forEach items="${usuarios }" var="usuario">
				<tr>
					<td>${usuario.nome }</td>
					<td>${usuario.email }</td>
					<td>${usuario.roleBonita }</td>
					<td><form:form action="${s:mvcUrl('UC#editarRoles').arg(0, usuario.email).build() }" method="POST">
								<input type="image" src="${contextPath }resources/imagens/editar.png" 
									alt="Editar Roles" />
							</form:form></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</tags:pageTemplate>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Cadastro de Permissões para ${usuario.nome }">
	<head>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
	</head>

	<div class="container">
		<h1>Cadastro de Permissões para ${usuario.nome }</h1>
		<form:form
			action="${s:mvcUrl('UC#gravarRoles').arg(0, usuario).arg(1, usuario.email).build() }"
			method="post" commandName="usuario">
			<h3>Permissões:</h3>

			<c:forEach items="${roles }" var="role">

				<div class="checkbox">
					<label> <form:checkbox cssClass="checkbox" path="roles" value="${role.nome}" /> ${role.nome}
					</label>
				</div>

			</c:forEach>

			<button type="submit" class="btn btn-primary">Atualizar</button>
		</form:form>
	</div>
</tags:pageTemplate>

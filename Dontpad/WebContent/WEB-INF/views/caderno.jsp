<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
 	<title>Caderno:</title>
</head>

<body>
	
  	
	Auto Save: <input type="checkbox" id="autosave"/>
	<jsp:useBean id="caderno" class="main.java.br.ufc.qx.wed.dontpad.model.Caderno"/>
	<p>Nome do caderno: <strong>${caderno.nome}</strong></p>
	<p>&Uacute;ltima atualiza&ccedil;&atilde;o: ${caderno.ultimaAtualizacao}</p>
	<form method="POST" action="" id="form">
		<input type="hidden" id="id" name="id" value="${caderno.id}"/>
		<c:if test="${caderno.dono != null}"> <!-- usario logado -->
			<p>Privado: <input type="checkbox" id="visibilidade" name="visibilidade" value="checked"/></p>
		</c:if>
		<c:if test="${caderno.dono == null}">
			<c:if test="${caderno.publico == true}">
				<p>Visibilidade: <img src="img/publico" alt="p&uacute;blico"/></p>
			</c:if>
			<c:if test="${caderno.publico == false}">
				<p>Visibilidade: <img src="img/privado" alt="privado"/></p>
			</c:if>
		</c:if>
		<textarea id="conteudo" name="conteudo" disabled>${caderno.conteudo}</textarea>
		<input type="submit" value="Salvar"/>
	</form>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="<c:url value="/static/js/caderno.js"/>"></script>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deletar Colaborador</title>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>

	<c:if test="${msg ne '' or msg ne null }">
		<label style="color: red;">${msg }</label>
	</c:if>
	<form action="Deletar" method="post">
		<table>

			<tr>

				<td>Nome: <input type="text" readonly="readonly" name="nome"
					value="${col.nome}" /><br /></td>

			</tr>

			<tr>
				<td>Data de Nascimento: 
					<fmt:formatDate var="fmtDateNascimento" value="${col.dataDeNascimento}" pattern="dd/MM/yyyy"/>
				 <input type="text"
					name="dataNascimento" value="${fmtDateNascimento}"> 
			</tr>

			<tr>

				<td>CPF: <input type="text" readonly="readonly" name="cpf"
					value="${col.cpf}"></td>

			</tr>

			<tr>
			<tr>
				<td>Cargo: <select name="cargo">
						<option selected value="">Selecione!</option>
						<c:forEach var="cargo" items="${listaCargos}">
							<option value="${cargo.id}">${cargo.descricao}</option>

						</c:forEach>
				</select> <input type="Submit" value='OK' />

				</td>
		</table>
		<input type="hidden" value="DEL" name="cmd">
	</form>
</body>
</html>
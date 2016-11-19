<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Deletar Colaborador</title>
</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>
	<c:if test="${msg ne '' or msg ne null }">
		<label style="color: red;">${msg }</label>
	</c:if>
	<form action="Deletar" method="post">
		<table>

			<tr>

				<td>CPF: <input type="text" name="Cpf" /><br /></td>

			</tr>
			<tr>

				<td><input type="Submit" value='Consultar' /></td>

			</tr>


		</table>

		<input type="hidden" value="CON" name="cmd">
	</form>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>


	<table border="1">

		<tr>

			<td>Nome</td>


			<td>CPF</td>


			<td>Data de Nascimento</td>


			<td>Cargo</td>

		</tr>
		
		<c:forEach var="colab" items="${lista}">
		<tr>

			<td>${colab.nome}</td>
			
			<td>
			${colab.cpfString}
			
			</td>
			
			<td>
			<fmt:formatDate var="fmtDateNascimento" value="${colab.dataDeNascimento}" pattern="dd/MM/yyyy"/>
			${fmtDateNascimento}</td>
			
			<td>${colab.descricao}</td>
			
			
		</tr>
		
		</c:forEach>

	</table>




</body>
</html>
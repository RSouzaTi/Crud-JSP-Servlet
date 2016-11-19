<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CadastrarColaborador</title>
<script type="text/javascript">
	function validarCampos() {
		var inputNome = document.getElementById("nome");
		if (inputNome.value == "") {
			alert("Favor preencher o campo nome");
			inputNome.focus();
			return false;
		}

		var inputDataNascimento = document.getElementById("dataNascimento");
		if (inputDataNascimento.value == "") {
			alert("Favor preencher o campo Data de Nascimento");
			inputDataNascimento.focus();
			return false;
		}

		var inputCpf = document.getElementById("cpf");
		if (inputCpf.value == "") {
			alert("Favor preencher o campo cpf");
			inputCpf.focus();
			return false;

		}

		var inputCargo = document.getElementById("cargo");
		if (inputCargo.value == "") {
			alert("Favor preencher o campo cargo");
			inputCargo.focus();
			return false;
		}

		return true;
	}

	function somenteNumero(e) {
		var tecla = (window.event) ? event.keyCode : e.which;
		if ((tecla > 47 && tecla < 58))
			return true;
		else {
			if (tecla == 8 || tecla == 0)
				return true;
			else
				return false;
		}

	}

	function dateFormat(e) {

		var v = e.value;
		if (v.match(/^\d{2}$/) !== null) {
			e.value = v + '/';
		} else if (v.match(/^\d{2}\/\d{2}$/) !== null) {
			e.value = v + '/';
		}

	}

	function cpfFormat(e) {
		var s = e.value;
		s = s.replace(/(\.|\(|\)|\/|\-| )+/g, '');

		tam = s.length + 1;

		if (tam > 3 && tam < 7)
			e.value = s.substr(0, 3) + '.' + s.substr(3, tam);
		if (tam >= 7 && tam < 10)
			e.value = s.substr(0, 3) + '.' + s.substr(3, 3) + '.'
					+ s.substr(6, tam - 6);
		if (tam >= 10 && tam < 12)
			e.value = s.substr(0, 3) + '.' + s.substr(3, 3) + '.'
					+ s.substr(6, 3) + '-' + s.substr(9, tam - 9);

	}
</script>
</head>
<body>

	<jsp:include page="Menu.jsp"></jsp:include>

	<c:if test="${msg ne '' or msg ne null }">
		<c:if test="${msgType eq 'info' }">
			<label style="color: blue;">${msg }</label>
		</c:if>
		<c:if test="${msgType eq 'error' }">
			<label style="color: red;">${msg }</label>
		</c:if>
	</c:if>

	<form action="cadastrar" method="post"
		onsubmit="return validarCampos();" id="frmCadastrar">
		<table>

			<tr>

				<td>Nome: <input type="text" name="nome" id="nome" /><br /></td>

			</tr>

			<tr>
				<td>Data de Nascimento: <input type="text"
					name="dataNascimento" id="dataNascimento"
					onkeyup="dateFormat(this)"></td>
			</tr>

			<tr>

				<td>CPF: <input type="text" name="cpf" id="cpf" maxlength="14"
					onkeyup="cpfFormat(this)"></td>

			</tr>

			<tr>
				<td>Cargo: <select name="cargo" id="cargo">
						<option selected value="">Selecione!</option>
						<c:forEach var="cargo" items="${listaCargos}">
							<option value="${cargo.id}">${cargo.descricao}</option>




						</c:forEach>
				</select> <input type="Submit" value='OK' name="btnSubmit" id="btnSubmit" />

				</td>

			</tr>


		</table>
	</form>
</body>
</html>
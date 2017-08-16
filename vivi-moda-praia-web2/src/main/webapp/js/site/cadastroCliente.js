$('.datepicker').datepicker({
	dateFormat : 'dd/mm/yy',
	maxDate : 0
});

function onSubmitFormCadastroCliente() {
	var csrfParameter = document.head
			.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var home = document.getElementById('home').getAttribute('value');

	event.preventDefault();
	// setup some local variables

	// let's select and cache all the fields
	// serialize the data in the form
	var formData = new FormData();

	var nome = document.getElementById('cliente.nome').value;
	var rg = document.getElementById('cliente.rg').value;
	var cpf = document.getElementById('cliente.cpfCnpj').value;
	var dataNascimento = document.getElementById('cliente.dataNascimento').value;
	var telefone = document.getElementById('cliente.telefone').value;
	var celular = document.getElementById('cliente.celular').value;
	var email = document.getElementById('cliente.email').value;
	var senha = document.getElementById('cliente.senha').value;
	var confirmacaoSenha = document.getElementById('confirmacaoSenha').value;

	formData.append('cliente.nome', nome);
	formData.append('cliente.rg', rg);
	formData.append('cliente.cpfCnpj', cpf);
	formData.append('cliente.dataNascimento', dataNascimento);
	formData.append('cliente.telefone', telefone);
	formData.append('cliente.celular', celular);
	formData.append('cliente.email', email);
	formData.append('cliente.senha', senha);
	formData.append('confirmacaoSenha', confirmacaoSenha);

	var action = document.getElementById('formCadastroCliente').getAttribute(
			'action');

	// fire off the request to /action
	var headers = {};
	headers[csrfHeader] = csrfToken;
	var xhr = getXHR();
	xhr.open('POST', action);
	xhr.setRequestHeader(csrfHeader, csrfToken);
	xhr.onload = function() {
		if (xhr.status === 200) {
			var response = xhr.responseText;
			// pagina = $(returndata).filter('#pagina')[0];
			var conteudo = document.getElementById('conteudo');
			var cadastro = document.getElementById('cadastro');

			conteudo.innerHTML = response;

		} else {
			alert('Request failed.  Returned status of ' + xhr.status);
		}
	}
	xhr.send(formData);

}
function buscarEnderecoPorCEP() {

	var cep = document.getElementById('endereco.cep').value;
	var idCliente = document.getElementById('idCliente').value;
	var parametros = 'cep=' + cep + '&idCliente=' + idCliente;
	var $home = $('#home').attr('value');
	$.ajax({
		url : $home + '/busca/endereco/cep',
		type : 'GET',
		data : parametros,
		async : true,
		cache : false,
		contentType : false,
		processData : false,
		success : function(returndata) {
			$("#cadastro").html(returndata);
		}
	});

}
function voltarCadastroCliente() {
	//var idCliente = document.getElementById('idCliente').value;
	var formData = new FormData($(this)[0]);
	var home = $('#home').attr('value');
	var headers = {};
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	headers[csrfHeader] = csrfToken;

	$.ajax({
		url : home + 'voltar/cadastro/cliente',
		type : 'GET',
		headers : headers,
		data : formData,
		async : true,
		cache : false,
		contentType : false,
		processData : false,
		success : function(returnData) {
			// window.location.reload();
			var divCadastro = document.getElementById("conteudo");
			divCadastro.innerHTML = returnData;
		}

	});

}
function getXHR() {
	if (window.XMLHttpRequest) {
		// Chrome, Firefox, IE7+, Opera, Safari
		return new XMLHttpRequest();
	}
	// IE6
	try {
		// The latest stable version. It has the best security, performance,
		// reliability, and W3C conformance. Ships with Vista, and available
		// with other OS's via downloads and updates.
		return new ActiveXObject('MSXML2.XMLHTTP.6.0');
	} catch (e) {
		try {
			// The fallback.
			return new ActiveXObject('MSXML2.XMLHTTP.3.0');
		} catch (e) {
			alert('This browser is not AJAX enabled.');
			return null;
		}
	}
}



/*
 * function onSubmitFormCadastroCliente(){ var csrfParameter =
 * document.head.querySelector("meta[name='_csrf_parameter']").content; var
 * csrfHeader =
 * document.head.querySelector("meta[name='_csrf_header']").content; var
 * csrfToken = document.head.querySelector("meta[name='_csrf']").content; var
 * home = document.getElementById('home').getAttribute('value');
 * 
 * event.preventDefault(); // setup some local variables
 *  // let's select and cache all the fields // serialize the data in the form
 * //var formData = new FormData(document.forms.item(0).id); var formData = new
 * FormData($(this)[0]); var action =
 * document.getElementById('formCadastroCliente').getAttribute('action');
 *  // fire off the request to /action var headers = {}; headers[csrfHeader] =
 * csrfToken; var xhr = new XMLHttpRequest(); xhr.open('POST', action);
 * xhr.setRequestHeader(csrfHeader, csrfToken); xhr.send("formCliente=" +
 * formData); if (xhr.status === 200) { alert('User\'s name is ' +
 * xhr.responseText); }else { alert('Request failed. Returned status of ' +
 * xhr.status); } }
 * 
 * 
 * $("#formCadastroCliente").submit(function(event) {
 * 
 * var csrfParameter = $("meta[name='_csrf_parameter']").attr("content"); var
 * csrfHeader = $("meta[name='_csrf_header']").attr("content"); var csrfToken =
 * $("meta[name='_csrf']").attr("content");
 * 
 * var $home = $('#home').attr('value'); event.preventDefault(); // setup some
 * local variables var $form = $("#formCadastroCliente");
 *  // let's select and cache all the fields // serialize the data in the form
 * var formData = new FormData($(this)[0]); // fire off the request to /action
 * var headers = {}; headers[csrfHeader] = csrfToken; $.ajax({ url :
 * $form.attr('action'), type : 'POST', headers : headers, data : formData,
 * async : true, cache : false, contentType : false, processData : false,
 * success : function(returndata) { pagina = $(returndata).filter('#pagina')[0];
 * 
 * if (pagina != 'undefined' || pagina.value == 'paginaPedido') {
 * $("#conteudo").html(returndata); } else { $("#cadastro").html(returndata); }
 * 
 * $(window).scrollTop(0); } }); });
 */
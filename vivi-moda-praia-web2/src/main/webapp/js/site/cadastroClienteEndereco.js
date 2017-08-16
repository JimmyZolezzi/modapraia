function voltarCadastroCliente(){
	var idCliente = document.getElementById('idCliente').value;
	var parametros = 'idCliente=' + idCliente;
	var home = $('#home').attr('value');
	var headers = {};
	var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	headers[csrfHeader] = csrfToken;
	
	$.ajax({
		url: home + 'voltar/cadastro/cliente',
		type: 'GET',
		headers: headers,
		data: parametros,
		async:true,
		cache: false,
		contentType: false,
	    processData: false,
		success: function (returnData){
			//window.location.reload();
			var divCadastro = document.getElementById("conteudo");
			divCadastro.innerHTML = returnData;
		}
			
	});
	
}
$("#form").submit(function(event){
	
	var $home = $('#home').attr('value');
	event.preventDefault();
	// setup some local variables
	var $form = $("#form");
	
	// let's select and cache all the fields
	// serialize the data in the form
	var formData = new FormData($(this)[0]);
	// fire off the request to /action
	$.ajax({
	    url: $form.attr('action'),
	    type: 'POST',
	    data: formData,
	    async: true,
	    cache: false,
	    contentType: false,
	  	processData: false,
	    success: function (returndata) {
	    	pagina = $(returndata).filter('#pagina')[0];
	    	
	    	if(pagina != 'undefined' ||  pagina.value == 'paginaDadosClienteCadastrado'){
		    	$("#conteudo").html(returndata);	
	    		
	    	}else{
	    		$("#cadastro").html(returndata);	
	    		$(window).scrollTop(0);
	    		
	    	}
    	}
	});
});	
function buscarEnderecoPorCEP(){
		
	var cep = document.getElementById('endereco.cep').value;
	var idCliente = document.getElementById('idCliente').value;
	var parametros = 'cep=' + cep + '&idCliente=' + idCliente;
	var $home = $('#home').attr('value');
	$.ajax({
	    url: $home + '/busca/endereco/cep',
	    type: 'GET',
	    data: parametros,
	    async: true,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function (returndata) {
	    	$("#cadastro").html(returndata);
	    }
	  });
	
}

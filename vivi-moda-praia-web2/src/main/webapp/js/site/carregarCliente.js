function carregarUsuarioLogado(){
	
	
	var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
	var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
	var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
	var headers = {};
	headers[csrfHeader] = csrfToken;
	
	var $home = $('#home').attr('value');
	$.ajax({
	    url: $home + 'carregarClienteLogado',
	    type: 'GET',
	    headers: headers,
	    async: true,
	    cache: false,
	    contentType: false,
	    processData: false,
	    success: function (returndata) {
	    	var nomeCliente = document.getElementById('nomeCliente');
	    	if(returndata != null && nomeCliente != null){
	    		nomeCliente.innerHTML  = returndata;
	    	}
	    }
	  });
}
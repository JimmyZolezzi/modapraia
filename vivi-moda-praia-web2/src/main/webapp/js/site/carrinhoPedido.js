	function mudarQuantidade(chave){
		var quantidade = document.getElementById('qtd_' + chave).value;
		var parametros = 'chave=' + chave + '&quantidade=' + quantidade;
		var $home = $('#home').attr('value');
		$.ajax({
		    url: $home + 'mudar/quantidade/produto/pedido',
		    type: 'GET',
		    data: parametros,
		    async: true,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		    	$("#tabelaCarrinhoCompras").html($(returndata));
		    	
		    }
		  });
	}
	
	function calcularFretePedidoCarrinho(){
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		
		var cep = document.getElementById('inputCEP').value;
		
		var $home = $('#home').attr('value');
		var parametros = 'cep=' + cep;
		var headers = {};
		headers[csrfHeader] = csrfToken;
		$('#resultadoLoading').html(getAjaxLoading());
		$('#resultadoLoading').addClass("loading");
		$.ajax({
		    url: $home + 'calcular/frete/pedido/carrinho',
		    type: 'GET',
		    headers: headers,
		    data: parametros,
		    async: true,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		    	$("#tabelaCarrinhoCompras").html($(returndata));
		    
		    },
		  }).done(function() {
			$('#resultadoLoading').html("");
			$('#resultadoLoading').removeClass("loading");
		  });
	}
	
	function excluirProdutoPedido(chave){
		var $home = $('#home').attr('value');
		var parametros = 'chave=' + chave;
		$.ajax({
		    url: $home + 'excluir/produto/pedido/carrinho',
		    type: 'GET',
		    data: parametros,
		    async: true,
		    cache: false,
		    contentType: false,
		    processData: false,
		    success: function (returndata) {
		    	$("#tabelaCarrinhoCompras").html($(returndata));
		    	atualizarCarrinho();
		    }
		  });
	}
	

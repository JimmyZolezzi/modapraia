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
	

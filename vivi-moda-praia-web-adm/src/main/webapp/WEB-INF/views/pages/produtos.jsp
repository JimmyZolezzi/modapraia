<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- Custom CSS -->

	<c:url var="home" value="/" scope="request" />		
	<input id="home" type="hidden" value="${home}"/>
	<h2>
		<a href="#" id="habilitarProduto" onclick="carregarFormularioCadastroProduto();" />Produto Novo</a><br/>
		<a href="#" id="habilitarVisualizacaoProdutos" onclick="carregarFormularioListaProdutos()" class="none" />Visualiar Produtos</a>
		
	</h2>
	<h3>Produtos</h3>
	
	<div id="conteudoProduto">
		<jsp:include page="componentes/lista-produtos.jsp" />
	</div>
<script>

	function carregarFormularioListaProdutos(){
		
		var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
		var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
		var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
		var home = document.getElementById('home').getAttribute('value');
		var headers = {};
		headers[csrfHeader] = csrfToken;
		var url = home + 'carregar/lista/produtos';
		$.ajax({
			url: url,
			type: 'GET',
			async: true,
			success: function(response){
				$("#conteudoProduto").html(response);
				habilitarLinkCadastroProduto();
			
				
			}
		
		});
		
	}

	
	function carregarFormularioCadastroProduto(){
		
		var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
		var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
		var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
		var home = document.getElementById('home').getAttribute('value');
		var headers = {};
		headers[csrfHeader] = csrfToken;
		var url = home + 'carregar/cadastro/produto';
		$.ajax({
			url: url,
			type: 'GET',
			async: true,
			success: function(response){
				$("#conteudoProduto").html(response);
				habilitarLinkVisualiacaoProdutos();
			}
		
		});
	}
	function categoriaOnChange2(){
		
		var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
		var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
		var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
		var home = document.getElementById('home').getAttribute('value');

		var idCategoria = document.getElementById("categoria").value;
		var url = "pesquisa/categoria/onChange";
		var data = 'categoria=' + idCategoria;
		var $home = $('#home').attr('value');
		var headers = {};
		headers[csrfHeader] = csrfToken;
		$.ajax({
		    url: $home + url,
		    type: 'GET',
		    headers: headers,
		    data: data,
		    async: true,
		    cache: false,
		    contentType: false,
		  	processData: false,
		    success: function (data) {
		    	var categorias = data;
		    	var tamanho = data.length;
		    	var opcoesSelect = "";
		    	opcoesSelect += "<option value=''>selecionar</option>";
		    	for (var i = 0; i < tamanho; i++){
		    		var subcategoria = categorias[i];
		    		opcoesSelect += "<option value='" + subcategoria.id + "'>" + subcategoria.descricao + "</option>";
		    					    	
		    	}
		    	$("#subcategoria").html(opcoesSelect);
		    }	
	    });
		
	}
	
	function calcularValorComPercentualDesconto(){
		
		var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
		var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
		var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
		
		var home = $('#home').attr('value');
		var url = home + "calcular/valorDesconto";
		var percentualDesconto = document.getElementById('descontoPercentualStr').value;
		var valorProduto = document.getElementById('valorStr').value;
		var headers = {};
		headers[csrfHeader] = csrfToken; 
		var data = "valorProduto=" + valorProduto + "&percentualDesconto=" + percentualDesconto;
		$.ajax(
			{
				url : url,
				headers: headers,
				data: data,
				asycr: false,
				type: 'GET',
				success:function(response){
					var valorDesconto = document.getElementById('valorDesconto');
					valorDesconto.innerHTML = 'R$ ' + response;
				}
				
			}	
		);
	}

	function habilitarLinkVisualiacaoProdutos(){
		var habilitarVisualizacaoProdutos = document.getElementById("habilitarVisualizacaoProdutos");
		var habilitarProduto = document.getElementById("habilitarProduto");
		habilitarProduto.classList.add("none");
		habilitarVisualizacaoProdutos.classList.remove("none");
	}

	function habilitarLinkCadastroProduto(){
		
		var habilitarVisualizacaoProdutos = document.getElementById("habilitarVisualizacaoProdutos");
		var habilitarProduto = document.getElementById("habilitarProduto");
		habilitarProduto.classList.remove("none");
		habilitarVisualizacaoProdutos.classList.add("none");
	}
	
	
	
	

	
	
	function formataValor(elemento) {
		var valor =  formatarValorMonetarioOnType(elemento.value);
		elemento.value = valor;
    }

	function formataValorPercentual(elemento){
		var valor = elemento.value;
		var tamanhoValorAntesFormatar = valor.length;
		var regxTest100 = /^100\,00\w$/;
		
		if(regxTest100.test(valor)){
			var tamanhoValor = valor.length;
			valor = valor.substr(0,tamanhoValor -1);
			
		}else{
			
			valor = formatarValorMonetarioOnType(valor);

			var rgx = /^(\d{1,3}\,\d{2})$/;
			var rgx2 = /^\d+$/;
			var valorComparacao = valor.replace(',','.');
			
			if((!rgx.test(valor) && !rgx2.test(valor)) || valorComparacao > 100 ){
				
				var tamanhoValor = valor.length;
				valor = valor.substr(0,tamanhoValor -1);
			
				valor = formatarValorMonetarioOnType(valor);
			}
			
		}
		elemento.value = valor;
	}
	
	function formatarValorMonetarioOnType(valor){
		
		var number = valor;
        var rgx = /^(\d+)(\.\d+)*?(\,\d+?)*$/;
        
       	var tamanho = number.length;

       	if (!rgx.test(number)) {
        	//obter o ultimo carater
        	//remove o ultimo caracter digitado errado
			number = number.substr(0,tamanho -1);
        	return number;
        }else{

        	
        	if(tamanho == 1){
        		number =  '0,0' + number;	
        	}
        	if(tamanho == 2){
        		number =  '0,' + number;	
        	}

        	//mover uma posicao da virgula para direita
				number = number.replace(',','');
				tamanho = number.length;
        	if(tamanho >= 2){
        		
        		var primeiroCaracter = number.substr(0,1);
        		var segundoCaracter = number.substr(1,1);
        		var valorAntesVirgula = number.substr(0, tamanho - (2));
        		if(valorAntesVirgula == ''){
        			valorAntesVirgula = '0';
        		}
        		
        		var valorDepoisVirgula = number.substr(tamanho -2 , tamanho)
        		valorAntesVirgula = valorAntesVirgula.replace('.','');
        		number = valorAntesVirgula + ',' + valorDepoisVirgula;
        		if((primeiroCaracter == '0' && tamanho >= 4) || (primeiroCaracter == '0' && segundoCaracter == '0') && tamanho >= 4){
        			number = number.substr(1, tamanho);
        		}
        		valorAntesVirgula = valorAntesVirgula.replace(/\./g , '');
       			var tamanhoAntesVirgula = valorAntesVirgula.length;
        		if(tamanhoAntesVirgula > 3){
					var totalPontos = Math.floor(tamanhoAntesVirgula/3);
					var numerosAntesPonto = tamanhoAntesVirgula - (totalPontos * 3);
					var valorAntesPonto = valorAntesVirgula.substr(0, numerosAntesPonto);
					var valorDepoisPonto = valorAntesVirgula.substr(numerosAntesPonto, tamanhoAntesVirgula);
					
					var tamanhoValorDepoisPonto = valorDepoisPonto.length;
					var contadorCaracteres = 0;
					var novovalorDepoisPonto = '';
						for(var i = 0; i < tamanhoValorDepoisPonto; i=i+3){
							if(valorAntesPonto == '' && i == 0){
								novovalorDepoisPonto += valorDepoisPonto.substr(i,3) ;
							}else{
								novovalorDepoisPonto +=  '.' + valorDepoisPonto.substr(i,3) ;
							}
						}
						number = valorAntesPonto + novovalorDepoisPonto	+ ',' +valorDepoisVirgula;					
        			
        		}
        		
        	}
        	return number;
        }
	}
	
function produtoAlteracao(idProduto){
		
		var csrfParameter = document.head.querySelector("meta[name='_csrf_parameter']").content;
		var csrfHeader = document.head.querySelector("meta[name='_csrf_header']").content;
		var csrfToken = document.head.querySelector("meta[name='_csrf']").content;
		var home = document.getElementById('home').getAttribute('value');
		var headers = {};
		headers[csrfHeader] = csrfToken;
		
		
		window.scrollTo(0, 0);
		var $form = $("#form");
		var $home = $('#home').attr('value');
		// let's select and cache all the fields
		// serialize the data in the form
		var formData = new FormData($form);
		// fire off the request to /action
		
		$.ajax({
		    url: $home + "produto/carregar/alteracao",
		    type: 'GET',
		    headers: headers,
		    data: {idProduto:idProduto},
		    async: true,
		    success: function (returndata) {
		    	$("#conteudoProduto").html(returndata);
		    	$("#form").attr('action', $home + "produto/alterar");
				$("#botaoFormulario").html('Alterar');
				var habilitarVisualizacaoProdutos = document.getElementById("habilitarVisualizacaoProdutos");
				habilitarVisualizacaoProdutos.classList.remove('none');
		    }
		  });
		
	}

</script>
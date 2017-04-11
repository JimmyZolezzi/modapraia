function aparecerOuEsconderPesquisa(){
	var pesquisaComp = document.getElementById('pesquisaComponente');
	if(pesquisaComp.classList.contains("pesquisaMobile")){
	
		pesquisaComp.classList.remove('pesquisaMobile');

	}else{
		pesquisaComp.classList.add('pesquisaMobile');
	}
}
function onResize(){
	var tamanhoPagina = document.body.clientWidth;
	var pesquisaComp = document.getElementById('pesquisaComponente');
	if(tamanhoPagina < 768){
		pesquisaComp.classList.remove('none');
	}else{
		pesquisaComp.classList.add('none');
	}
}
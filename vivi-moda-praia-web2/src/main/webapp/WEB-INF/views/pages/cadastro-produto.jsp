<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/theme-novo.css" />">
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/css/heroic-features.css" />">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/simple-sidebar.css" rel="stylesheet">
<div id="wrapper">

		
	<h3>Cadastro de Produtos</h3>
	<form:form method="post" modelAttribute="produtoForm"
		action="produto/add" enctype="multipart/form-data">
		<div class="form-group">
			<label for="descricao">Descri&ccedil;&atilde;o</label>
			<form:input path="descricao" type="text" class="form-control"
				id="descricao" placeholder="Descri&ccedil;&atilde;o" />
		</div>
		<div class="form-group">
			<label for="categoria">Categoria</label> <select class="form-control"
				id="categoria">
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
			</select>
		</div>
		<div class="form-group">
			<label for="foto">Foto 1</label> <input type="file" name="foto"
				id="foto">
			<p class="help-block">Example block-level help text here.</p>
		</div>
		<div class="form-group">
			<label class="sr-only" for="exampleInputAmount">Valor (em reais)</label>
			<div class="input-group">
				<div class="input-group-addon">R$</div>
				<form:input path="valor" type="text" class="form-control"
					id="exampleInputAmount" placeholder="Valor" />
				<div class="input-group-addon">.00</div>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Cadastrar</button>
	</form:form>
		
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</html>
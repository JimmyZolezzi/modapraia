<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<img class="thumbnail media-object" id="fotoSelecionada" data-zoom-image="<c:url value="/image?id=${idProdutoFoto}&tamanhoImagem=normal" />"  width="90%" height="100%" src="<c:url value="/image?id=${idProdutoFoto}&tamanhoImagem=normal" />"  />
<script>
	$("#fotoSelecionada").elevateZoom();
</script>
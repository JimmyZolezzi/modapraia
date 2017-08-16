<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Jumbotron Header -->
        <header class="jumbotron hero-spacer">
            <h1>Bem Vindo!!</h1>
            <p>Estamos com descontos. Aproveite as ofertas!</p>
        </header>

        <hr>
		
        <!-- Title -->
        <div class="row">
            <div class="col-lg-12">
                <h3>Destaques</h3>
            </div>
        </div>
		<jsp:include page="pages/componentes/comp-destaques.jsp" />		
        

       
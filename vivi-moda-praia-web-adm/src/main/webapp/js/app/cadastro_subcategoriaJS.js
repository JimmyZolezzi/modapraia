var app = angular.module('app', []);
app.controller('myCtrl', function($scope, $http) {
	var $home = $('#home').attr('value');
	$scope.pesquisaAngular = function() {
		$http.get($home + "subcategoria/pesquisa").then(function(response) {
			$scope.subcategorias = response.data;
			angular.forEach($scope.subcategorias, function(value, key) {
				value.categoria = JSON.parse(value.categoria);
			});
		});

	};
	$scope.pesquisaAngular();
});

$("#form").submit(function() {

	// setup some local variables
	var $form = $("#form");
	
	// let's select and cache all the fields
	// serialize the data in the form
	var serializedData = $form.serialize();

	// fire off the request to /action
	request = $.ajax({
		url : $form.attr('action'),
		type : "post",
		data : serializedData,
		success : function(data) {
			$("#formulario").html($(data).find("#formulario"));
			pesquisar();
			// angular.element($("#myctrl")).scope().pesquisa();
			console.log("SUCCESS: ", data);
		},
		error : function(e) {
			console.log(e);
		},
	});

	// prevent default posting of form
	event.preventDefault();
});

function pesquisar() {
	angular.element($("#myctrl")).scope().pesquisaAngular();
};
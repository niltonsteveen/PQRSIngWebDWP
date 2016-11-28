/**
 * Modulo de AngularJS que nos permite contar con toda la funcionalidad al lado del
 * cliente para la gestión de los clientes en el sistema
 * @autor Elver Suárez Alzate - elver.suarez@udea.edu.co
 */

var appCliente = angular.module('PQRS', ['ngRoute']);

/**
 * Define los servicios necesario para la autenticación del usuario
 */
appCliente.service('Usuario', function($http){
	
	/**
	 * Llama el servicio web para autenticar.
	 */
	this.autenticar = function(usuario, pws){
		return $http({			
			method: 'GET',
			url : 'http://localhost:8080/PQRSIngWebDWP/rest/Usuario/validar',
			params: {
				username: usuario,
				password: pws
			}
		});
	};
});

/**
 * Configura el route de la aplicación para trabajar con Single Page Application
 */
appCliente.config(['$routeProvider', function($routeProvider){
	
	$routeProvider.when('/',{
		templateUrl: 'pages/loginModal.html',
		controller: 'contLogin'
	});
	
	$routeProvider.when('/principal',{
		templateUrl: 'index.html',
		controller: 'contLoged'
	});
	
}]);

/**
 * Controlador para funcionalidad de la autenticación del usuario
 */
appCliente.controller('contLogin', function($scope, $location, Usuario){
	
	$scope.nombreUsuario="";
	$scope.contrasena="";	
	
	$scope.autenticar = function(){
		Usuario.autenticar($scope.nombreUsuario,$scope.contrasena).success(function(data){
			if(data != ''){
				$scope.nombreUsuario="";
				$scope.contrasena="";
				$('#modalLogin').modal('toggle');
				$('#liRegistrarse').remove();
				$('#liInicioSesion').remove();
				
				$('#mostrar1').text('nilton'); /* aca va el data.username que no se como ponerlo. 
				*/
				alert("autenticacion correcta");
				$location.url("/principal");
				return;
			}else{
				alert("Datos Erroneos")
			}
		});
	}	
});
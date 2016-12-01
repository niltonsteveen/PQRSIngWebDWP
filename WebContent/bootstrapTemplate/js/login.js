/**
 * Modulo de AngularJS que nos permite contar con toda la funcionalidad al lado del
 * cliente para la gestiÃ³n de los clientes en el sistema
 * @autor Elver SuÃ¡rez Alzate - elver.suarez@udea.edu.co
 */

var appCliente = angular.module('PQRS', ['ngRoute']);

var username='';

/**
 * Define los servicios necesario para la autenticaciÃ³n del usuario
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
 * Configura el route de la aplicaciÃ³n para trabajar con Single Page Application
 */
appCliente.config(['$routeProvider', function($routeProvider){
	
	$routeProvider.when('/',{
		templateUrl: '/WebContent/bootstrapTemplate/index.html',
		controller: 'contLogin'
	});
	
}]);


appCliente.provider('datoU',function(){
	return{
		$get: function(){
			
			return{
				
				getUsername: function(){
					return username;
				},
				setUsername:function(valor){
					username=valor;
				}
			}
		}
	}
})


appCliente.controller('contLoged', function($scope, $location, Usuario, datoU){
	$scope.usuario=datoU.getUsername();
	alert(datoU.getUsername());
});


/**
 * Controlador para funcionalidad de la autenticaciÃ³n del usuario
 */
appCliente.controller('contLogin', function($scope, $location, Usuario, datoU){
	
	$scope.nombreUsuario="";
	$scope.contrasena="";	
	$scope.username="";
	$scope.autenticar = function(){
		Usuario.autenticar($scope.nombreUsuario,$scope.contrasena).success(function(data){
			$scope.username=datoU.setUsername(data.correo);
			if(data != ''){
				$scope.nombreUsuario="";
				$scope.contrasena="";
				
				$('#modalLogin').modal('toggle');
				$('#liRegistrarse').remove();
				$('#liInicioSesion').remove();
				$('#header1').text(data.username);
				alert(datoU.getUsername());
				$location.url("/principal");
				return;
			}else{
				alert("Datos Erroneos")
			}
		});
	}	
});

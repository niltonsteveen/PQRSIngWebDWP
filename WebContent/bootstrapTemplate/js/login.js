/**
 * Modulo de AngularJS que nos permite contar con toda la funcionalidad al lado del
 * cliente para la gestiÃ³n de los clientes en el sistema
 * @autor Elver SuÃ¡rez Alzate - elver.suarez@udea.edu.co
 */

var appCliente = angular.module('PQRS', ['ngRoute']);

var username='';
var pass='';

/**
 * Define los servicios necesario para la autenticaciÃ³n del usuario
 */


appCliente.run(function($rootScope){
	$rootScope.username='';
	$rootScope.password='';
})

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
	

	$routeProvider.when('/registrarPQR',{
		templateUrl: 'registrarPQR.html',
		controller: 'contPQR'
	});
	
	
}]);


appCliente.provider('datoU',function(){
	return{
		$get: function(){
			
			return{
				
				getPassword: function(){
					return pass;
				},
				
				setPassword: function(password){
					pass=password;
				},
				
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


appCliente.controller('contRegistroPqr', function($scope, $location, Usuario, datoU){
	alert('entro al controlador');
});

appCliente.controller('contLoged', function($scope, $location, Usuario, datoU){
	$scope.usuario=datoU.getUsername();
	alert(datoU.getUsername());
});


/**
 * Controlador para funcionalidad de la autenticaciÃ³n del usuario
 */
appCliente.controller('contLogin', function($scope, $location, Usuario, datoU,$rootScope){
	
	$scope.nombreUsuario="";
	$scope.contrasena="";	
	$scope.username="";
	$scope.autenticar = function(){
		Usuario.autenticar($scope.nombreUsuario,$scope.contrasena).success(function(data){
			$scope.username=datoU.setUsername(data.username);
			datoU.setPassword($scope.contrasena);
			if(data != ''){
				$scope.nombreUsuario="";
				//$scope.contrasena="";
				$rootScope.username=data.username;
				$rootScope.password=$scope.contrasena;
				$('#modalLogin').modal('toggle');
				$('#liRegistrarse').remove();
				$('#liInicioSesion').remove();
				$('#usernameAcciones').text(data.username);
				if(data.rol=='GERENTE'){
					$('#notificacionesMenu').css({display: 'block'});
				}
				alert("Se ha autenticado correctamente");
				$location.url("/principal");
				return;
			}else{
				alert("Datos Erroneos")
			}
		});
	}	
});


appCliente.controller('menuuPQR', function($scope, $location, Usuario, datoU){
	
	$scope.nombreUsuario="";
	$scope.contrasena="";	
	$scope.username="";
	$scope.registrarPQR = function(){
		alert('redireccionado');
		
	}	
});



/**
 * Define el servicio para la creacion del PQR
 */
appCliente.service('SolicitudPQR', function($http){
	
	/**
	 * Llama el servicio web para crearPQR.
	 */
	this.crearpqr = function(solicitud, usuario, pws){
		return $http({			
			method: 'POST',
			url : 'http://localhost:8080/PQRSIngWebDWP/rest/SolicitudPQR/createPQR',
			params: {
				solicitud: solicitud,
				usuario:usuario,
				password: pws
			}
		});
	};
});



/**
 * control de la invocacion del servicio web
 */
appCliente.controller('contPQR', function($scope, $location, SolicitudPQR, datoU,$rootScope){
	
	/*var usuario=$rootScope.username;
	alert(usuario);*/
	
	$scope.sucursal="";
	$scope.tipoPqr="";
	$scope.descripcion="";	
	//$scope.nombreUsuario=usuario;
	$scope.contrasena=datoU.getPassword();
	$scope.registrarPQR = function(){
		$scope.solicitud=[{'sucursalId':$scope.sucursal, 'tipo':$scope.tipoPqr, 'descripcion':$scope.descripcion}];
		SolicitudPQR.crearpqr($scope.solicitud,$rootScope.username,$rootScope.password).then(function successCallback(response){
			//if(data != ''){
				alert('La solicitud ha sido creada correctamente');
				$('#modalRegistrarPqr').modal('toggle');
				/*insertar codigo faltante*/
				//return;
			/*}else{
				alert("Datos Erroneos")
			}*/
		},
		function errorCallback(response){
			alert("Ha ocurrido un error en la creacion de pqr");
		}
		
		);
	}	
});


/**
 * Define el servicio para  listar PQRs
 */
appCliente.service('ListarPQR', function($http){
	
	/**
	 * Llama el servicio web para crearPQR.
	 */
	this.listarpqr = function(usuario){
		return $http({			
			method: 'GET',
			url : 'http://localhost:8080/PQRSIngWebDWP/rest/SolicitudPQR/notificacionPQR',
			params: {
				usuario:usuario
			}
		});
	};
});



/**
 * control de la invocacion del servicio web ListarPQR
 */
appCliente.controller('contListarPQR', function($scope, ListarPQR, $rootScope){
	
	ListarPQR.listarpqr($rootScope.nombreUsuario).then(function successCallback(response){
		$scope.listaSolicitudes=response.data.SolicitudPQRWS;
	}, function errorCallback(response){
		alert("Ha ocurrido un error consultando los clientes");
	});
});

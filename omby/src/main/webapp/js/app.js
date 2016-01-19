angular.module('ombyApp', ['ngRoute', 'ngCookies', 'ombyApp.services'])
	.config
	(
		[ '$routeProvider', '$locationProvider', '$httpProvider', 
		  function($routeProvider, $locationProvider, $httpProvider) 
		  {			
			$routeProvider.when('/create', {
											templateUrl: 'partials/create.html',
											controller: CreateLawsuitController
											}
								);
			
			$routeProvider.when('/edit/:id', {
											templateUrl: 'partials/edit.html',
											controller: EditLawsuitController
											}
								);

			$routeProvider.when('/login', {
											templateUrl: 'partials/login.html',
											controller: LoginController
											}
								);
			
			$routeProvider.otherwise(		{
											templateUrl: 'partials/index.html',
											controller: LawsuitIndexController
											}
									);
			
			$locationProvider.hashPrefix('!');
			
			/* Register error provider 
			 * that shows message on failed requests 
			 * or redirects to login page on
			 * unauthenticated requests */
		    $httpProvider.interceptors.push
		    (
		    	function ($q, $rootScope, $location) 
		    	{
			        return {
			        		'responseError': function(rejection) 
			        						{
									        		var status = rejection.status;
									        		var config = rejection.config;
									        		var method = config.method;
									        		var url = config.url;									      
									        		if (status == 401) {
									        			$location.path( "/login" );
									        		} else 
									        		{
									        			$rootScope.error = method + " on " + url + " failed with status " + status;
									        		}									              
									        		return $q.reject(rejection);
			        						}
			        		};
			    }
		    );
		    //--------------------------- End of Error provider registration
		    
		    /* Registers auth token interceptor, 
		     * auth token is either passed by header or by query parameter
		     * as soon as there is an authenticated user */
		    $httpProvider.interceptors.push
		    (		    		
		    	function ($q, $rootScope, $location) 
		    	{
		    		return {
		    				'request': function(config) 
		    							{
	    									var isRestCall = config.url.indexOf('rest') == 0;
	    									if (isRestCall && angular.isDefined($rootScope.authToken)) 
	    									{
	    										var authToken = $rootScope.authToken;
	    										if (ombyAppConfig.useAuthTokenHeader) 
	    										{
	    											config.headers['X-Auth-Token'] = authToken;
	    										} else 
	    										{
	    											config.url = config.url + "?token=" + authToken;
	    										}
	    									}
	    									return config || $q.when(config);
		    							}
		    				};
		    	}
		    );
		    //------------------------------ End of auth token registration
		    
		    
		    
		   
			}// end of function($routeProvider, $locationProvider, $httpProvider)  		
		]		
	).run(
		function($rootScope, $location, $cookieStore, UserService) 
			{		
				/* Reset error when a new view is loaded */
				$rootScope.$on('$viewContentLoaded', function() {
					delete $rootScope.error;
				});
		
				$rootScope.hasRole = function(role) {
					if (1 == 1) {
						return true; // FIXME remove later
					}
					
					
					if ($rootScope.user === undefined) {
						return false;
					}
					
					if ($rootScope.user.roles[role] === undefined) {
						return false;
					}
					
					return $rootScope.user.roles[role];
				};
		
				$rootScope.logout = function() {
					delete $rootScope.user;
					delete $rootScope.authToken;
					$cookieStore.remove('authToken');
					$location.path("/login");
				};
		
				 /* Try getting valid user from cookie or go to login page */
				var originalPath = $location.path();
				$location.path("/login");
				var authToken = $cookieStore.get('authToken');
				if (authToken !== undefined) {
					$rootScope.authToken = authToken;
					UserService.get(
										function(user) 	
										{
											$rootScope.user = user;
											$location.path(originalPath);
										}
									);
				}	
				// Hides initialization texts loading... displayed on the page
				// Shows the actual part
				$rootScope.initialized = true;
		}
		// ------------- End of function	
	);
	// ------------- End of run

//-------------------------------LAWSUIT ADDITION---------------------------------------

function LawsuitIndexController($scope, LawsuitService) 
{	
	$scope.lawsuitEntries = LawsuitService.lawsuit.query(function(){
														console.log($scope.lawsuitEntries);
														}
												);	
	
	$scope.names = LawsuitService.lawsuits.list(function(){
															console.log($scope.lawsuitEntries);
															}
												);		
	$scope.deleteLawsuitEntry = function(lawsuitEntry) 
								{
									lawsuitEntry.$remove(
													function(){
																	$scope.lawsuitEntries = LawsuitService.lawsuit.query();
															  }
														);
								};
};
function EditLawsuitController($scope, $routeParams, $location, LawsuitService) 
{
	$scope.lawsuitEntry = LawsuitService.lawsuit.get({id: $routeParams.id});	
	$scope.save = function() 
	{
		$scope.lawsuitEntry.$save(function() 
								 {
									$location.path('/');
								 }
							   );
	};
};
function CreateLawsuitController($scope, $location, LawsuitService) 
{	
	$scope.lawsuitEntry = new LawsuitService.lawsuit();	
	$scope.save = function() 
	{
		$scope.lawsuitEntry.$save(function()
								 {
									$location.path('/');
								 }
							   );
	};
};



//--------------------------------------------------------------------------------------




function LoginController($scope, $rootScope, $location, $cookieStore, UserService) 
{	
	$scope.rememberMe = false;	
	$scope.login = function() 
	{
		UserService.authenticate($.param({username: $scope.username, password: $scope.password}), 
										function(authenticationResult) 
										{
											var authToken = authenticationResult.token;
											$rootScope.authToken = authToken;
											if ($scope.rememberMe) {
												$cookieStore.put('authToken', authToken);
											}
											UserService.get(function(user) {
												$rootScope.user = user;
												$location.path("/");
											});
										}
								);
	};
};



var services = angular.module('ombyApp.services', ['ngResource']);

services.factory('UserService', 
		function($resource) {	
								return $resource('rest/user/:action', {},
													{
														authenticate: {
															method: 'POST',
															params: {'action' : 'authenticate'},
															headers : {'Content-Type': 'application/x-www-form-urlencoded'}
														},
													}
												);
							}
				);

//----------------return $resource('rest/lawsuit/:id', {id: '@id'});
services.factory('LawsuitService', 
		function($resource) 
						
		
{	
	return {
	
         lawsuit: $resource('rest/lawsuit/:id', {id: '@id'}),
         lawsuits: $resource('rest/lawsuit/:action', {}, 
							        		 			{
											        	 list: {  method:'GET', 
													            params: {action: 'list'},
													        	isArray: true
											                    }
							         					}
         					)	
	
			}
}	
	

);

  




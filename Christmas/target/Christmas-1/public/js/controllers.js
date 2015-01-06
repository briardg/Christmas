var lclsCtrl = angular.module("lclsControllers",['ngSanitize','ngCookies']);

lclsCtrl.controller('linkCtrl', ['$scope',function($scope){	
	$scope.links=
	    [
	    {'id':'home','name':'home','link':'/'},
	    {'id':'Add','name':'Add','link':'/Add'},
	    {'id':'MyList','name':'My List','link':'/MyList'}
	    ];
  	//$scope.links.push({'id':'inscription','name':'Inscription','link':'/course/inscription'});
  	$scope.tab='home';

  	$scope.selectTab=function(selectTab){
  		$scope.tab=selectTab;
  	};

  	$scope.isSelected=function(checktab){
  		return $scope.tab===checktab;
  	};


}]);

lclsCtrl.controller('connectionCtrl', ['$scope','$cookies',function($scope,$cookies){	
        $scope.co=$cookies.co;
}]);

lclsCtrl.controller('presentCtrl',  ['$scope','PresentsResource','$location','$routeParams','$route',
  function($scope,PresentsResource,$location,$routeParams,$route) {
    $scope.present={};
    
    if( $location.path()=="/"){
        $scope.presentsByUsers = PresentsResource.query({ByUsers : "ByUsers"});
        $scope.percentage=[];
    }
    else if ($location.path()==("/Edit/"+$routeParams.id)){
        //fill present
        $scope.present=PresentsResource.get({ByUsers : $routeParams.id });
    }
    else if ($location.path()=="/MyList"){
        //fill presents
        $scope.presents=PresentsResource.query({ByUsers : ""});
    }
        
    $scope.delete=function(index,id){
        PresentsResource.delete({ByUsers : id}, 
        function(successResult) {
            $scope.presents.splice(index,1);
        });
        
    };
    
    $scope.update=function(){
        PresentsResource.update($scope.present,function(successResult) {
            alert("Updated :D");
            $location.path("/MyList");
        });
        
    };
    
    $scope.edit=function(id){
        $location.path("/Edit/"+id);
    };
    
    $scope.submit=function(){
        PresentsResource.save($scope.present,function(successResult) {
            alert("Saved :D");
            $scope.present={};
        });
    };
    
    $scope.buyPresent = function(id){   
        PresentsResource.buy({ByUsers : 'Buy',
                              id:id,
                              percentage: $scope.percentage[id]
                            }, 
        function(successResult) {
            alert("Just bought "+$scope.percentage[id]+" %.");
            $route.reload();
        });
    };
    
    $scope.dropDownListPercentageArray = function(percentage){
        var s=[];
        for(i=10;i<=(100-percentage);i=i+10)
            s.push(i);
        return s;
    };
    
  }]);
  
  lclsCtrl.controller('usersCtrl',  ['$scope','UsersResource','$window',
  function($scope,UsersResource,$window) {
            //fill users
        $scope.users=UsersResource.query();
        
        $scope.changerUser=function(userLogin){
           	$window.location.href=server+"connection?login="+userLogin;
        };
  }]);





var lclsServices = angular.module('lclsServices', ['ngResource']);

var server = "http://christmas.briardg.me/";
var WS="REST/";

lclsServices.factory('PresentsResource', ['$resource',
  function($resource){
    return $resource(server+WS+"presents/:ByUsers", {ByUsers:'@ByUsers'}, {
      query: {method:'GET',isArray:true},
      get: {method:'GET',isArray:false},
      save: {method:'POST'},
      update: {method:'PUT'},
      buy: {method:'PUT', params:{id:'@id',percentage:'@percentage'}},
      remove: {method:'DELETE'}
    });
  }]);

lclsServices.factory('UsersResource', ['$resource',
  function($resource){
    return $resource(server+WS+'users/', {
      query: {method:'GET',isArray:true}
    });
  }]);
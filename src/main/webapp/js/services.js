/**
 * Created by Sandeep on 01/06/14.
 * Edited by Ricardo Mello on 27/09/2016
 */

angular.module('hoteisApp.services', []).factory('Hotel', function($resource) {
	return $resource('/api/hotel/:id', {}, {
        'query': {
            method: 'GET',
            isArray: true
        },
        'get': {
            method: 'GET',
            transformResponse: function(data) {
                if (data) {
                    data = angular.fromJson(data);
                }
                return data;
            }
        },
        'update': {
            method: 'PUT'
        }
    });
}).service('popupService', function($window) {
    this.showPopup = function(message) {
        return $window.confirm(message);
    }
});

/**
 * Created by Sandeep on 01/06/14.
 * Edited by Ricardo Mello on 27/09/2016.
 */

angular.module('hoteisApp', ['ui.router', 'ngResource', 'hoteisApp.controllers', 'hoteisApp.services']);

angular.module('hoteisApp').config(function($stateProvider) {
    $stateProvider.state('hoteis', {
        url: '/hoteis',
        templateUrl: 'partials/hoteis.html',
        controller: 'HotelListController'
    }).state('viewHotel', {
        url: '/hoteis/:id/view',
        templateUrl: 'partials/hotel-view.html',
        controller: 'HotelViewController'
    }).state('addHotel', {
        url: '/hoteis/add',
        templateUrl: 'partials/hotel-add.html',
        controller: 'HotelCreateController'
    }).state('editHotel', {
        url: '/hoteis/:id/edit',
        templateUrl: 'partials/hotel-edit.html',
        controller: 'HotelEditController'
    });
}).run(function($state) {
    $state.go('hoteis');
});
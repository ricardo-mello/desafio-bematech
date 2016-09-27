/**
 * Created by Sandeep on 01/06/14.
 * Edited by Ricardo Mello on 27/09/2016.
 */

angular.module('hoteisApp.controllers', []).controller('HotelListController', function($scope, $state, popupService, $window, Hotel) {

    $scope.hoteis = Hotel.query();

    $scope.deleteHotel = function(hotel) {
        if (popupService.showPopup('Você realmente quer remover este hotel?')) {
            hotel.$delete({id: hotel.id},
                function () {
                    $window.location.href = '';
                });
        }
    }


}).controller('HotelViewController', function($scope, $stateParams, Hotel) {

    $scope.hotel = Hotel.get({
        id: $stateParams.id
    });

}).controller('HotelCreateController', function($scope, $state, $stateParams, Hotel) {

    $scope.hotel = new Hotel();

    $scope.addHotel = function() {
        $scope.hotel.$save(function() {
            $state.go('hoteis');
        });
    }

}).controller('HotelEditController', function($scope, $state, $stateParams, Hotel) {

    $scope.updateHotel = function() {
        $scope.hotel.$update(function() {
            $state.go('hoteis');
        });
    };

    $scope.loadHotel = function() {
        $scope.hotel = Hotel.get({
            id: $stateParams.id
        });
    };

    $scope.loadHotel();
});

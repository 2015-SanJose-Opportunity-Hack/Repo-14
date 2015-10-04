var number_1, number_2, math_Operator, request;

var demoApp = angular.module('demoApp', [ 'ui.bootstrap' ]);

function SimpleController($scope, $http, $location) {
	$scope.clicked = function() {
		number_1 = $scope.number1;
		number_2 = $scope.number2;
		math_Operator = $scope.radioModel;
		if (!isNaN(number_1) && !isNaN(number_2)) {
			if ((number_2 === 0) && (math_Operator === "Division")) {
				alert("'0' cannot be a divisor");
			} else {
				request = {
					method : 'POST',
					url : 'http://localhost:8080/calculator',
					data : {
						"firstNumber" : number_1,
						"secondNumber" : number_2,
						"operation" : math_Operator
					},
				}
				$http(request).success(function(response) {
					$scope.answer = response.finalValue;
				}).error(function(err) {
					alert("Error");
				});
			}
		} else {
			if (isNaN(number_1)) {
				if (isNaN(number_2)) {
					alert("Value of First and Second Operand Must be Number");
				} else {
					alert("Value of First Operand Must be Number");
				}
			} else if (isNaN(number_2)) {
				alert("Value of Second Operand Must be Number");
			}
		}
	};
}

demoApp.controller('SimpleController', SimpleController);
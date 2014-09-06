app = angular.module 'app', []

app.controller 'mainController', ($scope) ->
  $scope.title = 'Обработка изображений'

app.controller 'uploadController', ($scope, fileUpload) ->
  $scope.imageUrl = '/images/'
  $scope.upload = ->
    file = $scope.image
    uploadUrl = "upload"
    console.log 'file is ' + JSON.stringify(file)
    fileUpload file, uploadUrl, (data) ->
      $scope.imageUrl += data

app.directive 'fileModel', ($parse) ->
  restrict: 'A',
  link: (scope, element, attrs) ->
    model = $parse attrs.fileModel
    modelSetter = model.assign
    element.bind 'change', ->
      scope.$apply ->
        modelSetter scope, element[0].files[0]

app.factory 'fileUpload', ($http) ->
    uploadFileToUrl = (file, uploadUrl, callback) ->
      fd = new FormData
      fd.append 'file', file
      $http.post uploadUrl, fd,
          transformRequest: angular.identity
          headers: 'Content-Type': undefined
      .success (data) ->
        callback(data)
      .error ->
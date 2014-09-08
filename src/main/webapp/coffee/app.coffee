app = angular.module 'app', []

app.constant 'config', {
  imagesPath: 'images'
  applyFilterUrl: 'applyFilter'
  uploadUrl: 'upload'
}

app.controller 'mainController', ($scope, $http, config, fileUpload) ->
  getSessionImage = ->
    $http.get 'getImage'
    .success (fileName) ->
      if (fileName)
        $scope.image = config.imagesPath + '/' + fileName

  $scope.applyFilter = (data) ->
    console.log data
    $http {
      url: config.applyFilterUrl,
      method: 'get',
      params: data
    }
    .success (fileName) ->
      $scope.outputImage = config.imagesPath + '/' + fileName
    .error (data) ->
      alert 'Упс! Что-то пошло не так.'

  $scope.uploadImage = () ->
    file = $scope.newImage
    console.log 'file is ' + JSON.stringify(file)
    fileUpload file, config.uploadUrl, (fileName) ->
      $scope.image = config.imagesPath + '/' + fileName

  getSessionImage()

app.directive 'fileModel', ($parse) ->
  restrict: 'A',
  link: (scope, element, attrs) ->
    model = $parse(attrs.fileModel)
    modelSetter = model.assign
    element.bind 'change', ->
      scope.$apply ->
        modelSetter scope, element[0].files[0]

app.factory 'fileUpload', ($http, $log) ->
  uploadFileToUrl = (file, uploadUrl, callback) ->
    fd = new FormData
    fd.append 'file', file
    $http.post uploadUrl, fd,
      transformRequest: angular.identity
      headers:
        'Content-Type': undefined
    .success (data) ->
      callback(data)
    .error ->
      $log.error 'cannot upload file'
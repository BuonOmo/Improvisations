document.app.controller 'homeController', [ 'improvisationService', '$scope', (improvisationService, $scope) ->
  $scope.hello = "world"
  $scope.improvisations = []

  ###
    Retrieve all improvisations
  ###
  $scope.all = ->
    improvisationService.all()
     .success (data) ->
        # Convert seconds to readable time
#        data.map (element) ->
#          element.duration =
#            moment 0
#            .second element.duration
#            .format 'm:ss'
        $scope.improvisations = data
      .error (data) ->
        console.log data

  ###
    Save an improvisation using its id (or creating it) and retrieving content from cells
  ###
  $scope.save = (id) ->
    # retrieve fields and corresponding values
    row = $('#'+id).find 'td' #todo: find if it is possible to do it with ng-model (or other angular item)
    fields = [ 'theme', 'category', 'playerNumber', 'type', 'niceDuration' ]
    values = fields
      .map (element,index) ->
        row[index].textContent
    # convert fields to json object
    improvisation= if typeof id == 'number' then 'id': id else {} # Create or update, if there is an id set
    for i in [0..fields.length]
      improvisation[fields[i]] = values[i] if values[i]
    # POST to the server
    improvisationService.save improvisation
      .success (data) ->
        data.saved = true
        if id == 'new_improvisation'
          # clean input
          row.parent().find('td:not(:last)').empty()
          # append new data
          $scope.improvisations.push data
        else
          # update category (to have title) fixme: maybe it’s better to update all
          $scope.improvisations
            .find (element) -> element.id == data.id
            .category.description = data.category.description
            .saved = true #todo: correct this
    return

  ###
    Delete an improvisation using its id
  ###
  $scope.delete = (id) ->
    improvisationService.delete id
      .success ->
        element = $scope.improvisations.find (element,index) -> return index if element.id == id
        index = $scope.improvisations.indexOf element
        $scope.improvisations.splice index,1
        return
      .error (data) ->
        console.log data
        return false

  ###
     Event listeners
  ###
  $scope.keyPressedRow = ($event,id) ->
    if $event.key == 'Enter'
      $event.preventDefault()
      $scope.save id

  ###
    Debug method
  ###
  $scope.echo = (something) ->
    console.log something
    return

  # Document ready
  $ ->
    $scope.all()
    return

  return
]
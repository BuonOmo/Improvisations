document.app.service 'improvisationService', ['$http', ($http) ->
  this.all = ->
    return $http.get '/improvisation'


  this.get = (id) ->
    return $http.get '/improvisation/' + id if id != null


  this.save = (improvisation) ->
    improvisation.category = { name: improvisation.category }
    console.log "Sending to /improvisation\nbody:"+JSON.stringify(improvisation)
    return $http.post '/improvisation', improvisation


  this.delete = (id) ->
    return $http.delete '/improvisations/' + id

  return
]

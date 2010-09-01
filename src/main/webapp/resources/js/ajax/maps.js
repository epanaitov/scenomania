dojo.declare("GoogleMap", null, {
	map: null,
	defaultOptions: {
		zoom: 7,
	    center: new google.maps.LatLng(42.37072, -74.058197),
	    mapTypeId: google.maps.MapTypeId.ROADMAP
	},

	onMapMoveEnd: function(){},
	onMapMouseMove: function (latLng){},
	onMapLoad: function(){},
	
	constructor: function (divId, options){
		if (typeof options == 'undefined'){
			options = defaultOptions;
		}
		this.map = new google.maps.Map(document.getElementById(divId),
				options);
		
		var _this = this;
		google.maps.event.addListener(this.map, 'idle', function (){
			_this.onMapMoveEnd();
		});
		google.maps.event.addListener(this.map, 'mousemove', function(mouseEvent){
			_this.onMapMouseMove(mouseEvent.latLng);
		});
		google.maps.event.addListener(this.map, 'tilesloaded', function(mouseEvent){
			_this.onMapLoad();
		});
		
	}
});

dojo.declare("CitiesMap", [GoogleMap], {
	cities: null,
	markerCluster: null,
	emptyImage: new google.maps.MarkerImage('',  new google.maps.Size(0, 0)),
	ajaxURL: '',
	
	constructor: function (divId, options){
		this.cities = [];
		this.markerCluster = new MarkerClusterer(this.map, [], {
	          maxZoom: null,
	          gridSize: null,
	          styles: null
	    });
	},
	
	onCityClick: function (city, mouseEvent){},
	
	onMapMoveEnd: function (){
		this.loadCities();
	},
	
	cityExists: function (city){
		for(key in this.cities){
			if (this.cities[key].id == city.id){
				return true;
			}
		}
		return false;
	},
	addCity: function (city){
		if (this.cityExists(city)){
			return;
		}
		
		this.cities.push(city);
		var _this = this;
		
		var marker = new google.maps.Marker({
	           position: new google.maps.LatLng(city.lat, city.lng),
	           draggable: false,
	           icon: this.emptyImage	           
	          });
		
		var circle = new google.maps.Circle({
			map: null,
			radius: ((city.pop/10265307))*9000 + 1000*Math.exp(city.pop/10265307-.5),
			center: new google.maps.LatLng(city.lat, city.lng)
		});
		
		
		google.maps.event.addListener(circle, 'click', function (mouseEvent){
			_this.onCityClick(city, mouseEvent);
		});
		
		google.maps.event.addListener(marker, 'visible_changed', function (e){
			if (marker.getVisible()){
				circle.setMap(_this.map);
			} else {
				circle.setMap(null);
			}
		});
		
		
		
		this.markerCluster.addMarker(marker);
	},

	loadCities: function (){
		var borders = {
				north: this.map.getBounds().getNorthEast().lat(),
				east: this.map.getBounds().getNorthEast().lng(),
				south: this.map.getBounds().getSouthWest().lat(),
				west: this.map.getBounds().getSouthWest().lng()
		};
		var _this = this;
		var req = new Array();
		for (b in borders){
			req.push(b + '=' + borders[b]);
		}
		var xhrArgs = {
				url: _this.ajaxURL + '?' + req.join('&'),
				handleAs: "text",
				preventCache: true,
				load: function (data, ioargs){
					var cities = dojo.fromJson(data);
					dojo.forEach(cities.cities, function (city, i){
							_this.addCity(city);
					});
				},
				error: function(error, ioargs){
					alert('request died: '+error);
				}
		}; //xhrArgs
		//alert(xhrArgs.url);
		dojo.xhrGet(xhrArgs);
		
	}
});
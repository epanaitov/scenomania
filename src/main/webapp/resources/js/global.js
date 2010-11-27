function url_builder(host,	path) {
	var url =  "";
	if (host) url+= 'http://'+host;
	if (path) url+= path;
	return url;
}

function ub(h, p) { // shortcut
	return url_builder(h, p);
}
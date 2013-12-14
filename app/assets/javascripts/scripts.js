$(document).ready(function() {
	$('[href=#]').click(function (e) {
		e.preventDefault()
	})
	
	$.ctrl = function(key, callback, args) {
		$(document).keydown(function(e) {
			if(!args) args=[]; // IE barks when args is null 
			if(e.keyCode == key.charCodeAt(0) && e.ctrlKey) {
				callback.apply(this, args);
				return false;
			}
		});
	};
});
document.onmousemove = function(mouse_coordinate) {
	let x = mouse_coordinate.x - 624;
	let y = mouse_coordinate.y - 310;
	console.log(x + ' ' + y);
	document.querySelector('.eyeball-left').style.transform = 'rotate(' + eye_co(x, y) + 'deg)';
	document.querySelector('.eyeball-right').style.transform = 'rotate(' + eye_co(x - 286, y) + 'deg)';

	function eye_co(x,y) {
		if (x > 0 && y > 0) {
			return 57.2958 * (Math.PI / 2 - Math.atan(x / y));
		}
		if (x < 0 && y > 0) {
			return 57.2958 * (Math.PI / 2 - Math.atan(x / y));
		}
		if (x < 0 && y < 0) {
			return 57.2958 * (Math.PI + Math.atan(y / x));	
		}
		if (x > 0 && y < 0) {
			return 57.2958 * ( 3 * Math.PI / 2 + Math.abs(Math.atan(x / y)));	
		}
	}
}
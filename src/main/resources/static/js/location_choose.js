function mine() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var elem1 = document.getElementById("latitude");
            var elem2 = document.getElementById("longitude");
            elem1.value = position.coords.latitude;
            elem2.value = position.coords.longitude;
            console.log(elem1.value);
            console.log(elem2.value);
            $.get('https://maps.googleapis.com/maps/api/geocode/json?latlng=' + position.coords.latitude + ',' + position.coords.longitude + '&key=AIzaSyAandusjfdlwecXwtMD9RdIlNmC1mrQJaQ', function (data) {
                var elem = document.getElementById("address");
                console.log(data);
                elem.value = data.results[0].formatted_address;
            });
        });
    } else 'No position found';
}
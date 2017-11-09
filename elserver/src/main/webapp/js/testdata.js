function OutputHtml() {
	var strurl = "http://localhost:8080/getJson?query=常州";
	$.get(strurl, function (data) {
		var obj = data.replace(/^\s+|\s+$/g, "");
		var obj = $.parseJSON(obj);
		var rows = obj.rows;
		alert(1);
		alert(rows[1].time);
	});
}

function loadCrudTable(id, path, remove){
	$("#"+id).empty();
	$.ajax({
		url : path,
		type : 'GET',
		contentType : 'application/json'
	}).done(function(data) {
		data.forEach(function(item) {
			var row = $('<div></div>').attr('class', 'row');
			$.each(item, function(k, v) {
				var removeCol = $('<div></div>').attr('class', 'col').text(v);
				row.append(removeCol);
			});
			if(remove === true){
				var removeCol = $('<div></div>').attr('class', 'col alert alert-danger').text('Delete');
				row.append(removeCol);
			}
			$("#"+id).append(row);
		});
	});
}

function insert(path, content, functionPost){
	$.ajax({
		url : path,
		type : 'POST',
		contentType : 'application/json',
		data: content
	}).done(function(data) {
		functionPost();
	});
}
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
				var removeCol = $('<div></div>').attr('class', 'col-2');
				var buttonCol = $('<button></button>').attr('class', 'col btn btn-danger').text('Delete');
				removeCol.append(buttonCol);
				row.append(removeCol);
			}
			$("#"+id).append(row);
		});
	});
}

function loadSelectGroups(id, path){
	$("#"+id).empty();
	$.ajax({
		url : path,
		type : 'GET',
		contentType : 'application/json'
	}).done(function(data) {
		data.forEach(function(item) {
			var option = $('<option></option>').text(item.group);
			$("#"+id).append(option);
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
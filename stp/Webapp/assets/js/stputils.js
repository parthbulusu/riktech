$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
function error()
{
	alert("error");
}

function ajaxPost(url,data,successFunction,failureFunction){
	$.ajax({
	    type: "POST",
	    url: url,
	    data: data,
	    dataType: 'json',
	    success: successFunction,
	    failure: failureFunction
	});
}
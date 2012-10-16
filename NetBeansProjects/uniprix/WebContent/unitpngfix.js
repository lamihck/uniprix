var clear = "images/clear.gif"; // path to clear.gif

pngfix = function() {
	var els = document.getElementsByTagName('*');
	var i_p = /\.png/i;
	var i = els.length;
	while (i-- > 0) {
		var el = els[i];
		var es = el.style;
		if (el.src && el.src.match(i_p) && es.filter == '') {
			el.height = el.height;
			el.width = el.width;
			es.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
					+ el.src + "',sizingMethod='crop')";
			el.src = clear;
		} else {
			var elb = el.currentStyle.backgroundImage;
			if (elb.match(i_p)) {
				var path = elb.split('"');
				var rep = (el.currentStyle.backgroundRepeat == 'no-repeat') ? 'crop'
						: 'scale';
				es.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"
						+ path[1] + "',sizingMethod='" + rep + "')";
				es.height = el.clientHeight + 'px';
				es.backgroundImage = "none";
			}
		}
		if (el.currentStyle.position != 'absolute' && !es.filter
				&& !el.tagName.match(/(body|html|script)/gi))
			es.position = "relative";
		if (es.filter && el.currentStyle.position == "relative")
			es.position = "static";
	}
};

window.attachEvent('onload', pngfix);

function getIdArticle(_idArticle)
{
	document.getElementById("articleId").value = _idArticle;
}

function plus(champs, sub)
{
	var plus = parseInt(document.getElementById(champs).value);
	document.getElementById(champs).value = plus +1;
	validate(sub, champs);
}

function del(champs, sub)
{
	//var plus = parseInt(document.getElementById(champs).value);
	document.getElementById(champs).value = 0;
	validate(sub, champs);
}

function moins(champs, sub)
{
	var plus = parseInt(document.getElementById(champs).value);
	document.getElementById(champs).value = plus -1;
	validate(sub, champs);
}

function validate(sub, champs2)
{
	var plus = parseInt(document.getElementById(champs2).value);
	if(isNaN(plus))
		{
			alert("Ce n'est pas un entier !");
		}
	else
		{
		var evt = document.createEvent("MouseEvents");
		  evt.initMouseEvent("click", true, true, window,
		    0, 0, 0, 0, 0, false, false, false, false, 0, null);
		  
		var input = document.getElementById(sub);
		input.dispatchEvent(evt);
		}
	
	
}

function notNUll2(champs)
{
	if (champs.value == "")
	{ 
		alert("Le champ ne doit pas etre vide");
		return false;
	}
	return true;
}


function notNUll(champs)
{
	if (champs.value == "")
	{ 
		alert("Le champ ne doit pas etre vide");
		return false;
	}
	if(isNaN(champs.value) && champs!="date")
	{
		alert("Le champ n'est pas un numérique");
		return false;
	}
	return true;
}
function is_numeric(num)
{
	var exp = new RegExp("^[0-9-.]*$","g");
	return exp.test(num);
}

function verifmail(emailStr) {
	if(emailStr !="")
	{
		var checkTLD = 1;
		var knownDomsPat = /^(ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cat|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|um|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw|aero|arpa|biz|com|coop|edu|eu|gov|info|int|mil|museum|name|net|org|pro|jobs|travel)$/;
		var emailPat = /^(.+)@(.+)$/;
		var specialChars = "\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
		var validChars = "\[^\\s" + specialChars + "\]";
		var quotedUser = "(\"[^\"]*\")";
		var ipDomainPat = /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
		var atom = validChars + '+';
		var word = "(" + atom + "|" + quotedUser + ")";
		var userPat = new RegExp("^" + word + "(\\." + word + ")*$");
		var domainPat = new RegExp("^" + atom + "(\\." + atom +")*$");
		var matchArray = emailStr.match(emailPat);
		if (matchArray == null) {alert("mail incorrect"); return false; }
		var user = matchArray[1];
		var domain = matchArray[2];
		for (i=0; i<user.length; i++) {
			if (user.charCodeAt(i) > 127) {alert("mail incorrect"); return false; }
		}
		for (i=0; i<domain.length; i++) {
			if (domain.charCodeAt(i) > 127) {alert("mail incorrect"); return false; }
		}
		if (user.match(userPat) == null) {alert("mail incorrect"); return false; }
		var IPArray=domain.match(ipDomainPat);
		if (IPArray != null) {
			for (var i=1; i<=4; i++) {
				if (IPArray[i] > 255) {alert("mail incorrect"); return false; }
			}
			return true;
		}
		var atomPat = new RegExp("^" + atom + "$");
		var domArr = domain.split(".");
		var len = domArr.length;
		for (i=0; i<len; i++) {
			if (domArr[i].search(atomPat) == -1) {alert("mail incorrect"); return false; }
		}
		if (checkTLD && domArr[domArr.length-1].length!=2 && domArr[domArr.length-1].search(knownDomsPat)==-1) {alert("mail incorrect"); return false; }
		if (len < 2) {alert("mail incorrect"); return false; }
		
		return true;
	}

}
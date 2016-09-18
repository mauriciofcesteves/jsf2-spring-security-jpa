/* Funções Genéricas */

function addEvent(obj,evt,fn) {
    if (obj.addEventListener) {
        obj.addEventListener(evt,fn,false);
    } else if (obj.attachEvent) {
        obj.attachEvent("on"+evt,fn);
    }
}

function removeEvent(obj,evt,fn) {
    if (obj.removeEventListener) {
        obj.removeEventListener(evt,fn,false);
    } else if (obj.detachEvent) {
        obj.detachEvent("on"+evt,fn);
    }
}

/* Funções Específicas */

function checkAll(obj) {
	var checks = document.getElementsByTagName("input");
	if (checks) {
		for (i = 0; i < checks.length; i++) {
			if (checks[i].type.toLowerCase() == "checkbox") {
				checks[i].checked = obj.checked;
			}
		}	
	}
}

function classeDestNacionalidade(obj){
	
	if (obj){
		if(obj.value=="N"){
			habilitar("form:painel_valorDiariaDolar", true);
			habilitar("form:painel_descricaoFatorGerador", true);
			habilitar("form:painel_valorParcialDiaria", true);
			habilitar("form:painel_variacaoValorDiaria", false);
			//document.getElementById("form:painel_valorDiariaDolar").disabled=true;
			//document.getElementById("form:painel_descricaoFatorGerador").disabled=true;
			//document.getElementById("form:painel_variacaoValorDiaria").disabled=false;
		}else if(obj.value=="I") {
			habilitar("form:painel_valorDiariaDolar", false);
			habilitar("form:painel_descricaoFatorGerador", false);
			habilitar("form:painel_variacaoValorDiaria", true);
			//document.getElementById("form:painel_valorDiariaDolar").disabled=false;
			//document.getElementById("form:painel_descricaoFatorGerador").disabled=false;
			//document.getElementById("form:painel_variacaoValorDiaria").disabled=true;
		}
	}
}

function habilitar(nomeCampo, valor) {
	var campo = document.getElementById(nomeCampo);
	
	if (valor) {
		campo.disabled = true;
		campo.value = "";
	} else {
		campo.disabled = false;
	}
}

function classeDestNacionalidadeOnload() {
	var f = document.getElementById("form");
	if(f) {
		var radios = f.elements['form:painel_tipoClasseDestino'];
		if(radios) {
			for(i=0;i<radios.length;i++) {
				if(radios[i].checked) {
					classeDestNacionalidade(radios[i]);
				}
			}
		}
	}
}

function isNumeroComPonto(e) {
	var retorno = false;	
	var code = (e.keyCode ? e.keyCode : e.which);
	
	if (code > 47 && code < 58) {
		// Um número foi digitado.
		retorno = true;
	} else if (code == 8 ||
			code == 46 ||
			code == 37 ||
			code == 39 ||
			code == 35 ||
			code == 36) {
		
			// Teclas Back Space, Home, Seta Direita, Seta Esquerda, End e Delete
			retorno = true;
	} else {
		retorno = false;
	}
	return retorno;
}

function isNumero(e) {
	var retorno = false;	
	var code = (e.keyCode ? e.keyCode : e.which);
	
	if (code > 47 && code < 58) {
		// Um número foi digitado.
		retorno = true;
	} else if (code == 8 ||
			code == 37 ||
			code == 39 ||
			code == 35 ||
			code == 36 ||
			code == 9) {
		
			// Teclas Back Space, Home, Seta Direita, Seta Esquerda, End ,Delete e Tab
			retorno = true;
	} else {
		retorno = false;
	}
	return retorno;
}
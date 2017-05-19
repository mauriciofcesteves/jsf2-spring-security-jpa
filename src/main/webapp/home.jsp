<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script> 
	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">	
	<title>Autenticação</title>
	
</head>

<body style="padding-top: 40px; padding-bottom: 40px; background-color: #eee;">
	<div class="container">

	
		<form action="j_spring_security_check" method="post" class="form-signin">
				<h2 class="form-signin-heading">
					Monitoramento de Segurados
				</h2>
	
				<div>
					<div>
							<%
								if(request.getParameter("error") != null){
								if (request.getParameter("error").equals("invalido")){
							%>
						
							<p>
								<span style="color:red">
									<div style="font-family: Sans-serif">
										<strong>Usuário ou Senha invalidos</strong>
									</div>
								</span>
							</p>
						
							<%
						
								} //fim do if equals
								
								}//fim do if null
						
							%>
					</div>
					<div>
        				<label for="inputEmail" class="sr-only">Usuário</label>
						<input name="j_username" type="text" id="inputEmail" class="form-control" placeholder="Usuário" required="" autofocus=""
						value="${not empty login_error ? SPRING_SECURITY_LAST_USERNAME : ''}" />
					</div>
					<div>
				        <label for="inputPassword" class="sr-only">Senha</label>
				        <input type="password" id="inputPassword" class="form-control" placeholder="Senha" required="" name="j_password"/>
					</div>
					<br/>
					<div>
						<input type="submit" value="Entrar" class="btn btn-lg btn-primary btn-block">
					</div>
				</div>
		</form>
	</div>
</body>

</html>

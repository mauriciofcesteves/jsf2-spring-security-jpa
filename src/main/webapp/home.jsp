<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">

<title>Autenticação</title>

</head>

<body>
	<form action="j_spring_security_check" method="post">
			<div style="font-family: Sans-serif">
				Monitoramento Segurados
			</div>

			<div class="ui-g">
				<div class="ui-g-2">
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
				<div class="ui-g-2">
					<strong>Usuário:</strong>
				</div>
				<div class="ui-g-2">
					<input name="j_username" type="text" value="${not empty login_error ? SPRING_SECURITY_LAST_USERNAME : ''}" />
				</div>
				<div class="ui-g-2">
					<strong>Senha:</strong>
				</div>
				<div class="ui-g-2">
					<input type="password" name="j_password">
				</div>
				<div class="ui-g-2">
					<input type="submit" value="Efetuar Login">
				</div>
			</div>
	</form>
</body>

</html>

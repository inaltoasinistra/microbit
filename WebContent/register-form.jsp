<form name="register" action="register.jsp" method="POST">
	<input type="email" name="email" value="you@domain" onclick="this.form.elements[0].value = ''" />
	<input type="password" name="password" value="***" onclick="this.form.elements[1].value = ''" />
	<input type="password" name="confirm" value="***" onclick="this.form.elements[2].value = ''" />
	<input type="submit" value="Register" style="visibility:hidden" />
</form>